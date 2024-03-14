package com.ruoyi.charity.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("charity_article")
public class ActivityArticle implements Serializable {

    @TableId(type = IdType.AUTO)
    private int id;

    @TableField(value = "author")
    @NotNull(message = "作者不能为空")
    private String author;

    @TableField(value = "content")
    @NotNull(message = "内容不能为空")
    private String content;


    @TableField(value = "img")
    @NotNull(message = "封面不能为空")
    private String  img;

    @TableField(value = "activity_id")
    @NotNull(message = "活动ID不能为空")
    private Long activityId;

    @TableField(value = "create_time")
    private String createTime;
}
