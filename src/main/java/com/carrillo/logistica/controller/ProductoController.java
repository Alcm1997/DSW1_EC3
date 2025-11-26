package com.carrillo.logistica.controller;

import com.carrillo.logistica.dto.ProductoDTO;
import com.carrillo.logistica.model.Producto;
import com.carrillo.logistica.model.Proveedor;
import com.carrillo.logistica.service.ProductoService;
import com.carrillo.logistica.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        return "productos/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("producto", new ProductoDTO());
        model.addAttribute("proveedores", proveedorService.listarActivos());
        return "productos/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("producto") ProductoDTO productoDTO,
            BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("proveedores", proveedorService.listarActivos());
            return "productos/form";
        }
        Proveedor proveedor = proveedorService.buscarPorId(productoDTO.getIdProveedor());
        Producto producto = com.carrillo.logistica.mapper.ProductoMapper.toEntity(productoDTO, proveedor);
        productoService.guardar(producto);
        redirectAttributes.addFlashAttribute("success", "Producto guardado correctamente");
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Producto producto = productoService.buscarPorId(id);
        model.addAttribute("producto", com.carrillo.logistica.mapper.ProductoMapper.toDTO(producto));
        model.addAttribute("proveedores", proveedorService.listarActivos());
        return "productos/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        productoService.eliminarLogico(id);
        redirectAttributes.addFlashAttribute("success", "Producto inactivado correctamente");
        return "redirect:/productos";
    }
}
