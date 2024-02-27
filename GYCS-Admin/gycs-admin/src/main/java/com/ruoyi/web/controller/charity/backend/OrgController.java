package com.ruoyi.web.controller.charity.backend;


import com.ruoyi.charity.domain.dto.Org;
import com.ruoyi.charity.service.IOrgService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 机构用户信息Controller
 *
 * @author zyh
 * @date 2024-01-31
 */
@RestController
@RequestMapping("/charity/org")
public class OrgController extends BaseController
{
    @Autowired
    private IOrgService orgService;

    /**
     * 查询机构用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('charity:org:list')")
    @GetMapping("/list")
    public TableDataInfo list(Org org)
    {
        startPage();
        List<Org> list = orgService.selectOrgList(org);
        return getDataTable(list);
    }

    /**
     * 导出机构用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('charity:org:export')")
    @Log(title = "机构用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Org org)
    {
        List<Org> list = orgService.selectOrgList(org);
        ExcelUtil<Org> util = new ExcelUtil<Org>(Org.class);
        util.exportExcel(response, list, "机构用户信息数据");
    }

    /**
     * 获取机构用户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('charity:org:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(orgService.selectOrgById(id));
    }

    /**
     * 新增机构用户信息
     */
    @PreAuthorize("@ss.hasPermi('charity:org:add')")
    @Log(title = "机构用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Org org)
    {
        return toAjax(orgService.insertOrg(org));
    }

    /**
     * 修改机构用户信息
     */
    @PreAuthorize("@ss.hasPermi('charity:org:edit')")
    @Log(title = "机构用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Org org)
    {
        return toAjax(orgService.updateOrg(org));
    }

    /**
     * 删除机构用户信息
     */
    @PreAuthorize("@ss.hasPermi('charity:org:remove')")
    @Log(title = "机构用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orgService.deleteOrgByIds(ids));
    }
}
