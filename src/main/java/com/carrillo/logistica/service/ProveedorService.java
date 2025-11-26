package com.carrillo.logistica.service;

import com.carrillo.logistica.exception.DuplicateResourceException;
import com.carrillo.logistica.exception.ResourceNotFoundException;
import com.carrillo.logistica.model.Proveedor;
import com.carrillo.logistica.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> listarTodos() {
        return proveedorRepository.findAll();
    }

    public List<Proveedor> listarActivos() {
        return proveedorRepository.findByEstado("A");
    }

    public Proveedor guardar(Proveedor proveedor) {
        // Verificar duplicidad de RUC (solo al crear o si cambió el RUC)
        if (proveedor.getIdProveedor() == null) {
            // Crear nuevo: verificar que no exista el RUC
            proveedorRepository.findByRuc(proveedor.getRuc()).ifPresent(p -> {
                throw new DuplicateResourceException("Ya existe un proveedor con el RUC: " + proveedor.getRuc());
            });
        } else {
            // Editar: verificar que no exista otro proveedor con el mismo RUC
            proveedorRepository.findByRuc(proveedor.getRuc()).ifPresent(p -> {
                if (!p.getIdProveedor().equals(proveedor.getIdProveedor())) {
                    throw new DuplicateResourceException("Ya existe un proveedor con el RUC: " + proveedor.getRuc());
                }
            });
        }

        return proveedorRepository.save(proveedor);
    }

    public Proveedor buscarPorId(Long id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con ID: " + id));
    }

    public void eliminarLogico(Long id) {
        Proveedor proveedor = buscarPorId(id);
        proveedor.setEstado("I");
        proveedorRepository.save(proveedor);
    }

    public List<Proveedor> buscarPorRucORazonSocial(String query) {
        return proveedorRepository.findByRucContainingOrRazonSocialContaining(query, query);
    }
}
