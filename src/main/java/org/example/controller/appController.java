package org.example.controller;

import org.example.entry.TopicTable;
import org.example.service.appService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/app")
public class appController {

    @Autowired
    appService appService;

    @PostMapping("/addTopic")
    public void addTopic(@RequestBody TopicTable topicTable){
        appService.addTopic(topicTable);
    }

    @PostMapping("/chapterTree")
    public Object chapterTree (@RequestBody Map map){
        return appService.chapterTree(map);
    }

    @PostMapping("/queryTpoicList")
    public Object queryTpoicList (@RequestBody Map map){
        return appService.queryTopicList(map);
    }

    @PostMapping("/file/upload")
    public void fileUpload(@RequestParam MultipartFile multipartFile, HttpServletRequest req){

    }
}
