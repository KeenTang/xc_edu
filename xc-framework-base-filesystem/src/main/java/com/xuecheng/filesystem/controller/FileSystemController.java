package com.xuecheng.filesystem.controller;

import com.xuecheng.api.filesystem.FileSystemControllerApi;
import com.xuecheng.filesystem.service.FileSystemService;
import com.xuecheng.framework.domain.filesystem.response.UploadFileResult;
import com.xuecheng.framework.model.response.CommonCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 * Desc:
 *
 * @author: keen
 * Date: 2020-02-23
 * Time: 18:22
 */
@RestController
@RequestMapping("/filesystem")
public class FileSystemController implements FileSystemControllerApi {

    @Autowired
    private FileSystemService fileSystemService;

    @Override
    @PostMapping("/upload")
    public UploadFileResult upload(MultipartFile file, String fileTag, String businessKey, String metadata) {
        try {
            return fileSystemService.upload(file, fileTag, businessKey, metadata);
        } catch (Exception e) {
            e.printStackTrace();
            return new UploadFileResult(CommonCode.FAIL, null);
        }
    }
}
