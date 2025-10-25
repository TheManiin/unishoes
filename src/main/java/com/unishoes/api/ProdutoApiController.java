package com.unishoes.api;

import com.unishoes.model.Produto;
import com.unishoes.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoApiController {

    private final ProdutoRepository produtoRepository;

    public ProdutoApiController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        Produto p = produtoRepository.findById(id).orElseThrow();
        p.setNome(produtoAtualizado.getNome());
        p.setPreco(produtoAtualizado.getPreco());
        p.setDescricao(produtoAtualizado.getDescricao());
        p.setCategoria(produtoAtualizado.getCategoria());
        return produtoRepository.save(p);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }
}
