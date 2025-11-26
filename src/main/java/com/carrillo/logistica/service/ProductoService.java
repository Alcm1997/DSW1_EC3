package com.carrillo.logistica.service;

import com.carrillo.logistica.exception.ResourceNotFoundException;
import com.carrillo.logistica.model.Producto;
import com.carrillo.logistica.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
    }

    public void eliminarLogico(Long id) {
        Producto producto = buscarPorId(id);
        producto.setEstado("I");
        productoRepository.save(producto);
    }
}
