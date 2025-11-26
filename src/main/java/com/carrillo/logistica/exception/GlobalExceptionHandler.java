package com.carrillo.logistica.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja errores 400 - Datos inválidos (validación)
     * Ocurre cuando los datos del formulario no pasan las validaciones
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(MethodArgumentNotValidException ex, Model model) {
        String errorMessage = "Datos inválidos";
        if (ex.getBindingResult().hasFieldErrors() && ex.getBindingResult().getFieldError() != null) {
            errorMessage += ": " + ex.getBindingResult().getFieldError().getDefaultMessage();
        }
        model.addAttribute("error", errorMessage);
        model.addAttribute("status", 400);
        return "error/400";
    }

    /**
     * Maneja errores 404 - Recurso no encontrado
     * Se lanza cuando se busca un ID que no existe en la base de datos
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException(ResourceNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", 404);
        return "error/404";
    }

    /**
     * Maneja errores 409 - Conflicto (duplicados)
     * Se lanza cuando se intenta crear un recurso que ya existe (ej: RUC duplicado)
     */
    @ExceptionHandler(DuplicateResourceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDuplicateResourceException(DuplicateResourceException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", 409);
        return "error/409";
    }

    /**
     * Maneja errores de integridad de datos
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException ex,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error",
                "Error de integridad de datos: Posible duplicidad o violación de restricciones.");
        return "redirect:/";
    }

    /**
     * Maneja errores 404 - Página no encontrada
     */
    @ExceptionHandler({ org.springframework.web.servlet.resource.NoResourceFoundException.class,
            org.springframework.web.servlet.NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(Model model) {
        model.addAttribute("error", "Página no encontrada");
        model.addAttribute("status", 404);
        return "error/404";
    }

    /**
     * Maneja errores 500 - Error interno del servidor
     * Captura cualquier excepción no manejada específicamente
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGlobalException(Exception ex, Model model) {
        model.addAttribute("error",
                "Ocurrió un error inesperado: " + ex.getClass().getSimpleName() + " - " + ex.getMessage());
        model.addAttribute("status", 500);
        return "error/error";
    }
}
