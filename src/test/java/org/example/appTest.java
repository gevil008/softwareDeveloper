package org.example;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.example.entry.ChapterTable;
import org.example.entry.OptionsTable;
import org.example.entry.TopicTable;
import org.example.service.appService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class appTest {
    
    @Autowired
    appService appService;

    @Test
    public void addTopic(){
        TopicTable topicTable = new TopicTable();
        topicTable.setTopicName("某机器字长为n，最高位是符号位，其定点整数的最大值为（ ）。");
        topicTable.setChapterId("4FCA8232942CA70ED4914FCA8201914FCA8224001A");
        topicTable.setAnalysis("本题考查计算机系统中的数据表示基础知识。\n" +
                "机器字长为n，最高位为符号位，则剩余的n-1位用来表示数值，其最大值是这n-1位都为1，也就是2n-1-1。");

        List<OptionsTable> list = new ArrayList<>();
        list.add(new OptionsTable("2ⁿ-1"));
        list.add(new OptionsTable("2ⁿ⁻¹-1",1));
        list.add(new OptionsTable("2ⁿ"));
        list.add(new OptionsTable("2ⁿ⁻¹"));
        topicTable.setList(list);
        appService.addTopic(topicTable);
    }

    @Test
    public void chapterTree(){
        Map<String, String> map = new HashMap<>();
        map.put("parentId","4FCA82236B2CA70ED4914FCA8201914FCA82220000");
        List<ChapterTable> chapterTables = appService.chapterTree(map);
        for (ChapterTable chapterTable : chapterTables) {
            log.info(JSONObject.toJSONString(chapterTable));
        }
    }

    @Test
    public void queryTopicList(){
        Map<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("pageSize", 20);
        map.put("chapterId", "4FCA82236B2CA70ED4914FCA8201914FCA82220000");
        Map result = appService.queryTopicList(map);
        log.info(JSONObject.toJSONString(result));
    }
}
