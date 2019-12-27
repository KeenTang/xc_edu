package com.xuecheng.manage.cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;

/**
 * Created with IntelliJ IDEA.
 * Desc:
 *
 * @author: keen
 * Date: 2019-12-15
 * Time: 18:20
 */
public interface PageService {
    QueryResponseResult findList(Integer page, Integer size,QueryPageRequest queryPageRequest);


    CmsPageResult add(CmsPage cmsPage);
}
