package com.carrillo.logistica.service;

import com.carrillo.logistica.model.Producto;
import com.carrillo.logistica.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public List<Producto> listarPorProveedor(Long idProveedor) {
        return productoRepository.findByProveedorIdProveedor(idProveedor);
    }

    public List<Producto> listarPorProveedorYEstado(Long idProveedor, String estado) {
        return productoRepository.findByProveedorIdProveedorAndEstado(idProveedor, estado);
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    public void eliminarLogico(Long id) {
        productoRepository.findById(id).ifPresent(producto -> {
            producto.setEstado("I");
            productoRepository.save(producto);
        });
    }
}
