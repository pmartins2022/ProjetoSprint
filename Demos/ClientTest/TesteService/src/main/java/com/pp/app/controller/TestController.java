package com.pp.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController
{
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String getAdmin()
    {
        return "este metodo so funciona se fores admin";
    }

    @GetMapping("/any")
    public String getAny()
    {
        return "este metodo funciona sem login";
    }

}