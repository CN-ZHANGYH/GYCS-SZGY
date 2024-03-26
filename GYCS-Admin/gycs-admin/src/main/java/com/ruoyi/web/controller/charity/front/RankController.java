package com.ruoyi.web.controller.charity.front;

import com.ruoyi.charity.domain.vo.RankUserVo;
import com.ruoyi.charity.service.UserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rank")
public class RankController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public AjaxResult getRankByUserCredit() {
        List<RankUserVo> rankByUserCredit = userService.getRankByUserCredit();
        AjaxResult success = AjaxResult.success();
        success.put("total",rankByUserCredit.size());
        success.put("rows",rankByUserCredit);
        return success;
    }
}
