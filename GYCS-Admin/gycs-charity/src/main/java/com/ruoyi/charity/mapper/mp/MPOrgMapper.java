package com.ruoyi.charity.mapper.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.charity.domain.dto.Org;
import com.ruoyi.charity.domain.vo.OrderDataVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zyh
 * @date 2024/3/17 22:48
 * @desc IntelliJ IDEA
 */

@Mapper
public interface MPOrgMapper extends BaseMapper<Org> {

    @Select("SELECT charity_org.org_name FROM charity_org WHERE charity_org.org_address = #{orgAddress}\n" +
            "UNION\n" +
            "SELECT charity_logistic.log_name FROM charity_logistic WHERE charity_logistic.log_address = #{logAddress}")
    public List<String> selectOrgNameAndLogisticName(@Param("orgAddress") String orgAddress,@Param("logAddress") String logAddress);


    @Select("SELECT \n" +
            "    DATE_FORMAT(date_range.date, '%m-%d') AS count_day,\n" +
            "    COALESCE(SUM(CASE WHEN co.is_sign = 0 THEN 1 ELSE 0 END), 0) AS count_delivery,\n" +
            "    COALESCE(SUM(CASE WHEN co.is_sign = 1 THEN 1 ELSE 0 END), 0) AS count_sign,\n" +
            "    CASE \n" +
            "        WHEN COALESCE(SUM(CASE WHEN co.is_sign = 0 THEN 1 ELSE 0 END), 0) + COALESCE(SUM(CASE WHEN co.is_sign = 1 THEN 1 ELSE 0 END), 0) = 0 THEN 0\n" +
            "        ELSE (LEAST(COALESCE(SUM(CASE WHEN co.is_sign = 0 THEN 1 ELSE 0 END), 0), COALESCE(SUM(CASE WHEN co.is_sign = 1 THEN 1 ELSE 0 END), 0))) -- 取发货数和签收数的中间值\n" +
            "    END AS count_rate\n" +
            "FROM \n" +
            "    (SELECT DATE_FORMAT(DATE_ADD(DATE_FORMAT(NOW(), '%Y-%m-01'), INTERVAL (t0.i + t1.i*10) DAY), '%Y-%m-%d') AS date\n" +
            "     FROM \n" +
            "        (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) AS t0,\n" +
            "        (SELECT 0 AS i UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) AS t1\n" +
            "    ) AS date_range\n" +
            "LEFT JOIN \n" +
            "    charity_order co ON DATE(co.create_time) = date_range.date\n" +
            "        AND co.source_address = #{address} -- 移动过滤条件到 LEFT JOIN 的 ON 子句中\n" +
            "WHERE \n" +
            "    date_range.date >= DATE_FORMAT(NOW(), '%Y-%m-01') -- 当月第一天\n" +
            "    AND date_range.date < DATE_FORMAT(DATE_ADD(NOW(), INTERVAL 1 MONTH), '%Y-%m-01') -- 下个月第一天\n" +
            "GROUP BY \n" +
            "    date_range.date\n" +
            "ORDER BY \n" +
            "    date_range.date;\n")
    List<OrderDataVo> selectUserOrderStatusByMonth(String address);
}
