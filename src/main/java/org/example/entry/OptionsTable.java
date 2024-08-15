package org.example.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 选项表
 */
@Data
@TableName(value = "options_table")
public class OptionsTable implements Serializable {
    /**
     * 选项id
     */
    @TableId(value = "option_id", type = IdType.INPUT)
    private String optionId;

    /**
     * 选项名称
     */
    @TableField(value = "option_name")
    private String optionName;

    /**
     * 所属题目id
     */
    @TableField(value = "tppic_id")
    private String tppicId;

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
     * 是否答案
     */
    @TableField(exist = false)
    private Integer answer = 0;

    public OptionsTable (String optionName){
        this.optionName = optionName;
    }

    public OptionsTable (String optionName, Integer answer){
        this.optionName = optionName;
        this.answer = answer;
    }

    private static final long serialVersionUID = 1L;
}