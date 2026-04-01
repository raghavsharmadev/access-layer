package com.rs.accesslayer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.accesslayer.entity.Tenant;

@Repository
public interface TenantRespository extends JpaRepository<Tenant, Long> {
    
}
