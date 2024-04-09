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
import com.ruoyi.charity.domain.dto.CharityRaiseFund;
import com.ruoyi.charity.service.raise.ICharityRaiseFundService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公益募资活动Controller
 *
 * @author zyh
 * @date 2024-02-01
 */
@RestController
@RequestMapping("/charity/fund")
public class CharityRaiseFundController extends BaseController
{
    @Autowired
    private ICharityRaiseFundService charityRaiseFundService;

    /**
     * 查询公益募资活动列表
     */
    @PreAuthorize("@ss.hasPermi('charity:fund:list')")
    @GetMapping("/list")
    public TableDataInfo list(CharityRaiseFund charityRaiseFund)
    {
        Long userId = SecurityUtils.getLoginUser().getUserId();
        startPage();
        List<CharityRaiseFund> list = charityRaiseFundService.selectCharityRaiseFundList(charityRaiseFund,userId);
        return getDataTable(list);
    }

    /**
     * 导出公益募资活动列表
     */
    @PreAuthorize("@ss.hasPermi('charity:fund:export')")
    @Log(title = "公益募资活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CharityRaiseFund charityRaiseFund)
    {
        Long userId = SecurityUtils.getLoginUser().getUserId();
        List<CharityRaiseFund> list = charityRaiseFundService.selectCharityRaiseFundList(charityRaiseFund, userId);
        ExcelUtil<CharityRaiseFund> util = new ExcelUtil<CharityRaiseFund>(CharityRaiseFund.class);
        util.exportExcel(response, list, "公益募资活动数据");
    }

    /**
     * 获取公益募资活动详细信息
     */
    @PreAuthorize("@ss.hasPermi('charity:fund:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(charityRaiseFundService.selectCharityRaiseFundById(id));
    }

    /**
     * 新增公益募资活动
     */
    @PreAuthorize("@ss.hasPermi('charity:fund:add')")
    @Log(title = "公益募资活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CharityRaiseFund charityRaiseFund)
    {
        return toAjax(charityRaiseFundService.insertCharityRaiseFund(charityRaiseFund));
    }

    /**
     * 修改公益募资活动
     */
    @PreAuthorize("@ss.hasPermi('charity:fund:edit')")
    @Log(title = "公益募资活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CharityRaiseFund charityRaiseFund)
    {
        System.out.println(charityRaiseFund);
        return toAjax(charityRaiseFundService.updateCharityRaiseFund(charityRaiseFund));
    }

    /**
     * 删除公益募资活动
     */
    @PreAuthorize("@ss.hasPermi('charity:fund:remove')")
    @Log(title = "公益募资活动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(charityRaiseFundService.deleteCharityRaiseFundByIds(ids));
    }
}
