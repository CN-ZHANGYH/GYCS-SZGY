package com.ruoyi.charity.config.cos;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zyh
 * @date 2024/3/17 21:53
 * @desc IntelliJ IDEA
 */

@Component
@Data
@ConfigurationProperties(prefix = "cos")
public class CosConfig {
    /** 项目名称 */
    private String secretId;

    /** 版本 */
    private String secretKey;

    /** 所属区域 */
    private String bucket;

    /** 存储桶名称 */
    private String bucketName;

    /** COS存储文件夹 */
    private String folder;

    /** 访问路径 */
    private String webUrl;
}
