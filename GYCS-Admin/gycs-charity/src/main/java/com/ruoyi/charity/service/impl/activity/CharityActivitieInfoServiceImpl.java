package com.ruoyi.charity.service.impl.activity;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.charity.domain.dto.Org;
import com.ruoyi.charity.mapper.mp.MPOrgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.charity.mapper.CharityActivitieInfoMapper;
import com.ruoyi.charity.domain.dto.CharityActivitieInfo;
import com.ruoyi.charity.service.activity.ICharityActivitieInfoService;

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


    @Autowired
    private MPOrgMapper mpOrgMapper;

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
     * @param userId
     * @return 公益灾区捐赠活动
     */
    @Override
    public List<CharityActivitieInfo> selectCharityActivitieInfoList(CharityActivitieInfo charityActivitieInfo, Long userId)
    {

        Org org = mpOrgMapper.selectOne(Wrappers.lambdaQuery(Org.class).eq(Org::getId, userId));
        List<CharityActivitieInfo> charityActivitieInfos = charityActivitieInfoMapper.selectCharityActivitieInfoList(charityActivitieInfo);
        List<CharityActivitieInfo> activitieInfoList = charityActivitieInfos.stream()
                .filter(charityActivitieInfoItem -> charityActivitieInfoItem.getLncomeAddress().equals(org.getOrgAddress()))
                .collect(Collectors.toList());
        return activitieInfoList;
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
