package com.ruoyi.charity.mapper;

import java.util.List;
import com.ruoyi.charity.domain.dto.CharityUser;

/**
 * 用户信息表Mapper接口
 *
 * @author zyh
 * @date 2024-02-01
 */
public interface CharityUserMapper
{
    /**
     * 查询用户信息表
     *
     * @param id 用户信息表主键
     * @return 用户信息表
     */
    public CharityUser selectCharityUserById(Long id);

    /**
     * 查询用户信息表列表
     *
     * @param charityUser 用户信息表
     * @return 用户信息表集合
     */
    public List<CharityUser> selectCharityUserList(CharityUser charityUser);

    /**
     * 新增用户信息表
     *
     * @param charityUser 用户信息表
     * @return 结果
     */
    public int insertCharityUser(CharityUser charityUser);

    /**
     * 修改用户信息表
     *
     * @param charityUser 用户信息表
     * @return 结果
     */
    public int updateCharityUser(CharityUser charityUser);

    /**
     * 删除用户信息表
     *
     * @param id 用户信息表主键
     * @return 结果
     */
    public int deleteCharityUserById(Long id);

    /**
     * 批量删除用户信息表
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCharityUserByIds(Long[] ids);
}
