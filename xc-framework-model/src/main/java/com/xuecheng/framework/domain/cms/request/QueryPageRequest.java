package com.xuecheng.framework.domain.cms.request;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Desc:
 *
 * @author: keen
 * Date: 2019-12-13
 * Time: 21:59
 */
@Data
public class QueryPageRequest {
    private String siteId;
    private String pageId;
    private String pageName;
    private String pageAliase;
    private String templateId;
}
