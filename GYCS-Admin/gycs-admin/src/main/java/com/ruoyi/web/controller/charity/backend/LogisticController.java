package com.ruoyi.web.controller.charity.backend;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.charity.domain.dto.Logistic;
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
import com.ruoyi.charity.service.ILogisticService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物流商用户信息Controller
 *
 * @author zyh
 * @date 2024-02-01
 */
@RestController
@RequestMapping("/charity/logistic")
public class LogisticController extends BaseController
{
    @Autowired
    private ILogisticService logisticService;

    /**
     * 查询物流商用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('charity:logistic:list')")
    @GetMapping("/list")
    public TableDataInfo list(Logistic logistic)
    {
        startPage();
        List<Logistic> list = logisticService.selectLogisticList(logistic);
        return getDataTable(list);
    }

    /**
     * 导出物流商用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('charity:logistic:export')")
    @Log(title = "物流商用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Logistic logistic)
    {
        List<Logistic> list = logisticService.selectLogisticList(logistic);
        ExcelUtil<Logistic> util = new ExcelUtil<Logistic>(Logistic.class);
        util.exportExcel(response, list, "物流商用户信息数据");
    }

    /**
     * 获取物流商用户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('charity:logistic:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(logisticService.selectLogisticById(id));
    }

    /**
     * 新增物流商用户信息
     */
    @PreAuthorize("@ss.hasPermi('charity:logistic:add')")
    @Log(title = "物流商用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Logistic logistic)
    {
        return toAjax(logisticService.insertLogistic(logistic));
    }

    /**
     * 修改物流商用户信息
     */
    @PreAuthorize("@ss.hasPermi('charity:logistic:edit')")
    @Log(title = "物流商用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Logistic logistic)
    {
        return toAjax(logisticService.updateLogistic(logistic));
    }

    /**
     * 删除物流商用户信息
     */
    @PreAuthorize("@ss.hasPermi('charity:logistic:remove')")
    @Log(title = "物流商用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(logisticService.deleteLogisticByIds(ids));
    }
}
