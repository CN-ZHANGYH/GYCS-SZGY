package com.ruoyi.charity.service.impl.trace;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.charity.mapper.DonationTraceMapper;
import com.ruoyi.charity.domain.dto.DonationTrace;
import com.ruoyi.charity.service.trace.IDonationTraceService;

/**
 * 捐款信息记录Service业务层处理
 *
 * @author zyh
 * @date 2024-02-01
 */
@Service
public class DonationTraceServiceImpl implements IDonationTraceService
{
    @Autowired
    private DonationTraceMapper donationTraceMapper;

    /**
     * 查询捐款信息记录
     *
     * @param donationId 捐款信息记录主键
     * @return 捐款信息记录
     */
    @Override
    public DonationTrace selectDonationTraceByDonationId(Long donationId)
    {
        return donationTraceMapper.selectDonationTraceByDonationId(donationId);
    }

    /**
     * 查询捐款信息记录列表
     *
     * @param donationTrace 捐款信息记录
     * @return 捐款信息记录
     */
    @Override
    public List<DonationTrace> selectDonationTraceList(DonationTrace donationTrace)
    {
        return donationTraceMapper.selectDonationTraceList(donationTrace);
    }

    /**
     * 新增捐款信息记录
     *
     * @param donationTrace 捐款信息记录
     * @return 结果
     */
    @Override
    public int insertDonationTrace(DonationTrace donationTrace)
    {
        return donationTraceMapper.insertDonationTrace(donationTrace);
    }

    /**
     * 修改捐款信息记录
     *
     * @param donationTrace 捐款信息记录
     * @return 结果
     */
    @Override
    public int updateDonationTrace(DonationTrace donationTrace)
    {
        return donationTraceMapper.updateDonationTrace(donationTrace);
    }

    /**
     * 批量删除捐款信息记录
     *
     * @param donationIds 需要删除的捐款信息记录主键
     * @return 结果
     */
    @Override
    public int deleteDonationTraceByDonationIds(Long[] donationIds)
    {
        return donationTraceMapper.deleteDonationTraceByDonationIds(donationIds);
    }

    /**
     * 删除捐款信息记录信息
     *
     * @param donationId 捐款信息记录主键
     * @return 结果
     */
    @Override
    public int deleteDonationTraceByDonationId(Long donationId)
    {
        return donationTraceMapper.deleteDonationTraceByDonationId(donationId);
    }
}
