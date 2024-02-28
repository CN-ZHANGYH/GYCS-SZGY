package com.ruoyi.web.controller.charity.backend;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.charity.domain.dto.CharityRaiseAudit;
import com.ruoyi.charity.service.ICharityRaiseAuditService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 审核Controller
 *
 * @author zyh
 * @date 2024-02-28
 */
@RestController
@RequestMapping("/charity/audit")
public class CharityRaiseAuditController extends BaseController
{
    @Autowired
    private ICharityRaiseAuditService charityRaiseAuditService;

    /**
     * 查询审核列表
     */
    @PreAuthorize("@ss.hasPermi('charity:audit:list')")
    @GetMapping("/list")
    public TableDataInfo list(CharityRaiseAudit charityRaiseAudit)
    {
        startPage();
        List<CharityRaiseAudit> list = charityRaiseAuditService.selectCharityRaiseAuditList(charityRaiseAudit);
        return getDataTable(list);
    }

    /**
     * 导出审核列表
     */
    @PreAuthorize("@ss.hasPermi('charity:audit:export')")
    @Log(title = "审核", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CharityRaiseAudit charityRaiseAudit)
    {
        List<CharityRaiseAudit> list = charityRaiseAuditService.selectCharityRaiseAuditList(charityRaiseAudit);
        ExcelUtil<CharityRaiseAudit> util = new ExcelUtil<CharityRaiseAudit>(CharityRaiseAudit.class);
        util.exportExcel(response, list, "审核数据");
    }

    /**
     * 获取审核详细信息
     */
    @PreAuthorize("@ss.hasPermi('charity:audit:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(charityRaiseAuditService.selectCharityRaiseAuditById(id));
    }

    /**
     * 新增审核
     */
    @PreAuthorize("@ss.hasPermi('charity:audit:add')")
    @Log(title = "审核", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CharityRaiseAudit charityRaiseAudit)
    {
        return toAjax(charityRaiseAuditService.insertCharityRaiseAudit(charityRaiseAudit));
    }

    /**
     * 修改审核
     */
    @PreAuthorize("@ss.hasPermi('charity:audit:edit')")
    @Log(title = "审核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CharityRaiseAudit charityRaiseAudit)
    {
        return toAjax(charityRaiseAuditService.updateCharityRaiseAudit(charityRaiseAudit));
    }

    /**
     * 删除审核
     */
    @PreAuthorize("@ss.hasPermi('charity:audit:remove')")
    @Log(title = "审核", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(charityRaiseAuditService.deleteCharityRaiseAuditByIds(ids));
    }
}
