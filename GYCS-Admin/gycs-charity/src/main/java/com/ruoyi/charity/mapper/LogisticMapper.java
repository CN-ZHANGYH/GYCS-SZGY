package com.ruoyi.charity.mapper;

import com.ruoyi.charity.domain.dto.Logistic;

import java.util.List;


/**
 * 物流商用户信息Mapper接口
 *
 * @author zyh
 * @date 2024-02-01
 */
public interface LogisticMapper
{
    /**
     * 查询物流商用户信息
     *
     * @param id 物流商用户信息主键
     * @return 物流商用户信息
     */
    public Logistic selectLogisticById(Long id);

    /**
     * 查询物流商用户信息列表
     *
     * @param logistic 物流商用户信息
     * @return 物流商用户信息集合
     */
    public List<Logistic> selectLogisticList(Logistic logistic);

    /**
     * 新增物流商用户信息
     *
     * @param logistic 物流商用户信息
     * @return 结果
     */
    public int insertLogistic(Logistic logistic);

    /**
     * 修改物流商用户信息
     *
     * @param logistic 物流商用户信息
     * @return 结果
     */
    public int updateLogistic(Logistic logistic);

    /**
     * 删除物流商用户信息
     *
     * @param id 物流商用户信息主键
     * @return 结果
     */
    public int deleteLogisticById(Long id);

    /**
     * 批量删除物流商用户信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLogisticByIds(Long[] ids);
}
