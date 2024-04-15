package com.ruoyi.charity.service.impl.raise;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.ruoyi.charity.domain.bo.*;
import com.ruoyi.charity.domain.dto.*;
import com.ruoyi.charity.domain.vo.*;
import com.ruoyi.charity.mapper.join.CharityUserJMapper;
import com.ruoyi.charity.mapper.join.DonationTraceJMapper;
import com.ruoyi.charity.mapper.join.RaiseFundJMapper;
import com.ruoyi.charity.mapper.mp.MPBankTransferRecordMapper;
import com.ruoyi.charity.mapper.mp.MPRaiseAuditMapper;
import com.ruoyi.charity.mapper.mp.MPRaiseFundMapper;
import com.ruoyi.charity.service.impl.CharityControllerService;
import com.ruoyi.charity.service.raise.ICharityRaiseAuditService;
import com.ruoyi.charity.service.raise.RaiseFundService;
import com.ruoyi.charity.utils.BlockTimeUtil;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Slf4j
@Service
public class RaiseFundServiceImpl implements RaiseFundService {


    @Autowired
    private CharityControllerService charityControllerService;

    @Autowired
    private MPRaiseFundMapper MPRaiseFundMapper;

    @Autowired
    private CharityUserJMapper charityUserJMapper;

    @Autowired
    private ICharityRaiseAuditService charityRaiseAuditService;

    @Autowired
    private MPRaiseAuditMapper MPRaiseAuditMapper;

    @Autowired
    private RaiseFundJMapper raiseFundJMapper;

    @Autowired
    private DonationTraceJMapper donationTraceJMapper;


    @Autowired
    private RedisCache redisCache;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MPBankTransferRecordMapper mpBankTransferRecordMapper;

    /**
     * 用户发起公益募资业务
     * @param charityRaiseFund
     * @return AjaxResult
     */
    @SneakyThrows
    @Override
    public AjaxResult initiateRaiseFund(CharityRaiseFund charityRaiseFund) {
        // 查询当前的公益活动是否已经存在
        CharityRaiseFund isRaiseFund = MPRaiseFundMapper.selectOne(Wrappers.lambdaQuery(CharityRaiseFund.class)
                .eq(CharityRaiseFund::getTitle, charityRaiseFund.getTitle())
                .eq(CharityRaiseFund::getPromoterAddress, charityRaiseFund.getPromoterAddress()));
        if (isRaiseFund != null) return AjaxResult.error("当前的公益活动已经存在");

        // 转换当前的时间
        long startTime = BlockTimeUtil.stringToMillis(charityRaiseFund.getStartTime(), "yyyy-MM-dd");
        long endTime = BlockTimeUtil.stringToMillis(charityRaiseFund.getEndTime(), "yyyy-MM-dd");

        // 调用区块链发起公益募资活动
        CharityControllerInitiateFundRaisingInputBO fundRaisingInputBO = getRaisingInputBO(charityRaiseFund, startTime, endTime);
        TransactionResponse transactionResponse = charityControllerService.InitiateFundRaising(fundRaisingInputBO);

        if (transactionResponse.getReturnMessage().equals(CharityControllerService.SUCCESS))
        {
            // 获取返回的值
            JSONArray result = JSONArray.parseArray(
                    JSONArray.parseArray(transactionResponse.getValues())
                            .get(0)
                            .toString());
            // 插入数据库中
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            charityRaiseFund.setId(result.getLongValue(0));
            charityRaiseFund.setCreateTime(sdf.parse(BlockTimeUtil.convertToDateTime(result.getLongValue(3))));
            charityRaiseFund.setStatus(result.getBigInteger(5));
            charityRaiseFund.setTotalPeople(BigInteger.valueOf(result.getLongValue(8)));
            charityRaiseFund.setOverAmount(BigInteger.valueOf(result.getLongValue(10)));
            charityRaiseFund.setWithdrawAmount(BigInteger.valueOf(result.getLongValue(11)));

            // 判断当前是否插入数据库成功
            int status = MPRaiseFundMapper.insert(charityRaiseFund);
            if (status > 0)
            {
                AjaxResult success = AjaxResult.success();
                success.put("msg","发起公益活动成功");
                success.put("id",result.getLongValue(0));
                return success;
            }
        }
        return AjaxResult.error().put("msg","发起公益活动失败");
    }

    /**
     * 上传证明信息接口
     * @param certificateInfoVo
     * @param username
     * @return
     */
    @Override
    public AjaxResult uploadCertificate(CertificateInfoVo certificateInfoVo, String username) {

        // 查询当前是否有审批 有则说明已经上传过了 需要提示已经上传证明
        CharityRaiseAudit isRaiseAudit = MPRaiseAuditMapper.selectOne(Wrappers.lambdaQuery(CharityRaiseAudit.class)
                .eq(CharityRaiseAudit::getRaiseId, certificateInfoVo.getRaiseId()));
        if (isRaiseAudit != null) return AjaxResult.error().put("msg","当前已经上传了证明");

        // 根据用户名查询用户的地址然后根据公益募资的活动编号和用户的区块链账户地址进行查询详细的信息
        // SELECT * FROM charity_raise_fund WHERE id = '19' and promoter_address = (SELECT user_address FROM charity_user WHERE username = 'mmm');
        CharityUser charityUser = queryCharityUserByUsername(username);
        CharityRaiseFund isRaiseFund = MPRaiseFundMapper.selectOne(Wrappers.lambdaQuery(CharityRaiseFund.class)
                .eq(CharityRaiseFund::getId, certificateInfoVo.getRaiseId())
                .eq(CharityRaiseFund::getPromoterAddress, charityUser.getUserAddress()));

        // 如果查询的结果不是空的说明已经发起了公益募资活动
        if (isRaiseFund != null) {
            // 如果不是空的 直接进行证明上链 然后需要进行审批
            CharityControllerUploadCertificateInputBO certificateInputBO = new CharityControllerUploadCertificateInputBO();
            certificateInputBO.set_raiseId(BigInteger.valueOf(certificateInfoVo.getRaiseId()));
            certificateInputBO.set_CertificateInfo(JSON.toJSONString(certificateInfoVo));
            System.out.println(certificateInputBO);
            try
            {
                TransactionResponse transactionResponse = charityControllerService.uploadCertificate(certificateInputBO);
                if (transactionResponse.getReturnMessage().equals(CharityControllerService.SUCCESS))
                {
                    // 如果交易成功就添加一条申请记录到审核中
                    CharityRaiseAudit charityRaiseAudit = new CharityRaiseAudit();
                    charityRaiseAudit.setRaiseId(certificateInfoVo.getRaiseId());
                    charityRaiseAudit.setApplyStatus(1);
                    charityRaiseAudit.setApplyTime(new Date());
                    charityRaiseAuditService.insertCharityRaiseAudit(charityRaiseAudit);

                    // 上传成功
                    return AjaxResult.success().put("msg","上传成功");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return AjaxResult.error().put("msg","上传失败");
    }

    private CharityUser queryCharityUserByUsername(String username) {
        CharityUser charityUser = charityUserJMapper.selectOne(Wrappers
                .lambdaQuery(CharityUser.class)
                .eq(CharityUser::getUsername, username));
        return charityUser;
    }

    /**
     * 查询发布的公益募资活动详细信息和上传的证明信息以及审批信息
     *
     * @param raiseId
     * @param username
     * @return
     */
    @SneakyThrows
    @Override
    public AjaxResult getRaiseFundInfo(Long raiseId, String username) {
        // 默认如果没有数据直接查询Redis读取缓存数据
        String raise_fund_audit_value = redisCache.getCacheObject(CacheConstants.RAISE_FUND_AUDIT_KEY + raiseId);
        HashMap result = JSONObject.parseObject(raise_fund_audit_value, HashMap.class);
        if (raise_fund_audit_value != null) {
            AjaxResult success = AjaxResult.success();
            success.put("data",result);
            return success;
        }
        // 多表联查当前的公益募资活动信息和审批信息
        MPJLambdaWrapper<CharityRaiseFund> lambdaWrapper = new MPJLambdaWrapper<CharityRaiseFund>()
                .eq(CharityRaiseFund::getId,raiseId)
                .selectAll(CharityRaiseFund.class)
                .select(
                        CharityRaiseAudit::getRaiseId,
                        CharityRaiseAudit::getApplyStatus,
                        CharityRaiseAudit::getUsername,
                        CharityRaiseAudit::getApplyTime,
                        CharityRaiseAudit::getAuditTime
                ).leftJoin(CharityRaiseAudit.class, CharityRaiseAudit::getRaiseId, CharityRaiseFund::getId);

        RaiseFundAuditVo raiseFundAuditVo = raiseFundJMapper.selectJoinOne(RaiseFundAuditVo.class, lambdaWrapper);

        // 调用查询区块链的信息 查询区块链的上传的证明信息
        CharityControllerGetCertificateInfoDetailInputBO detailInputBO = new CharityControllerGetCertificateInfoDetailInputBO();
        detailInputBO.set_raiseId(BigInteger.valueOf(raiseId));
        CallResponse callResponse = charityControllerService.getCertificateInfoDetail(detailInputBO);
        if (callResponse.getReturnMessage().equals(CharityControllerService.SUCCESS)) {
            String data = JSONArray.parseArray(callResponse.getValues()).get(0).toString();
            CertificateInfoVo certificateInfoVo = JSONObject.parseObject(data, CertificateInfoVo.class);

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("raise_info",raiseFundAuditVo);
            hashMap.put("certificate_info",certificateInfoVo);

            // 存储到redis缓存中 用户在第一次查询的时候是需要久一点获取数据，当用户第二次获取数据的时候可以直接从redis中读取缓存
            redisCache.setCacheObject(CacheConstants.RAISE_FUND_AUDIT_KEY + raiseId,JSON.toJSONString(hashMap),5, TimeUnit.MINUTES);
            AjaxResult success = AjaxResult.success();
            success.put("data",hashMap);
            return success;
        }
        return AjaxResult.error().put("msg","查询失败");
    }

    /**
     * 根据ID查询对应公益活动的上传证明信息
     *
     * @param raiseId
     * @param username
     * @return AjaxResult
     */
    @Override
    public AjaxResult getCertificateInfo(BigInteger raiseId, String username) {
        // 默认先查询redis的缓存中是否有该数据
        CertificateInfoVo certificateInfoVoResult = redisCache.getCacheObject(CacheConstants.CERTIFICATE_INFO_KEY + username);
        if (certificateInfoVoResult != null) {
            AjaxResult success = AjaxResult.success();
            success.put("data",certificateInfoVoResult);
            success.put("msg","查询成功");
            return success;
        }

        // 调用区块链查询当前的上传证明信息
        CharityControllerGetCertificateInfoDetailInputBO detailInputBO = new CharityControllerGetCertificateInfoDetailInputBO();
        detailInputBO.set_raiseId(raiseId);
        try
        {
            CallResponse callResponse = charityControllerService.getCertificateInfoDetail(detailInputBO);
            if (callResponse.getReturnMessage().equals(CharityControllerService.SUCCESS)) {
                String data = JSONArray.parseArray(callResponse.getValues()).get(0).toString();
                CertificateInfoVo certificateInfoVo = JSONObject.parseObject(data, CertificateInfoVo.class);

                // 第一次查询直接存储到Redis缓存中
                redisCache.setCacheObject(CacheConstants.CERTIFICATE_INFO_KEY + username,certificateInfoVo,5,TimeUnit.MINUTES);
                AjaxResult success = AjaxResult.success();
                success.put("data",certificateInfoVo);
                success.put("msg","查询成功");
                return success;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.error().put("msg","查询失败");

    }


    /**
     * 查询当前的公益募资活动的投票状态和详细信息
     * @param raiseId
     * @param username
     * @return
     */
    @Override
    public AjaxResult getRaiseFundVoteStatus(Long raiseId, String username) {

        // 先从redis中进行查询
        VoteInfo voteInfoResult = redisCache.getCacheObject(CacheConstants.VOTE_INFO + username);
        if (voteInfoResult != null) {
            AjaxResult success = AjaxResult.success();
            success.put("data",voteInfoResult);
            success.put("msg","查询成功");
            return success;
        }

        // 如果redis中没有则需要重新查询区块链上的数据
        CharityControllerGetVoteInfoInputBO infoInputBO = new CharityControllerGetVoteInfoInputBO();
        infoInputBO.set_raiseId(BigInteger.valueOf(raiseId));

        try
        {
            CallResponse callResponse = charityControllerService.getVoteInfo(infoInputBO);
            if (callResponse.getReturnMessage().equals(CharityControllerService.SUCCESS)) {

                VoteInfo voteInfo = VoteInfoByCallResponse(callResponse);

                // 存储到redis中
                redisCache.setCacheObject(CacheConstants.VOTE_INFO + username,voteInfo,5,TimeUnit.MINUTES);
                AjaxResult success = AjaxResult.success();
                success.put("data",voteInfo);
                success.put("msg","查询成功");
                return success;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.error().put("msg","查询失败");
    }

    private static VoteInfo VoteInfoByCallResponse(CallResponse callResponse) {
        JSONArray result = JSONArray.parseArray(callResponse.getValues());
        VoteInfo voteInfo = new VoteInfo();
        voteInfo.setIsYes(result.getBigInteger(0));
        voteInfo.setIsNo(result.getBigInteger(1));
        voteInfo.setIsTrue(result.getBoolean(2));
        return voteInfo;
    }

    /**
     * 参与投票
     * @param raiseId
     * @param status
     * @param username
     * @return
     */
    @Override
    public AjaxResult voteOfRaiseFund(Long raiseId, Boolean status, String username) {
        CharityUser charityUser = queryCharityUserByUsername(username);

        CharityControllerVoteOfRaiseFundInputBO voteInfo = new CharityControllerVoteOfRaiseFundInputBO();
        voteInfo.set_raiseId(BigInteger.valueOf(raiseId));
        voteInfo.set_flag(status);
        voteInfo.set_userAddress(charityUser.getUserAddress());
        try
        {
            TransactionResponse transactionResponse = charityControllerService.voteOfRaiseFund(voteInfo);
            if (transactionResponse.getReturnMessage().equals(CharityControllerService.SUCCESS)) {
                Boolean isStatus = (Boolean) JSONArray.parseArray(transactionResponse.getValues()).get(0);
                if (isStatus){

                    // 如果投票成功这里直接更新当前的用户投票次数
                    Integer oldVoteCount = charityUser.getVoteCount();
                    charityUser.setVoteCount(oldVoteCount + 1);
                    charityUserJMapper.updateById(charityUser);
                    return AjaxResult.success().put("msg","投票成功");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.error().put("msg","投票失败");
    }

    @Override
    public AjaxResult getRaiseFundDetail(Long raiseId) {
        CharityRaiseFund charityRaiseFund = MPRaiseFundMapper.selectById(raiseId);
        if (charityRaiseFund != null) {
            AjaxResult success = AjaxResult.success();
            success.put("data",charityRaiseFund);
            success.put("msg","查询成功");
            return  success;
        }
        return AjaxResult.error().put("msg","查询失败");
    }


    @Override
    public AjaxResult donation(DonatedFundVo donatedFundVo, String username) {
        // 直接进入缓存读取数据查询是否已经被审核和是否已经被票选通过
        CallResponse callResponse = null;
        VoteInfo voteInfo = null;
        CharityRaiseAudit charityRaiseAudit = null;
        CharityUser charityUser = null;

        Map<String, Object> cacheMap = redisCache.getCacheMap(CacheConstants.DONATINA_FUND_KEY + donatedFundVo.get_raiseId());
        if (!cacheMap.isEmpty()) {
            charityRaiseAudit = (CharityRaiseAudit) cacheMap.get("charityRaiseAudit");
            voteInfo = (VoteInfo) cacheMap.get("voteInfo");
            charityUser = (CharityUser) cacheMap.get("userInfo");

            if (charityRaiseAudit.getApplyStatus() != 2) {
                return AjaxResult.error().put("msg","当前的公益活动未审核通过");
            }

            if (!voteInfo.getIsTrue()){
                return AjaxResult.error().put("msg","当前公益活动未通过票选");
            }

            if (charityUser.getAmount().compareTo(donatedFundVo.get_amount()) == -1) {
                return AjaxResult.error("msg","当前的用户余额不足");
            }

        }
        charityUser = charityUserJMapper
                .selectOne(Wrappers.lambdaQuery(CharityUser.class).eq(CharityUser::getUsername, username));
        // 判断当前的用户是否有足够的金额进行支付
        if (charityUser.getAmount().compareTo(donatedFundVo.get_amount()) == -1) {
            return AjaxResult.error("msg","当前的用户余额不足");
        }

        // 查询当前捐款的公益活动是否被审核
        charityRaiseAudit = MPRaiseAuditMapper
                .selectOne(Wrappers.lambdaQuery(CharityRaiseAudit.class)
                        .eq(CharityRaiseAudit::getRaiseId, donatedFundVo.get_raiseId()));

        if (charityRaiseAudit.getApplyStatus() != 2) {
            return AjaxResult.error().put("msg","当前的公益活动未审核通过");
        }

        // 查询当前的公益活动是否已经投票成功
        CharityControllerGetVoteInfoInputBO voteInfoInputBO = new CharityControllerGetVoteInfoInputBO();
        voteInfoInputBO.set_raiseId(donatedFundVo.get_raiseId());
        try
        {
            callResponse = charityControllerService.getVoteInfo(voteInfoInputBO);
        } catch (Exception e) {
            // 交易失败
            throw new RuntimeException(e);
        }
        if (callResponse.getReturnMessage().equals(CharityControllerService.SUCCESS)) {
            voteInfo = VoteInfoByCallResponse(callResponse);
            if (!voteInfo.getIsTrue()){
                return AjaxResult.error().put("msg","当前公益活动未通过票选");
            }
        }

        // 将两次的查询直接读入到Redis缓存中 避免吗每一次访问查询都需要进行数据库的IO交互
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("charityRaiseAudit",charityRaiseAudit);
        dataMap.put("voteInfo",voteInfo);
        dataMap.put("userInfo",charityUser);
        redisCache.setCacheMap(CacheConstants.DONATINA_FUND_KEY + donatedFundVo.get_raiseId(),dataMap);
        redisCache.expire(CacheConstants.DONATINA_FUND_KEY + donatedFundVo.get_raiseId(),5,TimeUnit.MINUTES);


        // 使用rabbitmq消息队列进行异步处理 优化当前的接口耗时
        MessageResult messageResult = new MessageResult();
        messageResult.setCode(HttpStatus.SUCCESS);
        messageResult.setMessage("用户参与捐款");
        messageResult.setData(JSONObject.toJSONString(donatedFundVo));
        rabbitTemplate.convertAndSend("direct_donation_fund_exchange","DONATION_FUND_KEY",JSONObject.toJSONString(messageResult));

        return AjaxResult.success().put("msg","捐款成功");
    }


    /**
     * select this raise fund all trace,about user's address and transaction id
     * @param raiseId
     * @return AjaxResult
     */
    @SneakyThrows
    @Override
    public List<DonationTraceVo> getRaiseFundTrace(BigInteger raiseId) {
        MPJLambdaWrapper<DonationTrace> lambdaWrapper = new MPJLambdaWrapper<DonationTrace>()
                .eq(DonationTrace::getRaiseId, raiseId)
                .selectAll(DonationTrace.class)
                .select(DonationTransaction::getTransactionHash, DonationTransaction::getBlockNumber, DonationTransaction::getStatus)
                .leftJoin(DonationTransaction.class, DonationTransaction::getRaiseId, DonationTrace::getDonationId);

        List<DonationTraceVo> donationTraceVos = donationTraceJMapper.selectJoinList(DonationTraceVo.class, lambdaWrapper);
        return donationTraceVos;
    }


    /**
     * user can donate fund to this raise fund by bank to bank
     * @param bankTransferRecordVo
     * @return AjaxResult
     */
    @Override
    public AjaxResult donationBankTransfer(BankTransferRecordVo bankTransferRecordVo) {
        CharityControllerDonationByBankTransferInputBO transferInputBO = new CharityControllerDonationByBankTransferInputBO();
        transferInputBO.set_raiseId(bankTransferRecordVo.getRaiseId());
        transferInputBO.set_cardId(bankTransferRecordVo.getDonorCardId());
        bankTransferRecordVo.setTransTime(BlockTimeUtil.convertToLocalTime(System.currentTimeMillis()));
        transferInputBO.set_transferRecordInfo(JSONObject.toJSONString(bankTransferRecordVo));
        try
        {
            TransactionResponse transactionResponse = charityControllerService.donationByBankTransfer(transferInputBO);
            if (transactionResponse.getReturnMessage().equals(CharityControllerService.SUCCESS))
            {
                // 删除Redis的捐款溯源记录缓存
                boolean deleteBankTransferFlag = redisCache
                        .deleteObject(CacheConstants.USER_DONATION_BANK_TRANSFER_KEY + bankTransferRecordVo.getDonorCardId());

                if (!deleteBankTransferFlag){
                    log.info("当前的缓存已经删除,redis的key为：{}",CacheConstants.USER_DONATION_BANK_TRANSFER_KEY + bankTransferRecordVo.getDonorCardId());
                }
                // 更新当前的数据库
                BankTransferRecord bankTransferRecord = new BankTransferRecord();
                bankTransferRecord.setAmount(bankTransferRecordVo.getDonorAmount());
                bankTransferRecord.setRaiseId(bankTransferRecordVo.getRaiseId());
                mpBankTransferRecordMapper.insert(bankTransferRecord);

                return AjaxResult.success().put("msg","转账成功");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.error().put("msg","转账失败");
    }

    @Override
    public AjaxResult getDonationBankTransferInfo(String cardId) {
        // 查询Redis缓存中是否有最新的溯源记录
        ArrayList<BankTransferRecordVo> result = redisCache.getCacheObject(CacheConstants.USER_DONATION_BANK_TRANSFER_KEY + cardId);
        if (result != null) {
            AjaxResult success = AjaxResult.success();
            success.put("msg","查询成功");
            success.put("data",result);
            success.put("total",result.size());
            return success;
        }

        CharityControllerGetDonationTaceByBankTransferInputBO transferInputBO = new CharityControllerGetDonationTaceByBankTransferInputBO();
        transferInputBO.set_cardId(cardId);
        try
        {
            TransactionResponse donationTraceByBankTransfer = charityControllerService.getDonationTaceByBankTransfer(transferInputBO);
            if (donationTraceByBankTransfer.getReturnMessage().equals(CharityControllerService.SUCCESS)) {
                JSONArray jsonArray = JSONArray.parseArray(JSONArray.parseArray(donationTraceByBankTransfer.getValues()).get(0).toString());

                ArrayList<BankTransferRecordVo> transferRecordVos = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    BankTransferRecordVo bankTransferRecordVo = JSONObject.parseObject(jsonArray.get(i).toString(), BankTransferRecordVo.class);
                    transferRecordVos.add(bankTransferRecordVo);
                }

                AjaxResult success = AjaxResult.success();
                success.put("msg","查询成功");
                success.put("data",transferRecordVos);
                success.put("total",transferRecordVos.size());

                // 添加到Redis 缓存中
                redisCache.setCacheObject(CacheConstants.USER_DONATION_BANK_TRANSFER_KEY + cardId,transferRecordVos,5,TimeUnit.MINUTES);
                return success;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return AjaxResult.error().put("msg","查询失败");
    }


    @SneakyThrows
    @Override
    public AjaxResult getDonationBankTransferRecordList(Integer pageNum, Integer pageSize, BigInteger raiseId) {

        CharityControllerGetRaiseFundBankTransferRecordInputBO recordInputBO = new CharityControllerGetRaiseFundBankTransferRecordInputBO();
        recordInputBO.set_raiseId(raiseId);
        TransactionResponse raiseFundBankTransferRecord = charityControllerService.getRaiseFundBankTransferRecord(recordInputBO);
        if (raiseFundBankTransferRecord.getReturnMessage().equals(CharityControllerService.SUCCESS)) {
            JSONArray jsonArray = JSONArray.parseArray(JSONArray.parseArray(raiseFundBankTransferRecord.getValues()).get(0).toString());

            // 基于入参的pageNum和pageSize实现对jsonArray数组的分页查询
            int fromIndex = (pageNum - 1) * pageSize;
            int toIndex = Math.min(pageNum * pageSize, jsonArray.size());
            JSONArray subArray = new JSONArray();
            if (fromIndex <= toIndex) {
                subArray.addAll(jsonArray.subList(fromIndex, toIndex));
            }

            ArrayList<BankTransferRecordVo> transferRecordVos = new ArrayList<>();
            for (int i = 0; i < subArray.size(); i++) {
                BankTransferRecordVo bankTransferRecordVo = JSONObject.parseObject(jsonArray.get(i).toString(), BankTransferRecordVo.class);
                transferRecordVos.add(bankTransferRecordVo);
            }
            AjaxResult success = AjaxResult.success();
            success.put("msg","查询成功");
            success.put("data",transferRecordVos);
            success.put("total",transferRecordVos.size());
            return success;
        }

        return AjaxResult.error().put("msg","查询失败");
    }

    @Override
    public AjaxResult selectRaiseFundTotalData(BigInteger raiseId) {
        // 查询投票状态
        CharityControllerGetVoteInfoInputBO inputBO = new CharityControllerGetVoteInfoInputBO();
        inputBO.set_raiseId(raiseId);
        CallResponse callResponse = null;
        BigInteger totalVoteIsYes = null;
        try {
            callResponse = charityControllerService.getVoteInfo(inputBO);

            JSONArray result = JSONArray.parseArray(callResponse.getValues());
            System.out.println(result);
            totalVoteIsYes = result.getBigInteger(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        CharityRaiseFund raiseFund = MPRaiseFundMapper.selectById(raiseId);
        List<String> nameList = Arrays.asList("参与人数", "总需金额", "完成金额", "提现金额", "投票数量");
        List<BigInteger> valueList = Arrays.asList(raiseFund.getTotalPeople(), raiseFund.getTotalAmount(), raiseFund.getOverAmount(), raiseFund.getWithdrawAmount(),totalVoteIsYes);

        ArrayList<RaiseFundDataVo> raiseFundDataVos = new ArrayList<>();
        for (int i = 0; i < nameList.size(); i++) {
            RaiseFundDataVo raiseFundDataVo = new RaiseFundDataVo();
            raiseFundDataVo.setId(i);
            raiseFundDataVo.setName(nameList.get(i));
            raiseFundDataVo.setValue(valueList.get(i));
            raiseFundDataVos.add(raiseFundDataVo);
        }
        return AjaxResult.success().put("data",raiseFundDataVos);
    }

    @Override
    public AjaxResult selectRaiseFundVotesList() {
        List<CharityRaiseFund> charityRaiseFundList = null;
        AjaxResult success = AjaxResult.success();
        charityRaiseFundList = redisCache.getCacheObject(CacheConstants.RAISE_FUND_WAIT_VOTES);
        if (charityRaiseFundList != null) {
            success.put("total",charityRaiseFundList.size());
            success.put("rows",charityRaiseFundList);
            return success;
        }
        charityRaiseFundList = MPRaiseFundMapper
                .selectList(Wrappers
                        .lambdaQuery(CharityRaiseFund.class)
                        .eq(CharityRaiseFund::getStatus, 2));
        redisCache.setCacheObject(CacheConstants.RAISE_FUND_WAIT_VOTES,charityRaiseFundList);
        success.put("total",charityRaiseFundList.size());
        success.put("rows",charityRaiseFundList);
        return success;
    }

    private static CharityControllerInitiateFundRaisingInputBO getRaisingInputBO(CharityRaiseFund charityRaiseFund, long startTime, long endTime) {
        CharityControllerInitiateFundRaisingInputBO fundRaisingInputBO = new CharityControllerInitiateFundRaisingInputBO();
        fundRaisingInputBO.set_title(charityRaiseFund.getTitle());
        fundRaisingInputBO.set_desc(charityRaiseFund.getDescription());
        fundRaisingInputBO.set_promoterAddress(charityRaiseFund.getPromoterAddress());
        fundRaisingInputBO.set_totalAmount(charityRaiseFund.getTotalAmount());
        fundRaisingInputBO.set_startTime(BigInteger.valueOf(startTime));
        fundRaisingInputBO.set_endTime(BigInteger.valueOf(endTime));
        return fundRaisingInputBO;
    }
}
