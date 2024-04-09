package com.ruoyi.web.controller.charity.backend;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.charity.domain.dto.CharityActivitieInfo;
import com.ruoyi.charity.service.activity.ICharityActivitieInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公益灾区捐赠活动Controller
 *
 * @author zyh
 * @date 2024-02-01
 */
@RestController
@RequestMapping("/charity/activitieInfo")
public class CharityActivitieInfoController extends BaseController
{
    @Autowired
    private ICharityActivitieInfoService charityActivitieInfoService;

    /**
     * 查询公益灾区捐赠活动列表
     */
    @PreAuthorize("@ss.hasPermi('charity:activitieInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(CharityActivitieInfo charityActivitieInfo)
    {
        Long userId = SecurityUtils.getLoginUser().getUserId();
        startPage();
        List<CharityActivitieInfo> list = charityActivitieInfoService.selectCharityActivitieInfoList(charityActivitieInfo,userId);
        return getDataTable(list);
    }

    /**
     * 导出公益灾区捐赠活动列表
     */
    @PreAuthorize("@ss.hasPermi('charity:activitieInfo:export')")
    @Log(title = "公益灾区捐赠活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CharityActivitieInfo charityActivitieInfo)
    {
        Long userId = SecurityUtils.getLoginUser().getUserId();
        List<CharityActivitieInfo> list = charityActivitieInfoService.selectCharityActivitieInfoList(charityActivitieInfo,userId);
        ExcelUtil<CharityActivitieInfo> util = new ExcelUtil<CharityActivitieInfo>(CharityActivitieInfo.class);
        util.exportExcel(response, list, "公益灾区捐赠活动数据");
    }

    /**
     * 获取公益灾区捐赠活动详细信息
     */
    @PreAuthorize("@ss.hasPermi('charity:activitieInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(charityActivitieInfoService.selectCharityActivitieInfoById(id));
    }

    /**
     * 新增公益灾区捐赠活动
     */
    @PreAuthorize("@ss.hasPermi('charity:activitieInfo:add')")
    @Log(title = "公益灾区捐赠活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CharityActivitieInfo charityActivitieInfo)
    {
        return toAjax(charityActivitieInfoService.insertCharityActivitieInfo(charityActivitieInfo));
    }

    /**
     * 修改公益灾区捐赠活动
     */
    @PreAuthorize("@ss.hasPermi('charity:activitieInfo:edit')")
    @Log(title = "公益灾区捐赠活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CharityActivitieInfo charityActivitieInfo)
    {
        return toAjax(charityActivitieInfoService.updateCharityActivitieInfo(charityActivitieInfo));
    }

    /**
     * 删除公益灾区捐赠活动
     */
    @PreAuthorize("@ss.hasPermi('charity:activitieInfo:remove')")
    @Log(title = "公益灾区捐赠活动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(charityActivitieInfoService.deleteCharityActivitieInfoByIds(ids));
    }
}
