package com.unishoes.controller;

import com.unishoes.model.Categoria;
import com.unishoes.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/app/categorias-view") // ou qualquer outro
public class CategoriaViewController {

    private final CategoriaService categoriaService;

    public CategoriaViewController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // Listar categorias
    @GetMapping
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "categorias"; // categorias.html
    }

    // Formulário de nova categoria
    @GetMapping("/nova")
    public String novaCategoriaForm(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "nova-categoria"; // nova-categoria.html
    }

    // Salvar categoria (VIEW)
    @PostMapping("/salvar")
    public String salvarCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.salvar(categoria);
        return "redirect:/app/categorias";
    }

    // Editar categoria
    @GetMapping("/editar/{id}")
    public String editarCategoria(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada: " + id));
        model.addAttribute("categoria", categoria);
        System.out.println(categoria.getId());
        return "nova-categoria"; // reutiliza o mesmo formulário
    }

    @PostMapping("/deletar/{id}")
    public String deletarCategoria(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            categoriaService.deletar(id);
            redirectAttributes.addFlashAttribute("success", "Categoria excluída com sucesso!");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao excluir categoria.");
        }

        return "redirect:/app/categorias";
    }

}
