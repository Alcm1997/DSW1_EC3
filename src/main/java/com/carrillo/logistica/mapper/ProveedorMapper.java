package com.carrillo.logistica.mapper;

import com.carrillo.logistica.dto.ProveedorDTO;
import com.carrillo.logistica.model.Proveedor;

public class ProveedorMapper {

    public static ProveedorDTO toDTO(Proveedor proveedor) {
        if (proveedor == null) {
            return null;
        }

        ProveedorDTO dto = new ProveedorDTO();
        dto.setIdProveedor(proveedor.getIdProveedor());
        dto.setRuc(proveedor.getRuc());
        dto.setRazonSocial(proveedor.getRazonSocial());
        dto.setContacto(proveedor.getContacto());
        dto.setEstado(proveedor.getEstado());

        return dto;
    }

    public static Proveedor toEntity(ProveedorDTO dto) {
        if (dto == null) {
            return null;
        }

        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(dto.getIdProveedor());
        proveedor.setRuc(dto.getRuc());
        proveedor.setRazonSocial(dto.getRazonSocial());
        proveedor.setContacto(dto.getContacto());

        if (dto.getEstado() != null) {
            proveedor.setEstado(dto.getEstado());
        }

        return proveedor;
    }
}
