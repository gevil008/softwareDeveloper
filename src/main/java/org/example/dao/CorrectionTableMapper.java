package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entry.CorrectionTable;

@Mapper
public interface CorrectionTableMapper extends BaseMapper<CorrectionTable> {
    int batchInsert(@Param("list") List<CorrectionTable> list);
}