package com.auth.twofactor.presentation;

import com.auth.twofactor.domain.Utilisateur;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * VO des utilisateurs
 */
public class UtilisateurVo {
    private Long id;

    @NotNull
    private String nomPrenoms;

    @NotNull
    private String username;

    private String email;

    @NotBlank
    private String password;

    /**
     * Constructeur par défaut.
     */
    public UtilisateurVo() {
    }

    /**
     * Constructeur paramétré
     *
     * @param utilisateur l'utilisateur
     */
    public UtilisateurVo(Utilisateur utilisateur) {
        this.id = utilisateur.getId();
        this.nomPrenoms = utilisateur.getNomPrenoms();
        this.username = utilisateur.getUsername();
        this.email = utilisateur.getEmail();
        this.password = utilisateur.getPassword();
    }

    public UtilisateurVo(Long id, String nomPrenoms, String username, String password) {
        this.id = id;
        this.nomPrenoms = nomPrenoms;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getNomPrenoms() {
        return nomPrenoms;
    }

    public void setNomPrenoms(@NotNull String nomPrenoms) {
        this.nomPrenoms = nomPrenoms;
    }

    public void setUsername(@NotNull String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
