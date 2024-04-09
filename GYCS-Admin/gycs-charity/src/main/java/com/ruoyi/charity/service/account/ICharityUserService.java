package com.ruoyi.charity.service.account;

import java.util.List;
import com.ruoyi.charity.domain.dto.CharityUser;

/**
 * 用户Service接口
 * 
 * @author zyh
 * @date 2024-02-27
 */
public interface ICharityUserService 
{
    /**
     * 查询用户
     * 
     * @param id 用户主键
     * @return 用户
     */
    public CharityUser selectCharityUserById(Long id);

    /**
     * 查询用户列表
     * 
     * @param charityUser 用户
     * @return 用户集合
     */
    public List<CharityUser> selectCharityUserList(CharityUser charityUser);

    /**
     * 新增用户
     * 
     * @param charityUser 用户
     * @return 结果
     */
    public int insertCharityUser(CharityUser charityUser);

    /**
     * 修改用户
     * 
     * @param charityUser 用户
     * @return 结果
     */
    public int updateCharityUser(CharityUser charityUser);

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的用户主键集合
     * @return 结果
     */
    public int deleteCharityUserByIds(Long[] ids);

    /**
     * 删除用户信息
     * 
     * @param id 用户主键
     * @return 结果
     */
    public int deleteCharityUserById(Long id);
}
