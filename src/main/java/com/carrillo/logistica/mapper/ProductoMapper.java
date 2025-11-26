package com.carrillo.logistica.mapper;

import com.carrillo.logistica.dto.ProductoDTO;
import com.carrillo.logistica.model.Producto;
import com.carrillo.logistica.model.Proveedor;

public class ProductoMapper {

    public static ProductoDTO toDTO(Producto producto) {
        if (producto == null) {
            return null;
        }

        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(producto.getIdProducto());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setEstado(producto.getEstado());

        if (producto.getProveedor() != null) {
            dto.setIdProveedor(producto.getProveedor().getIdProveedor());
            dto.setProveedorNombre(producto.getProveedor().getRazonSocial());
        }

        return dto;
    }

    public static Producto toEntity(ProductoDTO dto, Proveedor proveedor) {
        if (dto == null) {
            return null;
        }

        Producto producto = new Producto();
        producto.setIdProducto(dto.getIdProducto());
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());

        if (dto.getEstado() != null) {
            producto.setEstado(dto.getEstado());
        }

        producto.setProveedor(proveedor);

        return producto;
    }
}
