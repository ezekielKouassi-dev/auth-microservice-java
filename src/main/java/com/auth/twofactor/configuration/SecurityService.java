package com.auth.twofactor.configuration;

import com.auth.twofactor.domain.Utilisateur;
import com.auth.twofactor.presentation.CodeOTPVo;
import com.auth.twofactor.presentation.GenerationCodeOTPVo;
import com.auth.twofactor.presentation.TokenVo;
import com.auth.twofactor.presentation.UsernamePasswordVo;
import com.auth.twofactor.presentation.UtilisateurVo;
import com.auth.twofactor.repository.UtilisateurRepository;
import com.auth.twofactor.service.GenererCodeOTPService;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

import static java.util.List.of;

@Service
public class SecurityService implements UserDetailsService {

	private final JwtTokenUtils jwtTokenUtils;
	private final UtilisateurRepository utilisateurRepository;
	private final GenererCodeOTPService genererCodeOTPService;

	public SecurityService(JwtTokenUtils jwtTokenUtils, UtilisateurRepository utilisateurRepository, GenererCodeOTPService genererCodeOTPService) {
		this.jwtTokenUtils = jwtTokenUtils;
		this.utilisateurRepository = utilisateurRepository;
		this.genererCodeOTPService = genererCodeOTPService;
	}

	/**
	 * Génère le Token JWT du utilisateur.
	 *
	 * @param utilisateurVo le utilisateur.
	 * @return le Token JWT de du utilisateur.
	 */
	private String genererTokenJwt(UtilisateurVo utilisateurVo) {
		return jwtTokenUtils.generateToken(utilisateurVo);
	}

	/**
	 * Récupère un utilisateur à partir de son username.
	 *
	 * @param username le username de l'utilisateur.
	 * @return L'utilisateur.
	 * @throws UsernameNotFoundException Exception levé lorsqu'aucun utilisateur ne correspond à cet username.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByUsername(username);
		if (optionalUtilisateur.isPresent()) {
			return optionalUtilisateur.get().buildUser();
		} else {
			throw new UsernameNotFoundException("Aucun utilisateur trouvé pour le username: " + username);
		}
	}

	/**
	 * Récupère un utilisateur à partir de son email et de son password.
	 *
	 * @param username    l'email de l'utilisateur.
	 * @param password le password de l'utilisateur.
	 * @return L'utilisateur.
	 * @throws UsernameNotFoundException Exception levé lorsqu'aucun utilisateur ne correspond à ce email.
	 */
	private Utilisateur rechercherutilisateurParUsernameEtPassword(String username, String password) throws UsernameNotFoundException {
		Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByUsername(username);
		if (utilisateurOptional.isPresent()) {
			Utilisateur utilisateur = utilisateurOptional.get();
			if (this.comparerPassword(password, utilisateur.getPassword())) {
				return utilisateur;
			}
		}
		throw new UsernameNotFoundException("Aucun utilisateur trouvé pour le username: " + username);
	}

	@Transactional
	public void authentifierUtilisateurPourLaPremiereFois(UsernamePasswordVo usernamePasswordVo) throws Exception {
		try {
			Utilisateur utilisateur = rechercherutilisateurParUsernameEtPassword(usernamePasswordVo.getUsername(), usernamePasswordVo.getPassword());
			GenerationCodeOTPVo generationCodeOTPVo = new GenerationCodeOTPVo(utilisateur.getEmail());
			CodeOTPVo codeOTPVo = genererCodeOTPService.genererCodeOtp(generationCodeOTPVo);
			utilisateur.setCodeOtp(codeOTPVo.getCode());
			utilisateurRepository.save(utilisateur);
		}
		catch (Exception e) {
			throw new Exception();
		}

	}

	public TokenVo genererToken(UtilisateurVo utilisateurVo) {
		String token = genererTokenJwt(utilisateurVo);
		return new TokenVo(StringUtils.join(of("Bearer", token), " "));
	}

	/**
	 * Compare les mots de passe.
	 *
	 * @param password       le mot de passe.
	 * @param encodePassword le mot de passe crypté.
	 * @return le mot de passe crypté.
	 */
	private boolean comparerPassword(String password, String encodePassword) {
		return getEncoder().matches(password, encodePassword);
	}

	private boolean comparerCodeOtp(String codeUtilisateur, String vraiCode) {
		return codeUtilisateur.equals(vraiCode);
	}

	/**
	 * Crypte le mot de passe de l'utilisateur.
	 *
	 * @param password le mot de passe.
	 * @return le mot de passe crypté.
	 */
	public String crypterPassword(String password) {
		return getEncoder().encode(password);
	}

	private BCryptPasswordEncoder getEncoder() {
		int strength = 10;
		return new BCryptPasswordEncoder(strength, new SecureRandom());
	}

	public TokenVo authentifierUtilisateurPourLaSecondeFois(String code) throws Exception {
		Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByCodeOtp(code);

		if (utilisateurOptional.isPresent()) {
			Utilisateur utilisateur = utilisateurOptional.get();
			if (comparerCodeOtp(code, utilisateur.getCodeOtp())) {
				return genererToken(new UtilisateurVo(utilisateur));
			}
			throw new Exception();
		}
		return null;
	}
}
