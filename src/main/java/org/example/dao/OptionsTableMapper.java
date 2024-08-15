package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entry.OptionsTable;

@Mapper
public interface OptionsTableMapper extends BaseMapper<OptionsTable> {
    int batchInsert(@Param("list") List<OptionsTable> list);
}