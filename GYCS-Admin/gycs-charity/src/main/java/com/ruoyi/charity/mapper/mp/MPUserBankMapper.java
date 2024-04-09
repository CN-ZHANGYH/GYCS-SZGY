package com.ruoyi.charity.mapper.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.charity.domain.dto.UserBankCard;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 张宇豪
 * @date 2024/3/25 10:59
 * @desc 用户绑定银行卡的Mapper类
 */

@Mapper
public interface MPUserBankMapper extends BaseMapper<UserBankCard> {
}
