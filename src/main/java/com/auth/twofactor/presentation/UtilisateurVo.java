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
    private String nom;

    @NotNull
    private String prenoms;

    @NotNull
    private String username;

    private boolean actif;

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
        this.nom = utilisateur.getNom();
        this.prenoms = utilisateur.getPrenoms();
        this.username = utilisateur.getUsername();
        this.actif = utilisateur.isActif();
        this.password = utilisateur.getPassword();
    }

    public UtilisateurVo(Long id, String nom, String prenoms, String username, String password) {
        this.id = id;
        this.nom = nom;
        this.prenoms = prenoms;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public String getUsername() {
        return username;
    }

    public boolean isActif() {
        return actif;
    }

    public String getPassword() {
        return password;
    }
}
