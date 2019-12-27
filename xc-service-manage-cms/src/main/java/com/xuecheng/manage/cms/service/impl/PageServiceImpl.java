package com.xuecheng.manage.cms.service.impl;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage.cms.dao.CmsPageRepository;
import com.xuecheng.manage.cms.service.PageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Desc:
 *
 * @author: keen
 * Date: 2019-12-15
 * Time: 18:20
 */
@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Override
    public QueryResponseResult findList(Integer page, Integer size, QueryPageRequest queryPageRequest) {
        if (page == null || page < 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 10;
        }
        CmsPage cmsPage;
        Example<CmsPage> example = null;
        cmsPage = new CmsPage();
        cmsPage.setSiteId(queryPageRequest.getSiteId());
        cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        if (StringUtils.isNotBlank(queryPageRequest.getPageAliase())) {
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
            example = Example.of(cmsPage, matcher);
        } else {
            example = Example.of(cmsPage);
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<CmsPage> cmsPages = cmsPageRepository.findAll(example, pageable);
        QueryResult<CmsPage> queryResult = new QueryResult<>();
        queryResult.setList(cmsPages.getContent());
        queryResult.setTotal(cmsPages.getTotalElements());
        QueryResponseResult responseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return responseResult;
    }

    @Override
    public CmsPageResult add(CmsPage cmsPage) {
        CmsPage cmsPageResult = cmsPageRepository.findBySiteIdAndPageNameAndPageWebPath(
                cmsPage.getSiteId(), cmsPage.getPageName(), cmsPage.getPageWebPath());
        if (cmsPageResult == null) {
            cmsPageRepository.save(cmsPage);
            return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
        }
        return new CmsPageResult(CommonCode.FAIL, null);
    }
}
