package org.example.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 章节表
 */
@Data
@TableName(value = "chapter_table")
public class ChapterTable implements Serializable {
    /**
     * 章节id
     */
    @TableId(value = "chapter_id", type = IdType.INPUT)
    private String chapterId;

    /**
     * 章节名称
     */
    @TableField(value = "chapter_name")
    private String chapterName;

    /**
     * 级别
     */
    @TableField(value = "`level`")
    private String level;

    /**
     * 父id
     */
    @TableField(value = "parent_id")
    private String parentId;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    private static final long serialVersionUID = 1L;
}