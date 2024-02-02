package com.ruoyi.charity.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.charity.mapper.CharityUserMapper;
import com.ruoyi.charity.domain.dto.CharityUser;
import com.ruoyi.charity.service.ICharityUserService;

/**
 * 用户信息表Service业务层处理
 *
 * @author zyh
 * @date 2024-02-01
 */
@Service
public class CharityUserServiceImpl implements ICharityUserService
{
    @Autowired
    private CharityUserMapper charityUserMapper;

    /**
     * 查询用户信息表
     *
     * @param id 用户信息表主键
     * @return 用户信息表
     */
    @Override
    public CharityUser selectCharityUserById(Long id)
    {
        return charityUserMapper.selectCharityUserById(id);
    }

    /**
     * 查询用户信息表列表
     *
     * @param charityUser 用户信息表
     * @return 用户信息表
     */
    @Override
    public List<CharityUser> selectCharityUserList(CharityUser charityUser)
    {
        return charityUserMapper.selectCharityUserList(charityUser);
    }

    /**
     * 新增用户信息表
     *
     * @param charityUser 用户信息表
     * @return 结果
     */
    @Override
    public int insertCharityUser(CharityUser charityUser)
    {
        return charityUserMapper.insertCharityUser(charityUser);
    }

    /**
     * 修改用户信息表
     *
     * @param charityUser 用户信息表
     * @return 结果
     */
    @Override
    public int updateCharityUser(CharityUser charityUser)
    {
        return charityUserMapper.updateCharityUser(charityUser);
    }

    /**
     * 批量删除用户信息表
     *
     * @param ids 需要删除的用户信息表主键
     * @return 结果
     */
    @Override
    public int deleteCharityUserByIds(Long[] ids)
    {
        return charityUserMapper.deleteCharityUserByIds(ids);
    }

    /**
     * 删除用户信息表信息
     *
     * @param id 用户信息表主键
     * @return 结果
     */
    @Override
    public int deleteCharityUserById(Long id)
    {
        return charityUserMapper.deleteCharityUserById(id);
    }
}
