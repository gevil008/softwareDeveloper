package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entry.ChapterTable;

@Mapper
public interface ChapterTableMapper extends BaseMapper<ChapterTable> {
    int batchInsert(@Param("list") List<ChapterTable> list);
}