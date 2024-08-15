package org.example.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 错题表
 */
@Data
@TableName(value = "correction_table")
public class CorrectionTable implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "obj_id", type = IdType.INPUT)
    private String objId;

    /**
     * 题目id
     */
    @TableField(value = "tppic_id")
    private String tppicId;

    private static final long serialVersionUID = 1L;
}