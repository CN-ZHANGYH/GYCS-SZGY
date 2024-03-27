package com.ruoyi.charity.mapper.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.charity.domain.dto.DonationTrace;
import com.ruoyi.charity.domain.vo.TransWeekVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zyh
 * @date 2024/3/4 9:55
 * @desc IntelliJ IDEA
 */

@Mapper
public interface MPDonationTraceMapper extends BaseMapper<DonationTrace> {


    @Select("SELECT\n" +
            "    CASE DAYOFWEEK(days.date)\n" +
            "        WHEN 2 THEN '周一'\n" +
            "        WHEN 3 THEN '周二'\n" +
            "        WHEN 4 THEN '周三'\n" +
            "        WHEN 5 THEN '周四'\n" +
            "        WHEN 6 THEN '周五'\n" +
            "        WHEN 7 THEN '周六'\n" +
            "        WHEN 1 THEN '周日'\n" +
            "    END AS week,\n" +
            "    COALESCE(SUM(cd.amount), 0) AS trans_amount,\n" +
            "    COALESCE(COUNT(cd.donation_id), 0) AS trans_total\n" +
            "FROM\n" +
            "    (SELECT DATE_ADD(CURDATE(), INTERVAL -WEEKDAY(CURDATE()) DAY) AS date\n" +
            "     UNION ALL SELECT DATE_ADD(CURDATE(), INTERVAL -WEEKDAY(CURDATE()) + 1 DAY)\n" +
            "     UNION ALL SELECT DATE_ADD(CURDATE(), INTERVAL -WEEKDAY(CURDATE()) + 2 DAY)\n" +
            "     UNION ALL SELECT DATE_ADD(CURDATE(), INTERVAL -WEEKDAY(CURDATE()) + 3 DAY)\n" +
            "     UNION ALL SELECT DATE_ADD(CURDATE(), INTERVAL -WEEKDAY(CURDATE()) + 4 DAY)\n" +
            "     UNION ALL SELECT DATE_ADD(CURDATE(), INTERVAL -WEEKDAY(CURDATE()) + 5 DAY)\n" +
            "     UNION ALL SELECT DATE_ADD(CURDATE(), INTERVAL -WEEKDAY(CURDATE()) + 6 DAY)) AS days\n" +
            "LEFT JOIN\n" +
            "    charity_donation cd ON DATE(cd.trans_time) = days.date\n" +
            "GROUP BY\n" +
            "    WEEK(days.date), week;")
    List<TransWeekVo> selectTransactionByWeek();
}
