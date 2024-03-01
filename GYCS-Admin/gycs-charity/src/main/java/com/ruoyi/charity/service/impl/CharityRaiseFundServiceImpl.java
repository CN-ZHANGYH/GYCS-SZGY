package com.ruoyi.charity.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.charity.mapper.CharityRaiseFundMapper;
import com.ruoyi.charity.domain.dto.CharityRaiseFund;
import com.ruoyi.charity.service.ICharityRaiseFundService;

/**
 * 公益募资活动Service业务层处理
 *
 * @author zyh
 * @date 2024-02-01
 */
@Service
public class CharityRaiseFundServiceImpl implements ICharityRaiseFundService
{
    @Autowired
    private CharityRaiseFundMapper charityRaiseFundMapper;

    /**
     * 查询公益募资活动
     *
     * @param id 公益募资活动主键
     * @return 公益募资活动
     */
    @Override
    public CharityRaiseFund selectCharityRaiseFundById(Long id)
    {
        return charityRaiseFundMapper.selectCharityRaiseFundById(id);
    }

    /**
     * 查询公益募资活动列表
     *
     * @param charityRaiseFund 公益募资活动
     * @return 公益募资活动
     */
    @Override
    public List<CharityRaiseFund> selectCharityRaiseFundList(CharityRaiseFund charityRaiseFund)
    {
        return charityRaiseFundMapper.selectCharityRaiseFundList(charityRaiseFund);
    }

    /**
     * 新增公益募资活动
     *
     * @param charityRaiseFund 公益募资活动
     * @return 结果
     */
    @Override
    public int insertCharityRaiseFund(CharityRaiseFund charityRaiseFund)
    {
        charityRaiseFund.setCreateTime(DateUtils.getNowDate());
        return charityRaiseFundMapper.insertCharityRaiseFund(charityRaiseFund);
    }

    /**
     * 修改公益募资活动
     *
     * @param charityRaiseFund 公益募资活动
     * @return 结果
     */
    @Override
    public int updateCharityRaiseFund(CharityRaiseFund charityRaiseFund)
    {
        System.out.println(charityRaiseFund);
        return charityRaiseFundMapper.updateCharityRaiseFund(charityRaiseFund);
    }

    /**
     * 批量删除公益募资活动
     *
     * @param ids 需要删除的公益募资活动主键
     * @return 结果
     */
    @Override
    public int deleteCharityRaiseFundByIds(Long[] ids)
    {
        return charityRaiseFundMapper.deleteCharityRaiseFundByIds(ids);
    }

    /**
     * 删除公益募资活动信息
     *
     * @param id 公益募资活动主键
     * @return 结果
     */
    @Override
    public int deleteCharityRaiseFundById(Long id)
    {
        return charityRaiseFundMapper.deleteCharityRaiseFundById(id);
    }
}
