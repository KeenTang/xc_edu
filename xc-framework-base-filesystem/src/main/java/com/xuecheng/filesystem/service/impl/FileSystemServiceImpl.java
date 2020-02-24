package com.xuecheng.filesystem.service.impl;

import com.alibaba.fastjson.JSON;
import com.xuecheng.filesystem.service.FileSystemService;
import com.xuecheng.framework.domain.filesystem.FileSystem;
import com.xuecheng.framework.domain.filesystem.response.FileSystemCode;
import com.xuecheng.framework.domain.filesystem.response.UploadFileResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Desc:
 *
 * @author: keen
 * Date: 2020-02-23
 * Time: 11:31
 */
@Service
public class FileSystemServiceImpl implements FileSystemService {

    @Autowired
    private StorageClient1 storageClient1;
    @Override
    public UploadFileResult upload(MultipartFile file, String fileTag, String businessKey, String metadata) throws Exception {
       /* TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
        byte[] bytes = file.getBytes();
        String fileExtName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String fileId = storageClient1.upload_file1(bytes, fileExtName, null);
        FileSystem fileSystem = new FileSystem();
        fileSystem.setFileId(fileId);
        fileSystem.setBusinesskey(businessKey);
        fileSystem.setFiletag(fileTag);
        if (StringUtils.isNotBlank(metadata)) {
            Map map = JSON.parseObject(metadata, Map.class);
            fileSystem.setMetadata(map);
        }
        return new UploadFileResult(CommonCode.SUCCESS, fileSystem);*/
        byte[] bytes = file.getBytes();
        String fileExtName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String fileId = storageClient1.upload_file1(bytes, fileExtName, null);
        FileSystem fileSystem = new FileSystem();
        fileSystem.setFileId(fileId);
        fileSystem.setBusinesskey(businessKey);
        fileSystem.setFiletag(fileTag);
        if (StringUtils.isNotBlank(metadata)) {
            Map map = JSON.parseObject(metadata, Map.class);
            fileSystem.setMetadata(map);
        }
        return new UploadFileResult(CommonCode.SUCCESS, fileSystem);
    }
}
