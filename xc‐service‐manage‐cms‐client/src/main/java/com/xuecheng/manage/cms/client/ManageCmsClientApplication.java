package com.xuecheng.manage.cms.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created with IntelliJ IDEA.
 * Desc:
 *
 * @author: keen
 * Date: 2019-12-29
 * Time: 17:58
 */
@SpringBootApplication
@EntityScan("com.xuecheng.framework.domain.cms")
@ComponentScan("com.xuecheng.framework")
@ComponentScan("com.xuecheng.manage.cms.client")
public class ManageCmsClientApplication {
}
