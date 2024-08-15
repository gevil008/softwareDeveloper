package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entry.TopicTable;

@Mapper
public interface TopicTableMapper extends BaseMapper<TopicTable> {
    int batchInsert(@Param("list") List<TopicTable> list);
}