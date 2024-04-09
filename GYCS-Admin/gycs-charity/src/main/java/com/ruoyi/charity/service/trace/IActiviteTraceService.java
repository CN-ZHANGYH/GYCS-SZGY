package com.ruoyi.charity.service.trace;

import java.util.List;
import com.ruoyi.charity.domain.dto.ActiviteTrace;

/**
 * 公益活动溯源记录Service接口
 *
 * @author zyh
 * @date 2024-02-01
 */
public interface IActiviteTraceService
{
    /**
     * 查询公益活动溯源记录
     *
     * @param charityId 公益活动溯源记录主键
     * @return 公益活动溯源记录
     */
    public ActiviteTrace selectActiviteTraceByCharityId(Long charityId);

    /**
     * 查询公益活动溯源记录列表
     *
     * @param activiteTrace 公益活动溯源记录
     * @return 公益活动溯源记录集合
     */
    public List<ActiviteTrace> selectActiviteTraceList(ActiviteTrace activiteTrace);

    /**
     * 新增公益活动溯源记录
     *
     * @param activiteTrace 公益活动溯源记录
     * @return 结果
     */
    public int insertActiviteTrace(ActiviteTrace activiteTrace);

    /**
     * 修改公益活动溯源记录
     *
     * @param activiteTrace 公益活动溯源记录
     * @return 结果
     */
    public int updateActiviteTrace(ActiviteTrace activiteTrace);

    /**
     * 批量删除公益活动溯源记录
     *
     * @param charityIds 需要删除的公益活动溯源记录主键集合
     * @return 结果
     */
    public int deleteActiviteTraceByCharityIds(Long[] charityIds);

    /**
     * 删除公益活动溯源记录信息
     *
     * @param charityId 公益活动溯源记录主键
     * @return 结果
     */
    public int deleteActiviteTraceByCharityId(Long charityId);
}
