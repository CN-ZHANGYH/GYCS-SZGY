package com.ruoyi.charity.mapper.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.charity.domain.dto.Org;
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
}
