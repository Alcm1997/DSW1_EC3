package com.carrillo.logistica.repository;

import com.carrillo.logistica.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    List<Proveedor> findByRucContainingOrRazonSocialContaining(String ruc, String razonSocial);

    List<Proveedor> findByEstado(String estado);

    Optional<Proveedor> findByRuc(String ruc);
}
