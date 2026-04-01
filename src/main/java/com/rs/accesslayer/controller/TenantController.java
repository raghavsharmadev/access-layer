package com.rs.accesslayer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rs.accesslayer.entity.Tenant;
import com.rs.accesslayer.service.TenantService;

@RestController
public class TenantController {
    @Autowired private TenantService tenantService;

    @PostMapping(path = "/tenants")
    public Tenant createTenant(@RequestBody final Tenant tenant) {
        return tenantService.createTenant(tenant);
    }

    @GetMapping(path = "/tenants")
    public List<Tenant> getAllTenants() {
        return tenantService.getAllTenant();
    }
}
