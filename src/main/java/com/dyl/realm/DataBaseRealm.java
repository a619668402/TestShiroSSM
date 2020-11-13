package com.dyl.realm;

import com.dyl.service.PermissionService;
import com.dyl.service.RoleService;
import com.dyl.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class DataBaseRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = principalCollection.getPrimaryPrincipal().toString();
        Set<String> roles = roleService.listRoles(userName);
        Set<String> permissions = permissionService.listPermissions(userName);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = ((UsernamePasswordToken) authenticationToken);
        String username = token.getUsername();

        String passwordDb = userService.getPassword(username);

//        String passwordDb = "12345";

        String password = new String(token.getPassword());

        if (null == passwordDb || !password.equals(password)) {
            throw new AuthenticationException();
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
        return info;
    }
}
