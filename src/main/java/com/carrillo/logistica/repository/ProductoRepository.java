package com.carrillo.logistica.repository;

import com.carrillo.logistica.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByProveedorIdProveedor(Long idProveedor);

    List<Producto> findByProveedorIdProveedorAndEstado(Long idProveedor, String estado);
}
