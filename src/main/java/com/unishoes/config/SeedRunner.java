package com.unishoes.config;

import com.unishoes.model.Papel;
import com.unishoes.model.Usuario;
import com.unishoes.repository.PapelRepository;
import com.unishoes.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SeedRunner implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PapelRepository papelRepository;
    private final PasswordEncoder encoder;

    public SeedRunner(UsuarioRepository usuarioRepository,
                      PapelRepository papelRepository,
                      PasswordEncoder encoder) {
        this.usuarioRepository = usuarioRepository;
        this.papelRepository = papelRepository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        Papel adminRole = papelRepository.findByNome("ROLE_ADMIN")
                .orElseGet(() -> {
                    Papel p = new Papel();
                    p.setNome("ROLE_ADMIN");
                    return papelRepository.save(p);
                });

        Papel userRole = papelRepository.findByNome("ROLE_USER")
                .orElseGet(() -> {
                    Papel p = new Papel();
                    p.setNome("ROLE_USER");
                    return papelRepository.save(p);
                });

        if (usuarioRepository.findByEmail("admin@exemplo.com").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setEmail("admin@exemplo.com");
            admin.setSenha(encoder.encode("admin123"));
            admin.setAtivo(true);
            admin.setPapeis(Set.of(adminRole));
            usuarioRepository.save(admin);
        }

        if (usuarioRepository.findByEmail("user@exemplo.com").isEmpty()) {
            Usuario user = new Usuario();
            user.setEmail("user@exemplo.com");
            user.setSenha(encoder.encode("user123"));
            user.setAtivo(true);
            user.setPapeis(Set.of(userRole));
            usuarioRepository.save(user);
        }
    }
}
