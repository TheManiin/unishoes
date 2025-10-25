package com.unishoes.controller;

import com.unishoes.model.Usuario;
import com.unishoes.repository.UsuarioRepository;
import com.unishoes.repository.PapelRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UsuarioRepository usuarioRepository;
    private final PapelRepository papelRepository;

    public AdminController(UsuarioRepository usuarioRepository, PapelRepository papelRepository) {
        this.usuarioRepository = usuarioRepository;
        this.papelRepository = papelRepository;
    }

    // Página principal do painel admin
    @GetMapping
    public String painelAdmin(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "admin";
    }

    // Alternar o papel do usuário (ROLE_USER <-> ROLE_ADMIN)
    @PostMapping("/alterarRole/{id}")
    public String alterarRole(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            boolean isAdmin = usuario.getPapeis().stream()
                    .anyMatch(p -> p.getNome().equals("ROLE_ADMIN"));

            usuario.getPapeis().clear();
            if (isAdmin) {
                usuario.getPapeis().add(papelRepository.findByNome("ROLE_USER").orElseThrow());
            } else {
                usuario.getPapeis().add(papelRepository.findByNome("ROLE_ADMIN").orElseThrow());
            }
            usuarioRepository.save(usuario);
        }
        return "redirect:/admin";
    }

    // Ativar/desativar usuário
    @PostMapping("/toggleAtivo/{id}")
    public String toggleAtivo(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setAtivo(!usuario.isAtivo());
            usuarioRepository.save(usuario);
        }
        return "redirect:/admin";
    }
    
    // Excluir usuário
    @PostMapping("/excluir/{id}")
    public String deletarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/admin";
    }

}
