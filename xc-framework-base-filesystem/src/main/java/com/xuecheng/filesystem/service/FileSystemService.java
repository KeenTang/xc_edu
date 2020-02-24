package com.xuecheng.filesystem.service;

import com.xuecheng.framework.domain.filesystem.response.UploadFileResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 * Desc:
 *
 * @author: keen
 * Date: 2020-02-22
 * Time: 22:35
 */
public interface FileSystemService {
    /**
     * 文件上传
     * @param file
     * @param fileTag
     * @param businessKey
     * @param metadata
     * @return
     * @throws Exception
     */
    UploadFileResult upload(MultipartFile file,String fileTag,String businessKey,String metadata) throws Exception;
}
