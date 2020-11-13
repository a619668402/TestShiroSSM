package com.dyl.controller;

import com.dyl.model.Role;
import com.dyl.model.User;
import com.dyl.service.RoleService;
import com.dyl.service.UserRoleService;
import com.dyl.service.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("listUser")
    public String list(Model model) {
        List<User> users = userService.list();
        Map<User, List<Role>> user_roles = new HashMap<>();
        model.addAttribute("us", users);
        for (User user : users) {
            List<Role> roles = roleService.listRoles(user);
            user_roles.put(user, roles);
        }
        model.addAttribute("user_roles", user_roles);
        return "user/listUser";
    }

    @RequestMapping("editUser")
    public String edit(Model model, Long id) {
        List<Role> rs = roleService.list();
        model.addAttribute("rs", rs);
        User user = userService.get(id);
        model.addAttribute("user", user);

        List<Role> roles = roleService.listRoles(user);
        model.addAttribute("currentRoles", roles);
        return "user/editUser";
    }

    @RequestMapping("deleteUser")
    public String delete(Model model, long id) {
        userService.deleteUser(id);
        return "redirect:/user/listUser.do";
    }

    @RequestMapping("updateUser")
    public String update(User user, long[] roleIds) {
        userRoleService.setRoles(user, roleIds);
        String password = user.getPassword();
        //如果在修改的时候没有设置密码，就表示不改动密码
        if (user.getPassword().length() != 0) {
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            int times = 2;
            String algorithmName = "md5";
            String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();
            user.setSalt(salt);
            user.setPassword(encodedPassword);
        } else {
            user.setPassword(null);
        }
        userService.update(user);
        return "redirect:/user/listUser.do";
    }

    @RequestMapping("addUser")
    public String add(Model model, String name, String password) {
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithmName = "md5";

        String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();

        User u = new User();
        u.setName(name);
        u.setPassword(encodedPassword);
        u.setSalt(salt);
        userService.addUser(u);

        return "redirect:/user/listUser.do";
    }
}