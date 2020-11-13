package com.dyl.filter;

import com.dyl.service.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Set;

public class URIPathMatchingFilter extends PathMatchingFilter {

    @Autowired
    private PermissionService permissionService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String requestUri = getPathWithinApplication(request);
        System.out.println("requestURI: " + requestUri);
        if (requestUri.contains(".do")) {
            requestUri = requestUri.split("\\.")[0];
        }
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            WebUtils.issueRedirect(request, response, "/login.do");
            return false;
        }
        boolean neetInterceptor = permissionService.needInterceptor(requestUri);
        if (!neetInterceptor) {
            return true;
        } else {
            boolean hasPermission = false;
            String userName = subject.getPrincipal().toString();
            Set<String> permissionUrls = permissionService.listPermissionURLs(userName);
            for (String permissionUrl : permissionUrls) {
                if (permissionUrl.equals(requestUri)) {
                    hasPermission = true;
                    break;
                }
            }
            if (hasPermission) {
                return true;
            } else {
                UnauthorizedException ex = new UnauthorizedException("当前用户没有访问路径的权限");
                subject.getSession().setAttribute("ex", ex);
                WebUtils.issueRedirect(request, response, "/unauthorized.do");
                return false;
            }
        }
    }
}
