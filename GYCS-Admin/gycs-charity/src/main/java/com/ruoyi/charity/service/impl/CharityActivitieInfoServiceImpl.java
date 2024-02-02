package com.ruoyi.charity.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.charity.mapper.CharityActivitieInfoMapper;
import com.ruoyi.charity.domain.dto.CharityActivitieInfo;
import com.ruoyi.charity.service.ICharityActivitieInfoService;

/**
 * 公益灾区捐赠活动Service业务层处理
 *
 * @author zyh
 * @date 2024-02-01
 */
@Service
public class CharityActivitieInfoServiceImpl implements ICharityActivitieInfoService
{
    @Autowired
    private CharityActivitieInfoMapper charityActivitieInfoMapper;

    /**
     * 查询公益灾区捐赠活动
     *
     * @param id 公益灾区捐赠活动主键
     * @return 公益灾区捐赠活动
     */
    @Override
    public CharityActivitieInfo selectCharityActivitieInfoById(Long id)
    {
        return charityActivitieInfoMapper.selectCharityActivitieInfoById(id);
    }

    /**
     * 查询公益灾区捐赠活动列表
     *
     * @param charityActivitieInfo 公益灾区捐赠活动
     * @return 公益灾区捐赠活动
     */
    @Override
    public List<CharityActivitieInfo> selectCharityActivitieInfoList(CharityActivitieInfo charityActivitieInfo)
    {
        return charityActivitieInfoMapper.selectCharityActivitieInfoList(charityActivitieInfo);
    }

    /**
     * 新增公益灾区捐赠活动
     *
     * @param charityActivitieInfo 公益灾区捐赠活动
     * @return 结果
     */
    @Override
    public int insertCharityActivitieInfo(CharityActivitieInfo charityActivitieInfo)
    {
        charityActivitieInfo.setCreateTime(DateUtils.getNowDate());
        return charityActivitieInfoMapper.insertCharityActivitieInfo(charityActivitieInfo);
    }

    /**
     * 修改公益灾区捐赠活动
     *
     * @param charityActivitieInfo 公益灾区捐赠活动
     * @return 结果
     */
    @Override
    public int updateCharityActivitieInfo(CharityActivitieInfo charityActivitieInfo)
    {
        return charityActivitieInfoMapper.updateCharityActivitieInfo(charityActivitieInfo);
    }

    /**
     * 批量删除公益灾区捐赠活动
     *
     * @param ids 需要删除的公益灾区捐赠活动主键
     * @return 结果
     */
    @Override
    public int deleteCharityActivitieInfoByIds(Long[] ids)
    {
        return charityActivitieInfoMapper.deleteCharityActivitieInfoByIds(ids);
    }

    /**
     * 删除公益灾区捐赠活动信息
     *
     * @param id 公益灾区捐赠活动主键
     * @return 结果
     */
    @Override
    public int deleteCharityActivitieInfoById(Long id)
    {
        return charityActivitieInfoMapper.deleteCharityActivitieInfoById(id);
    }
}
