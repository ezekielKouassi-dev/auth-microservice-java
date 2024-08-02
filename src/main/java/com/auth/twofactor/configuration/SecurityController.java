package com.auth.twofactor.configuration;

import com.auth.twofactor.presentation.TokenVo;
import com.auth.twofactor.presentation.UsernamePasswordVo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/securite")
public class SecurityController {

	private final SecurityService securityService;

	public SecurityController(SecurityService securityService) {
		this.securityService = securityService;
	}

	@PostMapping("/auth")
	public TokenVo authentifierUtilisateur(@RequestBody UsernamePasswordVo usernamePasswordVo) throws Exception {
		return securityService.authentifierUtilisateur(usernamePasswordVo);
	}
}
