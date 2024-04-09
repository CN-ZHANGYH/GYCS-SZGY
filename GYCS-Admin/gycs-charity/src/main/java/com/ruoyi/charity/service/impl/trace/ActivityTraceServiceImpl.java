package com.ruoyi.charity.service.impl.trace;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.charity.domain.bo.CharityControllerDonatedMaterialsInputBO;
import com.ruoyi.charity.domain.dto.ActiviteTrace;
import com.ruoyi.charity.domain.dto.CharityOrder;
import com.ruoyi.charity.domain.dto.CharityUser;
import com.ruoyi.charity.domain.vo.MaterialInfoVo;
import com.ruoyi.charity.domain.vo.MessageResult;
import com.ruoyi.charity.mapper.mp.*;
import com.ruoyi.charity.service.impl.CharityControllerService;
import com.ruoyi.charity.service.trace.ActivityTraceService;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.bean.BeanUtils;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@Service
public class ActivityTraceServiceImpl implements ActivityTraceService {


    @Autowired
    private CharityControllerService charityControllerService;

    @Autowired
    private MPUserMapper mpUserMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MPOrgMapper mpOrgMapper;

    @Autowired
    private MPLogisticMapper mpLogisticMapper;

    @Autowired
    private MPActivityTraceMapper mpActivityTraceMapper;

    @Autowired
    private MPActivityOrderMapper mpActivityOrderMapper;

    @Override
    public AjaxResult donation(MaterialInfoVo materialInfoVo, String username) {

        CharityControllerDonatedMaterialsInputBO materialsInputBO = new CharityControllerDonatedMaterialsInputBO();
        BeanUtils.copyProperties(materialInfoVo,materialsInputBO);
        try
        {
            TransactionResponse transactionResponse = charityControllerService.donatedMaterials(materialsInputBO);
            if (transactionResponse.getReturnMessage().equals(CharityControllerService.SUCCESS))
            {
                // 获取交易哈希喝区块链高度
                String transactionHash = transactionResponse.getTransactionReceipt().getTransactionHash();
                int blockNumber = Integer.parseInt(transactionResponse.getTransactionReceipt().getBlockNumber().substring(2), 16);
                Integer traceId = JSONArray.parseArray(transactionResponse.getValues()).getIntValue(1);


                // 更新当前的用户信息
                CharityUser charityUser = mpUserMapper.selectOne(Wrappers.lambdaQuery(CharityUser.class).eq(CharityUser::getUsername, username));
                Integer oldCredit = charityUser.getCredit();
                charityUser.setCredit(oldCredit + 100);
                mpUserMapper.updateById(charityUser);

                // 添加当前的物流为订单
                CharityOrder charityOrder = new CharityOrder();
                charityOrder.setActivityId(materialInfoVo.get_activiteId());
                charityOrder.setTraceId(BigInteger.valueOf(traceId));
                charityOrder.setLogisticAddress(materialInfoVo.get_logisticAddress());
                charityOrder.setDestAddress(materialInfoVo.get_destAddress());
                charityOrder.setSourceAddress(materialInfoVo.get_userAddress());
                charityOrder.setIsSign(false);
                charityOrder.setOrderUuid(UUID.randomUUID().toString());
                charityOrder.setAmount(BigInteger.valueOf(0));
                charityOrder.setItemName(materialInfoVo.get_materialName());
                mpActivityOrderMapper.insert(charityOrder);


                HashMap<String, Object> dataMap = new HashMap<>();
                dataMap.put("transactionHash",transactionHash);
                dataMap.put("blockNumber",blockNumber);
                dataMap.put("traceId",traceId);
                dataMap.put("materialInfo",JSONObject.toJSONString(materialInfoVo));

                // 通过rabbitmq消息队列通知实现异步处理业务
                MessageResult messageResult = new MessageResult();
                messageResult.setCode(HttpStatus.SUCCESS);
                messageResult.setMessage("用户发起了物资捐赠请求");
                messageResult.setData(JSONObject.toJSONString(dataMap));
                rabbitTemplate.convertAndSend("direct_donation_material_exchange","ACTIVITY_DONATION_MATERIAL_KEY",JSONObject.toJSONString(messageResult));

                return AjaxResult.success().put("msg","用户捐赠物资请求成功");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.error().put("msg","用户捐赠物资失败");
    }

    @Override
    public AjaxResult selectUserMaterialOfRelationship(Integer activityId, String nickName) {
        // 查询当前的交易记录溯源的信息
        ActiviteTrace activiteTrace = mpActivityTraceMapper.selectOne(Wrappers.lambdaQuery(ActiviteTrace.class).eq(ActiviteTrace::getCharityId,activityId));

        String destAddress = activiteTrace.getDestAddress();
        String logisticAddress = activiteTrace.getLogisticAddress();
        List<String> orgAndLogisticName = mpOrgMapper.selectOrgNameAndLogisticName(destAddress, logisticAddress);
        orgAndLogisticName.add(nickName);

        return AjaxResult.success().put("data",orgAndLogisticName);

    }
}
