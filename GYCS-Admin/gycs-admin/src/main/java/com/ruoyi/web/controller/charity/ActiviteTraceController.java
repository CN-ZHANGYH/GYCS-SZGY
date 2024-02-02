package com.ruoyi.web.controller.charity;

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
import com.ruoyi.charity.domain.dto.ActiviteTrace;
import com.ruoyi.charity.service.IActiviteTraceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公益活动溯源记录Controller
 *
 * @author zyh
 * @date 2024-02-01
 */
@RestController
@RequestMapping("/charity/activite")
public class ActiviteTraceController extends BaseController
{
    @Autowired
    private IActiviteTraceService activiteTraceService;

    /**
     * 查询公益活动溯源记录列表
     */
    @PreAuthorize("@ss.hasPermi('charity:activite:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActiviteTrace activiteTrace)
    {
        startPage();
        List<ActiviteTrace> list = activiteTraceService.selectActiviteTraceList(activiteTrace);
        return getDataTable(list);
    }

    /**
     * 导出公益活动溯源记录列表
     */
    @PreAuthorize("@ss.hasPermi('charity:activite:export')")
    @Log(title = "公益活动溯源记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActiviteTrace activiteTrace)
    {
        List<ActiviteTrace> list = activiteTraceService.selectActiviteTraceList(activiteTrace);
        ExcelUtil<ActiviteTrace> util = new ExcelUtil<ActiviteTrace>(ActiviteTrace.class);
        util.exportExcel(response, list, "公益活动溯源记录数据");
    }

    /**
     * 获取公益活动溯源记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('charity:activite:query')")
    @GetMapping(value = "/{charityId}")
    public AjaxResult getInfo(@PathVariable("charityId") Long charityId)
    {
        return success(activiteTraceService.selectActiviteTraceByCharityId(charityId));
    }

    /**
     * 新增公益活动溯源记录
     */
    @PreAuthorize("@ss.hasPermi('charity:activite:add')")
    @Log(title = "公益活动溯源记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActiviteTrace activiteTrace)
    {
        return toAjax(activiteTraceService.insertActiviteTrace(activiteTrace));
    }

    /**
     * 修改公益活动溯源记录
     */
    @PreAuthorize("@ss.hasPermi('charity:activite:edit')")
    @Log(title = "公益活动溯源记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActiviteTrace activiteTrace)
    {
        return toAjax(activiteTraceService.updateActiviteTrace(activiteTrace));
    }

    /**
     * 删除公益活动溯源记录
     */
    @PreAuthorize("@ss.hasPermi('charity:activite:remove')")
    @Log(title = "公益活动溯源记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{charityIds}")
    public AjaxResult remove(@PathVariable Long[] charityIds)
    {
        return toAjax(activiteTraceService.deleteActiviteTraceByCharityIds(charityIds));
    }
}
