package com.ruoyi.charity.utils;

/**
 * @author zyh
 * @date 2024/3/17 21:44
 * @desc IntelliJ IDEA
 */

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.ruoyi.charity.config.cos.CosConfig;
import com.ruoyi.common.utils.uuid.UUID;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 腾讯云COS云存储工具类
 */

@Component
public class CosFileUtils {

    @Autowired
    private CosConfig cosConfig;

    /**
     * 文件上传到COS对象存储
     * @param multipartFile
     * @return
     */
    public String cosUpload(MultipartFile multipartFile){
        String oldFileName = multipartFile.getOriginalFilename();  // 获取文件名字
        String eName = oldFileName.substring(oldFileName.lastIndexOf(".")); // 获取文件后缀
        String newFileName = UUID.fastUUID() + eName; // 使用 UUID生成字符串，再与后缀结合生成新名字
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(cosConfig.getSecretId(), cosConfig.getSecretKey());
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(cosConfig.getBucket()));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = cosConfig.getBucketName();
        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        File localFile = null;
        try {
            localFile = File.createTempFile("temp", null);
            multipartFile.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 指定要上传到 COS 上的路径
        String key = cosConfig.getFolder() + datePath() + "/" + newFileName;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
        // 关闭客户端(关闭后台线程)
        cosclient.shutdown();
        // 返回存储路径
        return cosConfig.getWebUrl()+key;
    }

    /**
     * 日期路径 即年/月/日 如2000/01/01
     */
    public static String datePath(){
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

}
