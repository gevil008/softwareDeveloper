package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.util.guid.GUIDGenerator;
import org.apache.commons.lang3.StringUtils;
import org.example.dao.ChapterTableMapper;
import org.example.dao.OptionsTableMapper;
import org.example.dao.TopicTableMapper;
import org.example.entry.ChapterTable;
import org.example.entry.OptionsTable;
import org.example.entry.TopicTable;
import org.example.service.appService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class appServiceImpl implements appService {

    @Autowired
    OptionsTableMapper optionsTableMapper;

    @Autowired
    TopicTableMapper topicTableMapper;

    @Autowired
    ChapterTableMapper chapterTableMapper;

    @Override
    public void addOptions(OptionsTable optionsTable) {
    }

    @Override
    public void addOptionsList(List<OptionsTable> list) {

    }

    @Override
    public void addTopic(TopicTable topicTable) {
        int size = topicTableMapper.selectList(new QueryWrapper<TopicTable>().eq("chapter_id", topicTable.getChapterId())).size();
        topicTable.setTopicId(GUIDGenerator.generate42(false));
        topicTable.setSort(++size);
        for (int i = 0; i < topicTable.getList().size(); i++) {
            OptionsTable table = topicTable.getList().get(i);
            table.setOptionId(GUIDGenerator.generate42(false));
            table.setTppicId(topicTable.getTopicId());
            table.setSort(i);
            if (table.getAnswer() == 1) {
                topicTable.setOptionId(table.getOptionId());
            }
        }
        topicTableMapper.insert(topicTable);
        optionsTableMapper.batchInsert(topicTable.getList());
    }

    @Override
    public void addTopicList(List<TopicTable> list) {

    }

    @Override
    public List<ChapterTable> chapterTree(Map<String, String> map) {
        QueryWrapper<ChapterTable> wrapper = new QueryWrapper<>();
        if (StringUtils.isEmpty(map.get("parentId"))){
            wrapper.eq("level","0");
        } else {
            wrapper.eq("parent_id", map.get("parentId"));
        }
        wrapper.orderByAsc("sort");

        List<ChapterTable> chapterTables = chapterTableMapper.selectList(wrapper);
        return chapterTables;
    }

    @Override
    public Map queryTopicList(Map<String, Object> map) {
        int page = map.get("page") == null ? 1 : (int) map.get("page");
        int pageSize = map.get("pageSize") == null ? 20 : (int) map.get("pageSize");
        Page<TopicTable> objectPage = new Page<>(page, pageSize);
        QueryWrapper<TopicTable> wrapper = new QueryWrapper<>();
        if (map.get("chapterId") != null && !"".equals(map.get("chapterId"))){
            wrapper.eq("chapter_id", map.get("chapterId"));
        }
        Page<TopicTable> topicTablePage = topicTableMapper.selectPage(objectPage, wrapper);

        Map<String, Object> result = new HashMap<>();
        result.put("data", topicTablePage.getRecords());
        result.put("total", objectPage.getTotal());
        return result;
    }
}
