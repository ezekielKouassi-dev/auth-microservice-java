package com.auth.twofactor.domain;

import com.auth.twofactor.enums.ERole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import static java.util.Collections.singleton;

/**
 * Entité utilisateur
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = Utilisateur.TABLE_NAME)
public class Utilisateur extends AbstractEntity {

    public static final String TABLE_NAME = "utilisateur";
    public static final String TABLE_ID = TABLE_NAME + "_id";
    public static final String TABLE_SEQ = TABLE_ID + "_seq";

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = TABLE_SEQ, sequenceName = TABLE_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_SEQ)
    private Long id;

    private String nomPrenoms;

    private String username;

    private String email;

    private boolean actif;

    private String password;

    @Enumerated(EnumType.STRING)
    private ERole role;

    public Utilisateur(String password, ERole role) {
        this.password = password;
        this.actif = true;
        this.role = role;
    }

    /**
     * Constructeur par  défaut
     */
    public Utilisateur() {
    }

    public Utilisateur(String nomPrenoms, String username, String email, String password, ERole role) {
        this.nomPrenoms = nomPrenoms;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.actif = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomPrenoms() {
        return nomPrenoms;
    }

    public void setNomPrenoms(String nomPrenoms) {
        this.nomPrenoms = nomPrenoms;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    public User buildUser() {
        return new User(username, password, singleton(new SimpleGrantedAuthority(role.name())));
    }
}
