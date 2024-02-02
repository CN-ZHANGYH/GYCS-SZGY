package com.ruoyi.charity.mapper;

import java.util.List;
import com.ruoyi.charity.domain.dto.CharityActivitieInfo;

/**
 * 公益灾区捐赠活动Mapper接口
 *
 * @author zyh
 * @date 2024-02-01
 */
public interface CharityActivitieInfoMapper
{
    /**
     * 查询公益灾区捐赠活动
     *
     * @param id 公益灾区捐赠活动主键
     * @return 公益灾区捐赠活动
     */
    public CharityActivitieInfo selectCharityActivitieInfoById(Long id);

    /**
     * 查询公益灾区捐赠活动列表
     *
     * @param charityActivitieInfo 公益灾区捐赠活动
     * @return 公益灾区捐赠活动集合
     */
    public List<CharityActivitieInfo> selectCharityActivitieInfoList(CharityActivitieInfo charityActivitieInfo);

    /**
     * 新增公益灾区捐赠活动
     *
     * @param charityActivitieInfo 公益灾区捐赠活动
     * @return 结果
     */
    public int insertCharityActivitieInfo(CharityActivitieInfo charityActivitieInfo);

    /**
     * 修改公益灾区捐赠活动
     *
     * @param charityActivitieInfo 公益灾区捐赠活动
     * @return 结果
     */
    public int updateCharityActivitieInfo(CharityActivitieInfo charityActivitieInfo);

    /**
     * 删除公益灾区捐赠活动
     *
     * @param id 公益灾区捐赠活动主键
     * @return 结果
     */
    public int deleteCharityActivitieInfoById(Long id);

    /**
     * 批量删除公益灾区捐赠活动
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCharityActivitieInfoByIds(Long[] ids);
}
