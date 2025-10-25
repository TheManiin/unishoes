package com.unishoes.controller;

import com.unishoes.model.Produto;

import com.unishoes.service.ProdutoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Produto> buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoService.deletar(id);
    }
    
    @PostMapping("/salvar")
    public String salvarProduto(@ModelAttribute Produto produto,
                                @RequestParam("file") MultipartFile file) {
        try {
            // Se foi feito upload de arquivo
            if (file != null && !file.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path uploadPath = Paths.get("src/main/resources/static/uploads/");
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                produto.setImagemUrl("/uploads/" + fileName);
            } else {
                // Mantém a imagem existente ao editar
                if (produto.getId() != null) {
                    Produto existente = produtoService.buscarPorId(produto.getId()).orElse(null);
                    if (existente != null) {
                        produto.setImagemUrl(existente.getImagemUrl());
                    }
                }
            }

            produtoService.salvar(produto);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/app/produtos";
    }

    @PostMapping("/editar/{id}")
    public String atualizarProduto(@PathVariable Long id,
                                   @ModelAttribute Produto produto,
                                   @RequestParam("imagemFile") MultipartFile imagemFile) {
        try {
            // Se o usuário enviou uma nova imagem
            if (imagemFile != null && !imagemFile.isEmpty()) {
                String pastaUploads = "src/main/resources/static/uploads/";
                Path caminhoPasta = Paths.get(pastaUploads);

                if (!Files.exists(caminhoPasta)) {
                    Files.createDirectories(caminhoPasta);
                }

                String nomeArquivo = System.currentTimeMillis() + "_" + imagemFile.getOriginalFilename();
                Path caminhoArquivo = caminhoPasta.resolve(nomeArquivo);
                Files.copy(imagemFile.getInputStream(), caminhoArquivo, StandardCopyOption.REPLACE_EXISTING);

                produto.setImagemUrl("/uploads/" + nomeArquivo);
            } else {
                // Preserva imagem existente
                Produto existente = produtoService.buscarPorId(id).orElseThrow();
                produto.setImagemUrl(existente.getImagemUrl());
            }

            produtoService.salvar(produto);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/app/produtos";
    }


}
