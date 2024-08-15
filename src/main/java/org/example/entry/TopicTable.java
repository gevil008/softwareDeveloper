package org.example.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 题目表
 */
@Data
@TableName(value = "topic_table")
public class TopicTable implements Serializable {
    /**
     * 题目id
     */
    @TableId(value = "topic_id", type = IdType.INPUT)
    private String topicId;

    /**
     * 题目名称
     */
    @TableField(value = "topic_name")
    private String topicName;

    /**
     * 章节id
     */
    @TableField(value = "chapter_id")
    private String chapterId;

    /**
     * 答案id
     */
    @TableField(value = "option_id")
    private String optionId;

    /**
     * 解析
     */
    @TableField(value = "analysis")
    private String analysis;

    /**
     * 图片路径
     */
    @TableField(value = "image_url")
    private String imageUrl;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 选项
     */
    @TableField(exist = false)
    private List<OptionsTable> list;

    private static final long serialVersionUID = 1L;
}