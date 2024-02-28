package com.ruoyi.charity.mapper;

import java.util.List;
import com.ruoyi.charity.domain.CharityRaiseAudit;

/**
 * 审核Mapper接口
 * 
 * @author zyh
 * @date 2024-02-28
 */
public interface CharityRaiseAuditMapper 
{
    /**
     * 查询审核
     * 
     * @param id 审核主键
     * @return 审核
     */
    public CharityRaiseAudit selectCharityRaiseAuditById(Long id);

    /**
     * 查询审核列表
     * 
     * @param charityRaiseAudit 审核
     * @return 审核集合
     */
    public List<CharityRaiseAudit> selectCharityRaiseAuditList(CharityRaiseAudit charityRaiseAudit);

    /**
     * 新增审核
     * 
     * @param charityRaiseAudit 审核
     * @return 结果
     */
    public int insertCharityRaiseAudit(CharityRaiseAudit charityRaiseAudit);

    /**
     * 修改审核
     * 
     * @param charityRaiseAudit 审核
     * @return 结果
     */
    public int updateCharityRaiseAudit(CharityRaiseAudit charityRaiseAudit);

    /**
     * 删除审核
     * 
     * @param id 审核主键
     * @return 结果
     */
    public int deleteCharityRaiseAuditById(Long id);

    /**
     * 批量删除审核
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCharityRaiseAuditByIds(Long[] ids);
}
