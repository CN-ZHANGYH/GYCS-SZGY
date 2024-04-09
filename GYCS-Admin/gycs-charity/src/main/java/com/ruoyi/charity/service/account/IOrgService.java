package com.ruoyi.charity.service.account;

import java.util.List;
import com.ruoyi.charity.domain.dto.Org;

/**
 * 机构用户信息Service接口
 *
 * @author zyh
 * @date 2024-01-31
 */
public interface IOrgService
{
    /**
     * 查询机构用户信息
     *
     * @param id 机构用户信息主键
     * @return 机构用户信息
     */
    public Org selectOrgById(Long id);

    /**
     * 查询机构用户信息列表
     *
     * @param org 机构用户信息
     * @return 机构用户信息集合
     */
    public List<Org> selectOrgList(Org org);

    /**
     * 新增机构用户信息
     *
     * @param org 机构用户信息
     * @return 结果
     */
    public int insertOrg(Org org);

    /**
     * 修改机构用户信息
     *
     * @param org 机构用户信息
     * @return 结果
     */
    public int updateOrg(Org org);

    /**
     * 批量删除机构用户信息
     *
     * @param ids 需要删除的机构用户信息主键集合
     * @return 结果
     */
    public int deleteOrgByIds(Long[] ids);

    /**
     * 删除机构用户信息信息
     *
     * @param id 机构用户信息主键
     * @return 结果
     */
    public int deleteOrgById(Long id);
}
