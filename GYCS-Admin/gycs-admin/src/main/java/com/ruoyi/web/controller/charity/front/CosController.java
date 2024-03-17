package com.ruoyi.web.controller.charity.front;

import com.ruoyi.charity.utils.CosFileUtils;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zyh
 * @date 2024/3/17 21:56
 * @desc IntelliJ IDEA
 */

@RestController
@RequestMapping("/image")
public class CosController {

    @Autowired
    private CosFileUtils cosFileUtils;

    @PostMapping("/upload")
    @ApiOperation("通用的上传图片接口")
    @Log(title = "通用的上传图片接口",businessType = BusinessType.UPDATE)
    public AjaxResult uploadProductImage(@RequestParam("file") MultipartFile file){
        String imageUrl = cosFileUtils.cosUpload(file);
        if (imageUrl.isEmpty()) {
            return AjaxResult.error().put("msg","上传失败请重新上传");
        }
        AjaxResult success = AjaxResult.success();
        success.put("imgUrl",imageUrl);
        success.put("msg","上传成功");
        return success;
    }

}
