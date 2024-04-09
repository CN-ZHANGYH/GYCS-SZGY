package com.ruoyi.charity.mapper.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.charity.domain.dto.CharityOrder;
import com.ruoyi.charity.domain.vo.OrderStatusVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface MPActivityOrderMapper extends BaseMapper<CharityOrder> {

    @Select("SELECT \n" +
            "    COUNT(*) AS total_orders,\n" +
            "    SUM(CASE WHEN is_sign = 1 THEN 1 ELSE 0 END) AS signed_orders,\n" +
            "    SUM(CASE WHEN is_sign = 0 THEN 1 ELSE 0 END) AS unsigned_orders,\n" +
            "    ROUND((SUM(CASE WHEN is_sign = 1 THEN 1 ELSE 0 END) / COUNT(*)) * 100) AS signed_percentage,\n" +
            "    ROUND((SUM(CASE WHEN is_sign = 0 THEN 1 ELSE 0 END) / COUNT(*)) * 100) AS unsigned_percentage\n" +
            "FROM \n" +
            "    charity_order\n" +
            "WHERE \n" +
            "    logistic_address = #{logAddress};")
    OrderStatusVo selectOrderProcessByLogistic(String logAddress);
}
