package com.ruoyi.charity.service.impl;

import java.util.List;

import com.ruoyi.charity.domain.dto.Logistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.charity.mapper.LogisticMapper;
import com.ruoyi.charity.service.ILogisticService;

/**
 * 物流商用户信息Service业务层处理
 *
 * @author zyh
 * @date 2024-02-01
 */
@Service
public class LogisticServiceImpl implements ILogisticService
{
    @Autowired
    private LogisticMapper logisticMapper;

    /**
     * 查询物流商用户信息
     *
     * @param id 物流商用户信息主键
     * @return 物流商用户信息
     */
    @Override
    public Logistic selectLogisticById(Long id)
    {
        return logisticMapper.selectLogisticById(id);
    }

    /**
     * 查询物流商用户信息列表
     *
     * @param logistic 物流商用户信息
     * @return 物流商用户信息
     */
    @Override
    public List<Logistic> selectLogisticList(Logistic logistic)
    {
        return logisticMapper.selectLogisticList(logistic);
    }

    /**
     * 新增物流商用户信息
     *
     * @param logistic 物流商用户信息
     * @return 结果
     */
    @Override
    public int insertLogistic(Logistic logistic)
    {
        return logisticMapper.insertLogistic(logistic);
    }

    /**
     * 修改物流商用户信息
     *
     * @param logistic 物流商用户信息
     * @return 结果
     */
    @Override
    public int updateLogistic(Logistic logistic)
    {
        return logisticMapper.updateLogistic(logistic);
    }

    /**
     * 批量删除物流商用户信息
     *
     * @param ids 需要删除的物流商用户信息主键
     * @return 结果
     */
    @Override
    public int deleteLogisticByIds(Long[] ids)
    {
        return logisticMapper.deleteLogisticByIds(ids);
    }

    /**
     * 删除物流商用户信息信息
     *
     * @param id 物流商用户信息主键
     * @return 结果
     */
    @Override
    public int deleteLogisticById(Long id)
    {
        return logisticMapper.deleteLogisticById(id);
    }
}
