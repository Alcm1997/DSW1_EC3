package com.carrillo.logistica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "proveedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    @NotBlank(message = "El RUC es obligatorio")
    @Size(min = 11, max = 11, message = "El RUC debe tener 11 dígitos")
    @Pattern(regexp = "\\d+", message = "El RUC debe contener solo números")
    @Column(nullable = false, unique = true, length = 11)
    private String ruc;

    @NotBlank(message = "La razón social es obligatoria")
    @Column(nullable = false, length = 150)
    private String razonSocial;

    @Column(length = 100)
    private String contacto;

    @Column(nullable = false, length = 1)
    private String estado = "A"; // A: Activo, I: Inactivo

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    private List<Producto> productos;
}
