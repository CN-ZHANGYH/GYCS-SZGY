package com.ruoyi.charity.mapper.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.charity.domain.dto.CharityUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author zyh
 * @date 2024/3/4 15:58
 * @desc IntelliJ IDEA
 */

@Mapper
public interface MPUserMapper extends BaseMapper<CharityUser> {

    @Select("SELECT su.email FROM `charity_user` LEFT JOIN sys_user as su ON charity_user.id = su.user_id WHERE charity_user.user_address = #{promoterAddress} ")
    String selectUserEmailByUserAddress(String promoterAddress);
}
