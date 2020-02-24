package com.xuecheng.fastdsf;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Desc:
 *
 * @author: keen
 * Date: 2020-02-19
 * Time: 21:53
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestFastDFS {

    //测试上传文件
    @Test
    public void testUpload(){
        //初始化配置文件
        try {
            ClientGlobal.initByProperties("config/fastdsf-client.properties");
            //定义一个TrackerClient,用于请求TrackerServer
            TrackerClient trackerClient = new TrackerClient();
            //获取TrackerServer,用于请求StoreStorage
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            //用trackerServer和storageServer构建一个StorageClient
            StorageClient1 storageClient1 = new StorageClient1(trackerServer,storageServer);
            String filePath="D:\\360Downloads\\1000793.jpg";
            //返回的文件ID
            String fileId = storageClient1.upload_file1(filePath, "jpg", null);
            //wKgrhl5Q-CaAQNEuAAnHBSuQ8Qo839.jpg
            System.out.println(fileId);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //测试下载文件
    @Test
    public void testDownload(){
        try {
            ClientGlobal.initByProperties("config/fastdsf-client.properties");
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
            byte[] bytes = storageClient1.download_file1("group1/M00/00/00/wKgrhl5Q-CaAQNEuAAnHBSuQ8Qo839.jpg");
            FileOutputStream fileInputStream = new FileOutputStream("D://test_fafs.jpg");
            fileInputStream.write(bytes);
            fileInputStream.flush();
            System.out.println(bytes.length);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
