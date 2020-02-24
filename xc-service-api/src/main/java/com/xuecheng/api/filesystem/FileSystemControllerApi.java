package com.xuecheng.api.filesystem;

import com.xuecheng.framework.domain.filesystem.response.UploadFileResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 * Desc:
 *
 * @author: keen
 * Date: 2020-02-22
 * Time: 22:29
 */
public interface FileSystemControllerApi {

    /**
     * 上传文件的接口
     * @param file
     * @param fileTag
     * @param businessKey
     * @param metadata
     * @return
     */
    public UploadFileResult upload(MultipartFile file,String fileTag,String businessKey,String metadata);
}
