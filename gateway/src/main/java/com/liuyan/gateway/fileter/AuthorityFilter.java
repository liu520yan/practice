package com.liuyan.gateway.fileter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuyan.gateway.po.AuthorityRelation;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyan on 2017/10/10.
 */
@Slf4j
//@Component
public class AuthorityFilter extends ZuulFilter {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        //todo 获取权限列表 当前伪造 可能会改为数据库获取/配置文件读取
        List<AuthorityRelation> authorityRelations = getAuthorityRelationList();

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String jwt = request.getHeader("Authorization");
        jwt = jwt.replace("bearer", "").replace(" ", "");
        boolean b = dofilter(request, authorityRelations, jwt);

        if (!b) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("hello");
            return null;
        }

        return null;
    }

    private boolean dofilter(HttpServletRequest request, List<AuthorityRelation> authorityRelations, String jwt) {
//        return authorityRelations
//                .stream()
//                .anyMatch(s -> request.getRequestURI().equals(s.getUri())
//                        && request.getMethod().equals(s.getMethod())
//                        && haveAuthority(jwt, s.getAuthorization()));


        Jwt jwt1 = JwtHelper.decode(jwt);
        Resource resource = new ClassPathResource("public.cert");
        String publicKey;
        try {
            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SignatureVerifier signatureVerifier = new RsaVerifier(publicKey);
        try {
            jwt1.verifySignature(signatureVerifier);
        } catch (Exception e) {
            log.warn("验签不通过" + jwt);
            return false;
        }

        for (AuthorityRelation s : authorityRelations) {
            String uri = request.getRequestURI();
            String method = request.getMethod();
            if (uri.equals(s.getUri())
                    && method.equals(s.getMethod())
                    && haveAuthority(jwt, s.getAuthorization())) {
                return true;
            }
        }
        return false;
    }

    private boolean haveAuthority(String jwt, String authorization) {
        List<String> authorizations = getAuthorization(jwt);
        return authorizations.contains(authorization);
    }

    private List<String> getAuthorization(String jwt) {
        Jwt jwt1 = JwtHelper.decode(jwt);
        JSONObject o = JSONObject.parseObject(jwt1.getClaims());
        JSONArray objects = (JSONArray) o.get("authorities");
        return objects.toJavaList(String.class);
    }

    private List<AuthorityRelation> getAuthorityRelationList() {

        List<AuthorityRelation> authorityRelations = new ArrayList<>();

        AuthorityRelation authorityRelation = new AuthorityRelation();
        authorityRelation.setAuthorization("READ");
        authorityRelation.setMethod("GET");
        authorityRelation.setUri("/resources/test/get");
        authorityRelations.add(authorityRelation);

        AuthorityRelation authorityRelation1 = new AuthorityRelation();
        authorityRelation1.setAuthorization("WRITE");
        authorityRelation1.setMethod("POST");
        authorityRelation1.setUri("/resources/test/post");
        authorityRelations.add(authorityRelation1);

        return authorityRelations;
    }

}
