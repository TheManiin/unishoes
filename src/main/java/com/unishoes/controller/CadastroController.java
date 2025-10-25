package com.unishoes.controller;

import com.unishoes.model.Papel;
import com.unishoes.model.Usuario;
import com.unishoes.repository.PapelRepository;
import com.unishoes.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    private final UsuarioService usuarioService;
    private final PapelRepository papelRepository;

    public CadastroController(UsuarioService usuarioService, PapelRepository papelRepository) {
        this.usuarioService = usuarioService;
        this.papelRepository = papelRepository;
    }

    @GetMapping
    public String cadastroForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping
    public String salvarCadastro(@ModelAttribute Usuario usuario) {
        usuario.setAtivo(true);

        // Busca o papel padrão ou lança exceção se não existir
        Papel papelUser = papelRepository.findByNome("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Papel ROLE_USER não encontrado"));

        usuario.setPapeis(Collections.singleton(papelUser));
        usuarioService.salvar(usuario);

        return "redirect:/login?cadastroSucesso";
    }
}
