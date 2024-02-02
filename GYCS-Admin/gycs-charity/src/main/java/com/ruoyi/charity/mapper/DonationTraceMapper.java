package com.ruoyi.charity.mapper;

import java.util.List;
import com.ruoyi.charity.domain.dto.DonationTrace;

/**
 * 捐款信息记录Mapper接口
 *
 * @author zyh
 * @date 2024-02-01
 */
public interface DonationTraceMapper
{
    /**
     * 查询捐款信息记录
     *
     * @param donationId 捐款信息记录主键
     * @return 捐款信息记录
     */
    public DonationTrace selectDonationTraceByDonationId(Long donationId);

    /**
     * 查询捐款信息记录列表
     *
     * @param donationTrace 捐款信息记录
     * @return 捐款信息记录集合
     */
    public List<DonationTrace> selectDonationTraceList(DonationTrace donationTrace);

    /**
     * 新增捐款信息记录
     *
     * @param donationTrace 捐款信息记录
     * @return 结果
     */
    public int insertDonationTrace(DonationTrace donationTrace);

    /**
     * 修改捐款信息记录
     *
     * @param donationTrace 捐款信息记录
     * @return 结果
     */
    public int updateDonationTrace(DonationTrace donationTrace);

    /**
     * 删除捐款信息记录
     *
     * @param donationId 捐款信息记录主键
     * @return 结果
     */
    public int deleteDonationTraceByDonationId(Long donationId);

    /**
     * 批量删除捐款信息记录
     *
     * @param donationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDonationTraceByDonationIds(Long[] donationIds);
}
