package com.xuecheng.filesystem.config;

import lombok.Data;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.IOException;


/**
 * Created with IntelliJ IDEA.
 * Desc:
 *
 * @author: keen
 * Date: 2020-02-23
 * Time: 12:53
 */
@Configuration
//@ConfigurationProperties(prefix = "fastdfs")
//@PropertySource("config/fastdsf-client.properties")
public class FastDFSConfig {
    public FastDFSConfig(@Value("${xuecheng.fastdfs.tracker_servers}") String trackerServers,
                         @Value("${xuecheng.fastdfs.connect_timeout_in_seconds}") int connectTimeout,
                         @Value("${xuecheng.fastdfs.connect_timeout_in_seconds}") int networkTimeout,
                         @Value("${xuecheng.fastdfs.charset}") String charset) {
        try {
            ClientGlobal.initByTrackers(trackerServers);
            ClientGlobal.setG_network_timeout(networkTimeout);
            ClientGlobal.setG_charset(charset);
            ClientGlobal.setG_connect_timeout(connectTimeout);
        } catch (Exception e) {
            throw new RuntimeException("初始化FastDFS异常", e);
        }
    }

    @Bean
    public TrackerClient trackerClient() {
        return new TrackerClient();
    }

    @Bean
    public TrackerServer trackerServer(TrackerClient trackerClient) throws IOException {
        return trackerClient.getConnection();
    }

    @Bean
    public StorageServer storageServer(TrackerServer trackerServer, TrackerClient trackerClient) throws IOException {
        return trackerClient.getStoreStorage(trackerServer);
    }

    @Bean
    public StorageClient1 storageClient1(TrackerServer trackerServer, StorageServer storageServer) {
        return new StorageClient1(trackerServer, storageServer);
    }
}
