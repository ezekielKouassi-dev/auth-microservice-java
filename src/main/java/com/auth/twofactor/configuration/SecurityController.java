package com.auth.twofactor.configuration;

import com.auth.twofactor.presentation.TokenVo;
import com.auth.twofactor.presentation.UsernamePasswordVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/securite")
public class SecurityController {

	private final SecurityService securityService;

	public SecurityController(SecurityService securityService) {
		this.securityService = securityService;
	}

	@PostMapping("/auth")
	public void authentifierPourLaPremiereFois(@RequestBody UsernamePasswordVo usernamePasswordVo) throws Exception {
		securityService.authentifierUtilisateurPourLaPremiereFois(usernamePasswordVo);
	}

	@PostMapping("/auth2")
	public TokenVo authentifierPourLaSecondeFois(@RequestParam(name = "code") String code) throws Exception {
		return securityService.authentifierUtilisateurPourLaSecondeFois(code);
	}
}
