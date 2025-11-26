package com.carrillo.logistica.controller;

import com.carrillo.logistica.model.Proveedor;
import com.carrillo.logistica.service.ProveedorService;
import com.carrillo.logistica.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String listar(@RequestParam(required = false) String query, Model model) {
        List<Proveedor> proveedores;

        if (query != null && !query.trim().isEmpty()) {
            proveedores = proveedorService.buscarPorRucORazonSocial(query);
            model.addAttribute("query", query);
        } else {
            proveedores = proveedorService.listarTodos();
        }

        model.addAttribute("proveedores", proveedores);
        return "proveedores/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("proveedor", new com.carrillo.logistica.dto.ProveedorDTO());
        return "proveedores/form";
    }

    @PostMapping
    public String guardar(@Valid @ModelAttribute("proveedor") com.carrillo.logistica.dto.ProveedorDTO proveedorDTO,
            BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "proveedores/form";
        }
        Proveedor proveedor = com.carrillo.logistica.mapper.ProveedorMapper.toEntity(proveedorDTO);
        proveedorService.guardar(proveedor);
        redirectAttributes.addFlashAttribute("success", "Proveedor guardado correctamente");
        return "redirect:/proveedores";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Proveedor proveedor = proveedorService.buscarPorId(id);
        model.addAttribute("proveedor", com.carrillo.logistica.mapper.ProveedorMapper.toDTO(proveedor));
        return "proveedores/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        proveedorService.eliminarLogico(id);
        redirectAttributes.addFlashAttribute("success", "Proveedor inactivado correctamente");
        return "redirect:/proveedores";
    }

    @GetMapping("/{id}")
    public String verDetalle(@PathVariable Long id, @RequestParam(required = false) String estado, Model model) {
        Proveedor proveedor = proveedorService.buscarPorId(id);
        model.addAttribute("proveedor", proveedor);
        if (estado != null && !estado.isEmpty()) {
            model.addAttribute("productos", productoService.listarPorProveedorYEstado(id, estado));
        } else {
            model.addAttribute("productos", productoService.listarPorProveedor(id));
        }
        model.addAttribute("estadoSeleccionado", estado);
        return "proveedores/detalle";
    }
}
