package com.carrillo.logistica.service;

import com.carrillo.logistica.model.Proveedor;
import com.carrillo.logistica.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return proveedorRepository.save(proveedor);
    }

    public Optional<Proveedor> buscarPorId(Long id) {
        return proveedorRepository.findById(id);
    }

    public void eliminarLogico(Long id) {
        proveedorRepository.findById(id).ifPresent(proveedor -> {
            proveedor.setEstado("I");
            proveedorRepository.save(proveedor);
        });
    }

    public List<Proveedor> buscarPorRucORazonSocial(String query) {
        return proveedorRepository.findByRucContainingOrRazonSocialContaining(query, query);
    }
}
