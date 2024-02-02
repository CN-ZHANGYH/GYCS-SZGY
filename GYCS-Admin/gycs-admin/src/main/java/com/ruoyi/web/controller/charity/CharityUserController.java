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
import com.ruoyi.charity.domain.dto.CharityUser;
import com.ruoyi.charity.service.ICharityUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户信息表Controller
 *
 * @author zyh
 * @date 2024-02-01
 */
@RestController
@RequestMapping("/charity/user")
public class CharityUserController extends BaseController
{
    @Autowired
    private ICharityUserService charityUserService;

    /**
     * 查询用户信息表列表
     */
    @PreAuthorize("@ss.hasPermi('charity:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(CharityUser charityUser)
    {
        startPage();
        List<CharityUser> list = charityUserService.selectCharityUserList(charityUser);
        return getDataTable(list);
    }

    /**
     * 导出用户信息表列表
     */
    @PreAuthorize("@ss.hasPermi('charity:user:export')")
    @Log(title = "用户信息表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CharityUser charityUser)
    {
        List<CharityUser> list = charityUserService.selectCharityUserList(charityUser);
        ExcelUtil<CharityUser> util = new ExcelUtil<CharityUser>(CharityUser.class);
        util.exportExcel(response, list, "用户信息表数据");
    }

    /**
     * 获取用户信息表详细信息
     */
    @PreAuthorize("@ss.hasPermi('charity:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(charityUserService.selectCharityUserById(id));
    }

    /**
     * 新增用户信息表
     */
    @PreAuthorize("@ss.hasPermi('charity:user:add')")
    @Log(title = "用户信息表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CharityUser charityUser)
    {
        return toAjax(charityUserService.insertCharityUser(charityUser));
    }

    /**
     * 修改用户信息表
     */
    @PreAuthorize("@ss.hasPermi('charity:user:edit')")
    @Log(title = "用户信息表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CharityUser charityUser)
    {
        return toAjax(charityUserService.updateCharityUser(charityUser));
    }

    /**
     * 删除用户信息表
     */
    @PreAuthorize("@ss.hasPermi('charity:user:remove')")
    @Log(title = "用户信息表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(charityUserService.deleteCharityUserByIds(ids));
    }
}
