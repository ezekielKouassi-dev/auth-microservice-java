package com.auth.twofactor.service;

import com.auth.twofactor.presentation.CodeOTPVo;
import com.auth.twofactor.presentation.GenerationCodeOTPVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GenererCodeOTPService {

	@Value("${microservice.url.code-otp}")
	private String urlGenerationCodeOTP;

	/**
	 * Génère le code OTP.
	 *
	 * @param generationCodeOTPVo Vo pour la génération du code OTP.
	 * @return le vo contenant le code OTP.
	 */
	public CodeOTPVo genererCodeOtp(GenerationCodeOTPVo generationCodeOTPVo) throws JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();

		HttpEntity<Object> entity = new HttpEntity<>(generationCodeOTPVo, httpHeaders);

		ResponseEntity<String> response = restTemplate.exchange(urlGenerationCodeOTP, HttpMethod.POST, entity, String.class);
		String responseBody = response.getBody();

		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.readValue(responseBody, CodeOTPVo.class);
	}
}
