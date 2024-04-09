package com.ruoyi.charity.service.impl.account;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.charity.mapper.CharityUserMapper;
import com.ruoyi.charity.domain.dto.CharityUser;
import com.ruoyi.charity.service.account.ICharityUserService;

/**
 * 用户Service业务层处理
 * 
 * @author zyh
 * @date 2024-02-27
 */
@Service
public class CharityUserServiceImpl implements ICharityUserService 
{
    @Autowired
    private CharityUserMapper charityUserMapper;

    /**
     * 查询用户
     * 
     * @param id 用户主键
     * @return 用户
     */
    @Override
    public CharityUser selectCharityUserById(Long id)
    {
        return charityUserMapper.selectCharityUserById(id);
    }

    /**
     * 查询用户列表
     * 
     * @param charityUser 用户
     * @return 用户
     */
    @Override
    public List<CharityUser> selectCharityUserList(CharityUser charityUser)
    {
        return charityUserMapper.selectCharityUserList(charityUser);
    }

    /**
     * 新增用户
     * 
     * @param charityUser 用户
     * @return 结果
     */
    @Override
    public int insertCharityUser(CharityUser charityUser)
    {
        return charityUserMapper.insertCharityUser(charityUser);
    }

    /**
     * 修改用户
     * 
     * @param charityUser 用户
     * @return 结果
     */
    @Override
    public int updateCharityUser(CharityUser charityUser)
    {
        return charityUserMapper.updateCharityUser(charityUser);
    }

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的用户主键
     * @return 结果
     */
    @Override
    public int deleteCharityUserByIds(Long[] ids)
    {
        return charityUserMapper.deleteCharityUserByIds(ids);
    }

    /**
     * 删除用户信息
     * 
     * @param id 用户主键
     * @return 结果
     */
    @Override
    public int deleteCharityUserById(Long id)
    {
        return charityUserMapper.deleteCharityUserById(id);
    }
}
