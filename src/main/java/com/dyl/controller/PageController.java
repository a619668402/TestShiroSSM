package com.dyl.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "login")
    public String login() {
        return "login";
    }

    @RequiresPermissions("deleteOrder")
    @RequestMapping("deleteOrder")
    public String deleteOrder() {
        return "deleteOrder";
    }

    @RequiresRoles("admin")
    @RequestMapping("deleteProduct")
    public String deleteProduct() {
        return "deleteProduct";
    }

    @RequestMapping("listProduct")
    public String listProduct(){
        return "listProduct";
    }

    @RequestMapping("unauthorized")
    public String noPerms(){
        return "unauthorized";
    }
}
