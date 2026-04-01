package com.rs.accesslayer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.accesslayer.entity.Tenant;
import com.rs.accesslayer.repository.TenantRespository;

@Service
public class TenantService {
    @Autowired private TenantRespository tenantRespository;

    public Tenant createTenant(final Tenant tenant) {
        return tenantRespository.save(tenant);
    }

    public List<Tenant> getAllTenant() {
        return tenantRespository.findAll();
    }
}
