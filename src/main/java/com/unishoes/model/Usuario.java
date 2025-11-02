package com.unishoes.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String nome;
    private String senha;

    private boolean ativo = true;
    
    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuario_papel",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "papel_id")
    )
    private Set<Papel> papeis = new HashSet<>();

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setAtivo(boolean ativo) {
	    this.ativo = ativo;
	}

	public void setPapeis(Set<Papel> papeis) {
	    this.papeis = papeis;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public boolean isAtivo() {
		return ativo;
	}
	
	public Set<Papel> getPapeis() {
	    return papeis;
	}
}
