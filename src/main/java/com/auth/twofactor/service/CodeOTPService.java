package com.auth.twofactor.service;

import com.auth.twofactor.presentation.CodeOTPVo;
import com.auth.twofactor.presentation.GenerationCodeOTPVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CodeOTPService {

	@Value("${microservice.url.code-otp}")
	private String urlGenerationCodeOTP;

	@Value("${microservice.url.verify-code-otp}")
	private String urlVerificationCodeOTP;

	/**
	 * Génère le code OTP.
	 *
	 * @param generationCodeOTPVo Vo pour la génération du code OTP.
	 */
	public void envoyerCodeOtp(GenerationCodeOTPVo generationCodeOTPVo) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();

		HttpEntity<Object> entity = new HttpEntity<>(generationCodeOTPVo, httpHeaders);

		ResponseEntity<String> response = restTemplate.exchange(urlGenerationCodeOTP, HttpMethod.POST, entity, String.class);
		HttpStatus status = response.getStatusCode();

		if (status != HttpStatus.OK) {
			// TODO: créer une exception personnalisé
			throw new Exception("erreur code introuvable");
		}

	}

	// TODO: docs à faire
	public void verifierCodeOTP(String code) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();

		HttpEntity<Object> entity = new HttpEntity<>(new CodeOTPVo(code), httpHeaders);

		ResponseEntity<String> response = restTemplate.exchange(urlVerificationCodeOTP, HttpMethod.POST, entity, String.class);
		HttpStatus status = response.getStatusCode();

		if (status != HttpStatus.OK) {
			// TODO: créer une exception personnalisé
			throw new Exception("erreur code introuvable");
		}

	}
}
