package org.example.service;

import org.example.entry.ChapterTable;
import org.example.entry.OptionsTable;
import org.example.entry.TopicTable;

import java.util.List;
import java.util.Map;

public interface appService {

    void addOptions(OptionsTable optionsTable);

    void addOptionsList(List<OptionsTable> list);

    void addTopic(TopicTable topicTable);

    void addTopicList(List<TopicTable> list);

    List<ChapterTable> chapterTree(Map<String, String> map);

    Map queryTopicList(Map<String, Object> map);
}
