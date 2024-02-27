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
import com.ruoyi.charity.domain.dto.DonationTrace;
import com.ruoyi.charity.service.IDonationTraceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 捐款信息记录Controller
 *
 * @author zyh
 * @date 2024-02-01
 */
@RestController
@RequestMapping("/charity/donation")
public class DonationTraceController extends BaseController
{
    @Autowired
    private IDonationTraceService donationTraceService;

    /**
     * 查询捐款信息记录列表
     */
    @PreAuthorize("@ss.hasPermi('charity:donation:list')")
    @GetMapping("/list")
    public TableDataInfo list(DonationTrace donationTrace)
    {
        startPage();
        List<DonationTrace> list = donationTraceService.selectDonationTraceList(donationTrace);
        return getDataTable(list);
    }

    /**
     * 导出捐款信息记录列表
     */
    @PreAuthorize("@ss.hasPermi('charity:donation:export')")
    @Log(title = "捐款信息记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DonationTrace donationTrace)
    {
        List<DonationTrace> list = donationTraceService.selectDonationTraceList(donationTrace);
        ExcelUtil<DonationTrace> util = new ExcelUtil<DonationTrace>(DonationTrace.class);
        util.exportExcel(response, list, "捐款信息记录数据");
    }

    /**
     * 获取捐款信息记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('charity:donation:query')")
    @GetMapping(value = "/{donationId}")
    public AjaxResult getInfo(@PathVariable("donationId") Long donationId)
    {
        return success(donationTraceService.selectDonationTraceByDonationId(donationId));
    }

    /**
     * 新增捐款信息记录
     */
    @PreAuthorize("@ss.hasPermi('charity:donation:add')")
    @Log(title = "捐款信息记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DonationTrace donationTrace)
    {
        return toAjax(donationTraceService.insertDonationTrace(donationTrace));
    }

    /**
     * 修改捐款信息记录
     */
    @PreAuthorize("@ss.hasPermi('charity:donation:edit')")
    @Log(title = "捐款信息记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DonationTrace donationTrace)
    {
        return toAjax(donationTraceService.updateDonationTrace(donationTrace));
    }

    /**
     * 删除捐款信息记录
     */
    @PreAuthorize("@ss.hasPermi('charity:donation:remove')")
    @Log(title = "捐款信息记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{donationIds}")
    public AjaxResult remove(@PathVariable Long[] donationIds)
    {
        return toAjax(donationTraceService.deleteDonationTraceByDonationIds(donationIds));
    }
}
