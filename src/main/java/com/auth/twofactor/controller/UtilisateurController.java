package com.auth.twofactor.controller;

import com.auth.twofactor.facade.UtilisateurFacade;
import com.auth.twofactor.presentation.UtilisateurVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

	private final UtilisateurFacade utilisateurFacade;

	public UtilisateurController(UtilisateurFacade utilisateurFacade) {
		this.utilisateurFacade = utilisateurFacade;
	}

	// TODO: docs à faire
	@PostMapping("/enregistrer")
	public void enregistrer(@RequestBody UtilisateurVo utilisateurVo) throws Exception {
		utilisateurFacade.enregistrer(utilisateurVo);
	}

	// TODO: docs à faire
	@PostMapping("/valider-otp")
	public void validerOtp(@RequestParam(name = "code") String code) throws Exception {
		utilisateurFacade.validerCodeOtp(code);
	}
}
