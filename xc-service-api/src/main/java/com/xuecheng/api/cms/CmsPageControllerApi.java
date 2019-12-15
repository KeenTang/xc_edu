package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;

/**
 * Created with IntelliJ IDEA.
 * Desc:
 *
 * @author: keen
 * Date: 2019-12-15
 * Time: 14:13
 */
public interface CmsPageControllerApi {
    QueryResponseResult findList(Integer page, Integer size, QueryPageRequest queryPageRequest);
}
