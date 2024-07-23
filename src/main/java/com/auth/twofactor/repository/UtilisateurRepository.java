package com.auth.twofactor.repository;

import com.auth.twofactor.domain.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

	Optional<Utilisateur> findByUsername(String username);
	Optional<List<Utilisateur>> findByRole(String code);
	Optional<Utilisateur> findByEmail(String email);
	Optional<Utilisateur> findByCodeOtp(String code);
}

