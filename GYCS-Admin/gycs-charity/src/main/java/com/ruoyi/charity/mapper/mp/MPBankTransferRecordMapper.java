package com.ruoyi.charity.mapper.mp;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.charity.domain.dto.BankTransferRecord;
import com.ruoyi.charity.domain.vo.BankTransferDataView;
import com.ruoyi.common.core.domain.AjaxResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MPBankTransferRecordMapper extends BaseMapper<BankTransferRecord> {

    @Select("SELECT\n" +
            "    t.day_of_week,\n" +
            "    COALESCE(transaction_count, 0) AS transaction_count,\n" +
            "    COALESCE(transaction_amount, 0) AS transaction_amount\n" +
            "FROM\n" +
            "    (\n" +
            "        SELECT 1 AS day_of_week\n" +
            "        UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5\n" +
            "        UNION SELECT 6 UNION SELECT 7\n" +
            "    ) AS t\n" +
            "LEFT JOIN\n" +
            "    (\n" +
            "        SELECT\n" +
            "            DAYOFWEEK(trans_time) AS day_of_week,\n" +
            "            COUNT(*) AS transaction_count,\n" +
            "            SUM(amount) AS transaction_amount\n" +
            "        FROM\n" +
            "            charity_bank_transfer_record\n" +
            "        WHERE\n" +
            "            raise_id = #{raiseId}\n" +
            "            AND trans_time >= DATE_SUB(CURRENT_DATE(), INTERVAL 1 WEEK)\n" +
            "        GROUP BY\n" +
            "            DAYOFWEEK(trans_time)\n" +
            "    ) AS subquery\n" +
            "ON\n" +
            "    t.day_of_week = subquery.day_of_week\n" +
            "ORDER BY\n" +
            "    t.day_of_week;\n")
    public List<BankTransferDataView> selectBankTransferRecordByWeek(@Param("raiseId") Integer raiseId);
}
