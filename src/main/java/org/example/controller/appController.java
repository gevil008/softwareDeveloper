package org.example.controller;

import com.util.guid.GUIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.example.entry.TopicTable;
import org.example.service.appService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@RestController
@RequestMapping("/app")
@Slf4j
public class appController {

    @Autowired
    appService appService;

    @PostMapping("/addTopic")
    public void addTopic(@RequestBody TopicTable topicTable) {
        appService.addTopic(topicTable);
    }

    @PostMapping("/chapterTree")
    public Object chapterTree(@RequestBody Map map) {
        return appService.chapterTree(map);
    }

    @PostMapping("/queryTpoicList")
    public Object queryTpoicList(@RequestBody Map map) {
        return appService.queryTopicList(map);
    }

    @PostMapping("/file/upload")
    public Object fileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        try {
            // 获取项目根目录
            String projectRootPath = System.getProperty("user.dir");
            // 文件存储路径 (相对项目路径下的 "uploads" 文件夹)
            String uploadDir = projectRootPath + "/uploads/";

            // 如果 "uploads" 文件夹不存在，则创建
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 获取上传文件的原始名称并构建目标文件路径
            String fileName = file.getOriginalFilename();
            String attSuffix = fileName.substring(fileName.lastIndexOf("."));
            String s = GUIDGenerator.generate32(false);
            fileName = s + attSuffix;
            Path filePath = Paths.get(uploadDir + fileName);
            // 将文件保存到目标路径
            file.transferTo(filePath.toFile());
            log.info("文件上传成功: " + fileName + "，路径为: " + filePath);
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "文件上传失败";
        }
    }

    @GetMapping("/file/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam("filename") String filename) {
        try {
            // 获取项目根目录
            String projectRootPath = System.getProperty("user.dir");
            // 构建文件路径，假设文件保存在 "uploads" 文件夹下
            String uploadDir = projectRootPath + "/uploads/";
            Path filePath = Paths.get(uploadDir, filename);

            // 如果文件不存在，返回 404
            if (!Files.exists(filePath)) {
                return ResponseEntity.notFound().build();
            }

            // 以流的方式读取文件
            File file = filePath.toFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            // 返回文件下载响应
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(file.length())
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
