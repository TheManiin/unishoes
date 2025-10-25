package com.unishoes.service;

import com.unishoes.model.Categoria;
import com.unishoes.repository.CategoriaRepository;
import com.unishoes.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    
    @Transactional
    public void deletar(Long id) {
        long qtdProdutos = produtoRepository.countByCategoriaId(id);
        if (qtdProdutos > 0) {
            throw new IllegalStateException("Não é possível excluir esta categoria. Existem produtos vinculados a ela.");
        }

        categoriaRepository.deleteById(id);
    }

}
