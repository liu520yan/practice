package com.liuyan.gateway.fileter;

import com.liuyan.gateway.po.AuthorityRelation;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by liuyan on 2017/10/10.
 */
@Slf4j
public class AuthorityFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {

        //todo 获取权限列表 当前伪造 可能会改为数据库获取/配置文件读取
        List<AuthorityRelation> authorityRelations = getAuthorityRelationList();

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String value = request.getHeader("Authorization");
        String method = request.getMethod().toUpperCase();
        String uri = request.getRequestURI();
        Optional<AuthorityRelation> authorityRelation = authorityRelations.stream().filter(s -> s.getMethod().equals(method)).filter(s -> s.getAuthorization().equals(value)).filter(s -> s.getUri().equals(uri)).findAny();
        if (!authorityRelation.isPresent()) {
            log.warn("access token is matching !!! ");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }

        return null;
    }

    private List<AuthorityRelation> getAuthorityRelationList() {

        List<AuthorityRelation> authorityRelations = new ArrayList<>();

        AuthorityRelation authorityRelation = new AuthorityRelation();
        authorityRelation.setAuthorization("READ");
        authorityRelation.setMethod("GET");
        authorityRelation.setUri("/test/get");
        authorityRelations.add(authorityRelation);

        AuthorityRelation authorityRelation1 = new AuthorityRelation();
        authorityRelation1.setAuthorization("WRITE");
        authorityRelation1.setMethod("POST");
        authorityRelation1.setUri("/test/post");
        authorityRelations.add(authorityRelation1);

        return authorityRelations;
    }

}
