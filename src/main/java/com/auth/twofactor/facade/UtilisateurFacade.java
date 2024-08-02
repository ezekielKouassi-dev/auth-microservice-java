package com.auth.twofactor.facade;

import com.auth.twofactor.configuration.SecurityService;
import com.auth.twofactor.domain.Utilisateur;
import com.auth.twofactor.enums.ERole;
import com.auth.twofactor.presentation.GenerationCodeOTPVo;
import com.auth.twofactor.presentation.UtilisateurVo;
import com.auth.twofactor.repository.UtilisateurRepository;
import com.auth.twofactor.service.CodeOTPService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UtilisateurFacade {

	private final CodeOTPService codeOTPService;
	private final SecurityService securityService;
	private final UtilisateurRepository utilisateurRepository;

	public UtilisateurFacade(CodeOTPService codeOTPService, SecurityService securityService, UtilisateurRepository utilisateurRepository) {
		this.codeOTPService = codeOTPService;
		this.securityService = securityService;
		this.utilisateurRepository = utilisateurRepository;
	}

	// TODO: docs à faire
	@Transactional
	public void enregistrer(UtilisateurVo utilisateurVo) throws Exception {
		// On est dans le cas d'un enregistrement si on n'a pas d'identifiant dans le vo
		if (utilisateurVo.getId() == null) {
			// On commence par chiffrer le mot de passe.
			String motDePasseCrypter = securityService.crypterPassword(utilisateurVo.getPassword());

			// On crée un nouvel utilisateur avec les bonnes informations
			Utilisateur utilisateurASauvegarder = new Utilisateur(
					utilisateurVo.getNomPrenoms(),
					utilisateurVo.getUsername(),
					utilisateurVo.getEmail(),
					motDePasseCrypter,
					ERole.ADMIN
			);

			// On peut maintenant créer l'utilisateur
			utilisateurRepository.save(utilisateurASauvegarder);

			// On envoie le code otp
			codeOTPService.envoyerCodeOtp(new GenerationCodeOTPVo(utilisateurVo.getEmail()));
		}
	}

	// TODO: docs à faire
	public void validerCodeOtp(String code) throws Exception {
		codeOTPService.verifierCodeOTP(code);
	}
}
