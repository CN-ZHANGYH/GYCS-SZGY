package com.ruoyi.charity.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.charity.domain.vo.MessageResult;
import com.ruoyi.common.constant.HttpStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.charity.mapper.CharityRaiseAuditMapper;
import com.ruoyi.charity.domain.dto.CharityRaiseAudit;
import com.ruoyi.charity.service.ICharityRaiseAuditService;

/**
 * 审核Service业务层处理
 *
 * @author zyh
 * @date 2024-02-28
 */
@Service
public class CharityRaiseAuditServiceImpl implements ICharityRaiseAuditService
{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CharityRaiseAuditMapper charityRaiseAuditMapper;

    /**
     * 查询审核
     *
     * @param id 审核主键
     * @return 审核
     */
    @Override
    public CharityRaiseAudit selectCharityRaiseAuditById(Long id)
    {
        return charityRaiseAuditMapper.selectCharityRaiseAuditById(id);
    }

    /**
     * 查询审核列表
     *
     * @param charityRaiseAudit 审核
     * @return 审核
     */
    @Override
    public List<CharityRaiseAudit> selectCharityRaiseAuditList(CharityRaiseAudit charityRaiseAudit)
    {
        return charityRaiseAuditMapper.selectCharityRaiseAuditList(charityRaiseAudit);
    }

    /**
     * 新增审核
     *
     * @param charityRaiseAudit 审核
     * @return 结果
     */
    @Override
    public int insertCharityRaiseAudit(CharityRaiseAudit charityRaiseAudit)
    {
        return charityRaiseAuditMapper.insertCharityRaiseAudit(charityRaiseAudit);
    }

    /**
     * 修改审核
     *
     * @param charityRaiseAudit 审核
     * @return 结果
     */
    @Override
    public int updateCharityRaiseAudit(CharityRaiseAudit charityRaiseAudit)
    {
        // 如果审核不通过 需要使用rabbitmq进行消息通知 异步实现对当前的用户的链上活动关闭
        if (charityRaiseAudit.getApplyStatus() == 1) {
            MessageResult messageResult = new MessageResult();
            messageResult.setCode(HttpStatus.SUCCESS);
            messageResult.setMessage("审核不通过");
            messageResult.setData(JSONObject.toJSONString(charityRaiseAudit));

            // 封装消费者的消息并发送
            String message = JSONObject.toJSONString(messageResult);
            rabbitTemplate.convertAndSend("direct_raise_audit_exchange", "RAISE_AUDIT_ROUTING_KEY", message);
        }
        return charityRaiseAuditMapper.updateCharityRaiseAudit(charityRaiseAudit);
    }

    /**
     * 批量删除审核
     *
     * @param ids 需要删除的审核主键
     * @return 结果
     */
    @Override
    public int deleteCharityRaiseAuditByIds(Long[] ids)
    {
        return charityRaiseAuditMapper.deleteCharityRaiseAuditByIds(ids);
    }

    /**
     * 删除审核信息
     *
     * @param id 审核主键
     * @return 结果
     */
    @Override
    public int deleteCharityRaiseAuditById(Long id)
    {
        return charityRaiseAuditMapper.deleteCharityRaiseAuditById(id);
    }
}
