package com.ruoyi.charity.service.raise;

import java.util.List;
import com.ruoyi.charity.domain.dto.CharityRaiseFund;

/**
 * 公益募资活动Service接口
 *
 * @author zyh
 * @date 2024-02-01
 */
public interface ICharityRaiseFundService
{
    /**
     * 查询公益募资活动
     *
     * @param id 公益募资活动主键
     * @return 公益募资活动
     */
    public CharityRaiseFund selectCharityRaiseFundById(Long id);

    /**
     * 查询公益募资活动列表
     *
     * @param charityRaiseFund 公益募资活动
     * @param userId
     * @return 公益募资活动集合
     */
    public List<CharityRaiseFund> selectCharityRaiseFundList(CharityRaiseFund charityRaiseFund,Long userId);

    /**
     * 新增公益募资活动
     *
     * @param charityRaiseFund 公益募资活动
     * @return 结果
     */
    public int insertCharityRaiseFund(CharityRaiseFund charityRaiseFund);

    /**
     * 修改公益募资活动
     *
     * @param charityRaiseFund 公益募资活动
     * @return 结果
     */
    public int updateCharityRaiseFund(CharityRaiseFund charityRaiseFund);

    /**
     * 批量删除公益募资活动
     *
     * @param ids 需要删除的公益募资活动主键集合
     * @return 结果
     */
    public int deleteCharityRaiseFundByIds(Long[] ids);

    /**
     * 删除公益募资活动信息
     *
     * @param id 公益募资活动主键
     * @return 结果
     */
    public int deleteCharityRaiseFundById(Long id);
}
