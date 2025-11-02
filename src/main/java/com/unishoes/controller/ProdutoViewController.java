package com.unishoes.controller;

import com.unishoes.model.Produto;

import com.unishoes.model.Categoria;
import com.unishoes.service.ProdutoService;
import com.unishoes.service.CategoriaService;

import java.beans.PropertyEditorSupport;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.IOException;


@Controller
@RequestMapping("/app/produtos")
public class ProdutoViewController {

    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;

    public ProdutoViewController(ProdutoService produtoService, CategoriaService categoriaService) {
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtoService.listarTodos());
        return "produtos";
    }

    @GetMapping("/novo")
    public String novoProdutoForm(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "novo-produto";
    }

    @PostMapping("/salvar")
    public String salvarProduto(
            @ModelAttribute Produto produto,
            @RequestParam(value = "file", required = false) MultipartFile file) {

        try {
            
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            
            if (file != null && !file.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                produto.setImagemUrl("/uploads/" + fileName);
                System.out.println(">>> Imagem enviada: " + produto.getImagemUrl());
            } else {
                System.out.println(">>> Nenhuma imagem enviada no cadastro.");
            }

            produtoService.salvar(produto);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/app/produtos";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return "redirect:/app/produtos";
    }

    @GetMapping("/editar/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        Produto produto = produtoService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));
        model.addAttribute("produto", produto);
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "editar-produto"; 
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarProduto(
            @PathVariable Long id,
            @ModelAttribute Produto produto,
            @RequestParam(value = "file", required = false) MultipartFile file) {

        try {
            Produto existente = produtoService.buscarPorId(id).orElse(null);
            if (existente == null) {
                return "redirect:/app/produtos";
            }

            
            existente.setNome(produto.getNome());
            existente.setDescricao(produto.getDescricao());
            existente.setPreco(produto.getPreco());
            existente.setCategoria(produto.getCategoria());

            
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            
            if (file != null && !file.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                existente.setImagemUrl("/uploads/" + fileName);
                System.out.println(">>> Nova imagem salva: " + existente.getImagemUrl());
            }
            
            System.out.println(">>> Caminho da pasta de upload: " + uploadPath.toAbsolutePath());
            System.out.println(">>> Arquivo recebido: " + (file != null ? file.getOriginalFilename() : "nenhum"));


            produtoService.salvar(existente);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/app/produtos";
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        
        binder.registerCustomEditor(com.unishoes.model.Categoria.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isBlank()) {
                    setValue(null);
                } else {
                    Long id = Long.valueOf(text);
                    setValue(categoriaService.buscarPorId(id).orElse(null));
                }
            }
        });

        
        binder.registerCustomEditor(java.math.BigDecimal.class, "preco", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isBlank()) {
                    setValue(null);
                    return;
                }
                
                String normalized = text.replace("R$", "")
                                        .replace(" ", "")
                                        .replace(".", "")
                                        .replace(",", ".");
                try {
                    setValue(new java.math.BigDecimal(normalized));
                } catch (NumberFormatException e) {
                    setValue(null);
                }
            }
        });
    }

}
