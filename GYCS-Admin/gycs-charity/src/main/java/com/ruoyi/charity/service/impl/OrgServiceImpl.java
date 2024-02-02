package com.ruoyi.charity.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.charity.mapper.OrgMapper;
import com.ruoyi.charity.domain.dto.Org;
import com.ruoyi.charity.service.IOrgService;

/**
 * 机构用户信息Service业务层处理
 *
 * @author zyh
 * @date 2024-01-31
 */
@Service
public class OrgServiceImpl implements IOrgService
{
    @Autowired
    private OrgMapper orgMapper;

    /**
     * 查询机构用户信息
     *
     * @param id 机构用户信息主键
     * @return 机构用户信息
     */
    @Override
    public Org selectOrgById(Long id)
    {
        return orgMapper.selectOrgById(id);
    }

    /**
     * 查询机构用户信息列表
     *
     * @param org 机构用户信息
     * @return 机构用户信息
     */
    @Override
    public List<Org> selectOrgList(Org org)
    {
        return orgMapper.selectOrgList(org);
    }

    /**
     * 新增机构用户信息
     *
     * @param org 机构用户信息
     * @return 结果
     */
    @Override
    public int insertOrg(Org org)
    {
        return orgMapper.insertOrg(org);
    }

    /**
     * 修改机构用户信息
     *
     * @param org 机构用户信息
     * @return 结果
     */
    @Override
    public int updateOrg(Org org)
    {
        return orgMapper.updateOrg(org);
    }

    /**
     * 批量删除机构用户信息
     *
     * @param ids 需要删除的机构用户信息主键
     * @return 结果
     */
    @Override
    public int deleteOrgByIds(Long[] ids)
    {
        return orgMapper.deleteOrgByIds(ids);
    }

    /**
     * 删除机构用户信息信息
     *
     * @param id 机构用户信息主键
     * @return 结果
     */
    @Override
    public int deleteOrgById(Long id)
    {
        return orgMapper.deleteOrgById(id);
    }
}
