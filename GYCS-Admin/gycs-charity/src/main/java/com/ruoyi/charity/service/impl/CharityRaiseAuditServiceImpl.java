package com.ruoyi.charity.service.impl;

import java.util.List;
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
