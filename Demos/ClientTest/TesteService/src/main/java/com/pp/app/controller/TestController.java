package com.pp.app.controller;

import com.pp.app.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class TestController
{
    @Autowired
    private TestService service;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String getAdmin()
    {
        return "este metodo so funciona se fores admin";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/repo")
    public String getRepo(HttpServletRequest request)
    {
        String encoded = request.getHeader("Authorization");

        try
        {
            return service.getAdminMessage(encoded);
        }
        catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @GetMapping("/any")
    public String getAny()
    {
        return "este metodo funciona sem login";
    }
}