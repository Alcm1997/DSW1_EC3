package com.carrillo.logistica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProveedorDTO {

    private Long idProveedor;

    @NotBlank(message = "El RUC es obligatorio")
    @Size(min = 11, max = 11, message = "El RUC debe tener 11 dígitos")
    @Pattern(regexp = "\\d+", message = "El RUC debe contener solo números")
    private String ruc;

    @NotBlank(message = "La razón social es obligatoria")
    private String razonSocial;

    private String contacto;

    private String estado;
}
