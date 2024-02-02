package com.ruoyi.charity.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.charity.mapper.ActiviteTraceMapper;
import com.ruoyi.charity.domain.dto.ActiviteTrace;
import com.ruoyi.charity.service.IActiviteTraceService;

/**
 * 公益活动溯源记录Service业务层处理
 *
 * @author zyh
 * @date 2024-02-01
 */
@Service
public class ActiviteTraceServiceImpl implements IActiviteTraceService
{
    @Autowired
    private ActiviteTraceMapper activiteTraceMapper;

    /**
     * 查询公益活动溯源记录
     *
     * @param charityId 公益活动溯源记录主键
     * @return 公益活动溯源记录
     */
    @Override
    public ActiviteTrace selectActiviteTraceByCharityId(Long charityId)
    {
        return activiteTraceMapper.selectActiviteTraceByCharityId(charityId);
    }

    /**
     * 查询公益活动溯源记录列表
     *
     * @param activiteTrace 公益活动溯源记录
     * @return 公益活动溯源记录
     */
    @Override
    public List<ActiviteTrace> selectActiviteTraceList(ActiviteTrace activiteTrace)
    {
        return activiteTraceMapper.selectActiviteTraceList(activiteTrace);
    }

    /**
     * 新增公益活动溯源记录
     *
     * @param activiteTrace 公益活动溯源记录
     * @return 结果
     */
    @Override
    public int insertActiviteTrace(ActiviteTrace activiteTrace)
    {
        return activiteTraceMapper.insertActiviteTrace(activiteTrace);
    }

    /**
     * 修改公益活动溯源记录
     *
     * @param activiteTrace 公益活动溯源记录
     * @return 结果
     */
    @Override
    public int updateActiviteTrace(ActiviteTrace activiteTrace)
    {
        return activiteTraceMapper.updateActiviteTrace(activiteTrace);
    }

    /**
     * 批量删除公益活动溯源记录
     *
     * @param charityIds 需要删除的公益活动溯源记录主键
     * @return 结果
     */
    @Override
    public int deleteActiviteTraceByCharityIds(Long[] charityIds)
    {
        return activiteTraceMapper.deleteActiviteTraceByCharityIds(charityIds);
    }

    /**
     * 删除公益活动溯源记录信息
     *
     * @param charityId 公益活动溯源记录主键
     * @return 结果
     */
    @Override
    public int deleteActiviteTraceByCharityId(Long charityId)
    {
        return activiteTraceMapper.deleteActiviteTraceByCharityId(charityId);
    }
}
