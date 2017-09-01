package com.letstagon.web.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.letstagon.dao.model.User;
import com.letstagon.enums.UserTypeEnum;
import com.letstagon.facade.UserExperienceFacade;
import com.letstagon.facade.UserFacade;
import com.letstagon.facade.dto.SocialAppTypeDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.facade.dto.UserExperienceDTO;
import com.letstagon.facade.dto.UserSocialConnectDTO;
import com.letstagon.facade.dto.linkedin.LinkedInProfessionalExperienceDTO;
import com.letstagon.facade.dto.linkedin.LinkedinUserProfileDTO;
import com.letstagon.web.constant.LetsTagOnwebConstants.UrlConstants;
import com.letstagon.web.dto.LinkedInOauthResponseDTO;
import com.letstagon.web.session.LtoSessionService;

// TODO: Auto-generated Javadoc
/**
 * The Class LinkedinAuthController.
 */
@Controller
public class LinkedinAuthController {

	/** The grant type. */
	@Value("${security.oauth2.client.grant-type}")
	private String grant_type;
	
	/** The redirect uri. */
	@Value("${security.oauth2.client.pre-established-redirect-uri}")
	private String redirect_uri;
	
	/** The client id. */
	@Value("${security.oauth2.client.client-id}")
	private String client_id;
	
	/** The client secret. */
	@Value("${security.oauth2.client.client-secret}")
	private String client_secret;
	
	/** The access token uri. */
	@Value("${security.oauth2.client.access-token-uri}")
	private String accessTokenUri;

	/** The user auth url. */
	@Value("${security.oauth2.client.user-authorization-uri}")
	private String userAuthUrl;

	/** The user info uri. */
	@Value("${security.oauth2.resource.user-info-uri}")
	private String userInfoUri;

	/** The app url. */
	@Value("${letsTagon.appUrl}")
	private String appUrl;

	/** The user facade. */
	@Autowired
	private UserFacade userFacade;

	/** The user details service. */
	@Autowired
	private UserDetailsService userDetailsService;
	
	/** The user experience facade. */
	@Autowired
	private UserExperienceFacade userExperienceFacade;
	/** The user facade. */

	private UserDTO user ;
	
	/** The user details. */
	UserDetails userDetails ;
	
	/** The professional redirect uri. */
	@Value("${security.oauth2.client.professional-redirect-uri}")
	private String professional_redirect_uri;
	
	/** The user professional uri. */
	@Value("${security.oauth2.resource.user-professional-uri}")
	private String userProfessionalUri;
	
	/** The http session. */
	@Autowired 
	 private HttpSession httpSession;
	
	/** The lto session service. */
	@Autowired
	private LtoSessionService ltoSessionService;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LinkedinAuthController.class);

		
	/**
	 * Linkedin auth return.
	 *
	 * @param model the model
	 * @param request the request
	 * @param code the code
	 * @param state the state
	 * @return the string
	 */
	@RequestMapping(value = "/linkedin/auth", method = RequestMethod.GET)
	public String linkedinAuthReturn(Model model, HttpServletRequest request,
			@RequestParam(name = "code", required = false) String code, @RequestParam String state) {
		LinkedInOauthResponseDTO linkedinResponse =	requestForLogin( model, request,
				code, state,this.redirect_uri);
		///////////////////////////////////
		//fetch professional detail from linked in method call

		//fetchProfessionalDetailFromLinkedin(model, request, code, state);


		//////////////////////////////////




		// fetch user details

		RestTemplate profileRequest = new RestTemplate();

		MultiValueMap<String, String> profReqHeaders = new LinkedMultiValueMap<String, String>();
		profReqHeaders.add("Authorization", "Bearer " + linkedinResponse.getAccess_token());
		HttpEntity profReqEntity = new HttpEntity(profReqHeaders);
		ResponseEntity<LinkedinUserProfileDTO> profileDetailsResponse = profileRequest.exchange(userInfoUri,
				HttpMethod.GET, profReqEntity, LinkedinUserProfileDTO.class);


		LinkedinUserProfileDTO profileDetail = profileDetailsResponse.getBody();
		LOG.info(profileDetail.toString());
		LinkedinUserProfileDTO profileDetails = profileDetailsResponse.getBody();
		LOG.info("Professional detail : " + profileDetails.getFirstName());
		//LOG.info("LOCATION DETAIL : "+ profileDetails.getLocation());
		user = userFacade.getUserDetailsByEmail(profileDetails.getEmailAddress());

		if (user == null) {
			user = new UserDTO();
			////////////////////////////////// address is added mannually not fatching from linked in 


			/////////////////////////////////////////////

			user.setEmailAddress(profileDetails.getEmailAddress());
			user.setName(profileDetails.getFirstName() + " " + profileDetails.getLastName());
			user.setFirstName(profileDetails.getFirstName());
			user.setLastName(profileDetails.getLastName());
			user.setUserName(profileDetails.getEmailAddress());
			user.setPassword(UUID.randomUUID().toString()); // set random
			// password
			user.setUserRole(UserTypeEnum.VOLUNTEER.getName());
			user = userFacade.create(user);

			UserSocialConnectDTO socialConnect = new UserSocialConnectDTO();
			SocialAppTypeDTO socialAppType = new SocialAppTypeDTO();
			socialAppType.setId(com.letstagon.facade.enums.SocialAppType.LINKEDIN.getAppId());
			socialConnect.setSocialAppType(socialAppType);
			socialConnect.setUser(user);
			socialConnect.setSocialAppID(profileDetails.getId());

		}

		userDetails = userDetailsService.loadUserByUsername(user.getUserName());


		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,
				userDetails.getPassword(), userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);

		return UrlConstants.REDIRECT_URL + "/";

	}

	/**
	 * Signin via linked in.
	 *
	 * @return the string
	 */
	@RequestMapping(value = "/linkedin/signin", method = RequestMethod.GET)
	public String signinViaLinkedIn() {

		return UrlConstants.REDIRECT_URL + userAuthUrl + "&redirect_uri=" + this.appUrl + this.redirect_uri + "&state="
				+ RandomUtils.nextInt();

	}

	/**
	 * Request for login.
	 *
	 * @param model the model
	 * @param request the request
	 * @param code the code
	 * @param state the state
	 * @param url the url
	 * @return the linked in oauth response DTO
	 */
	public LinkedInOauthResponseDTO requestForLogin(Model model, HttpServletRequest request,
			@RequestParam(name = "code", required = false) String code, @RequestParam String state,String url){
		LOG.info("Return from Linkedin after auth.");
		LOG.debug("Code from linkedin : " + code);

		RestTemplate rest = new RestTemplate();

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("grant_type", grant_type);
		map.add("code", code);
		map.add("redirect_uri", this.appUrl + url);
		map.add("client_id", client_id);
		map.add("client_secret", client_secret);

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(map,
				requestHeaders);

		ResponseEntity<LinkedInOauthResponseDTO> response = rest.exchange(accessTokenUri, HttpMethod.POST,
				requestEntity, LinkedInOauthResponseDTO.class);

		LOG.info("Response : " + response);

		LinkedInOauthResponseDTO linkedinResponse = response.getBody();
		return linkedinResponse;

	}
	
	/**
	 * Signin via linked in for professional details.
	 *
	 * @return the string
	 */
	@RequestMapping(value = "/linkedin/professionalDetail", method = RequestMethod.GET)
	public String signinViaLinkedInForProfessionalDetails() {
		httpSession.removeAttribute("LinkedInOauthResponseDTO");
		LOG.info("come to redirect link of professional detail");
		return UrlConstants.REDIRECT_URL + userAuthUrl + "&redirect_uri=" + this.appUrl + this.professional_redirect_uri + "&state="
		+ RandomUtils.nextInt();

	}
	
	/**
	 * Fetch professional detail from linkedin.
	 *
	 * @param model the model
	 * @param request the request
	 * @param code the code
	 * @param state the state
	 * @return the string
	 */
	@RequestMapping(value ="/linkedin/importProfessionalDetails", method = RequestMethod.GET)
	public String fetchProfessionalDetailFromLinkedinFirst(Model model, HttpServletRequest request,
			@RequestParam(name = "code", required = false) String code, @RequestParam String state){
		//signinViaLinkedInForProfessionalDetails() ;
		LinkedInOauthResponseDTO linkedinResponse =	requestForLogin( model, request,code, state,this.professional_redirect_uri);
		httpSession.setAttribute("LinkedInOauthResponseDTO", linkedinResponse);
		User user = ltoSessionService.getLoggedInUser();
		userDetails = userDetailsService.loadUserByUsername(user.getUserName());
		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,
				userDetails.getPassword(), userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		return UrlConstants.REDIRECT_URL + "/#/linkedIn/professionalDetails";
	}
	
	

	/**
	 * Fetch professional detail from linkedin.
	 *
	 * @return the string
	 */
	@RequestMapping(value ="/linkedin/importProfessionalDetails/second", method = RequestMethod.GET)

	public String fetchProfessionalDetailFromLinkedin(){
		
		LinkedInOauthResponseDTO linkedinResponse = (LinkedInOauthResponseDTO) httpSession.getAttribute("LinkedInOauthResponseDTO");
		RestTemplate profileRequest = new RestTemplate();
		MultiValueMap<String, String> profReqHeaders = new LinkedMultiValueMap<String, String>();
		HttpEntity profReqEntity = null;
		
		if(linkedinResponse != null){
			if(linkedinResponse.getAccess_token() != null){
				long hours = linkedinResponse.getExpires_in()/3600;
				Calendar calendar1 = Calendar.getInstance();
				
				if(hours >= Calendar.HOUR){
					profReqHeaders.add("Authorization", "Bearer " + linkedinResponse.getAccess_token());
					profReqEntity = new HttpEntity(profReqHeaders);
					
				}else {
					return "expired";
				}
				
			}
		}else {
			return "expired";
		}
		
		httpSession.removeAttribute("profDetails");
		
		ResponseEntity<LinkedInProfessionalExperienceDTO> professionalDetailsResponse = profileRequest.exchange(userProfessionalUri,
				HttpMethod.GET, profReqEntity, LinkedInProfessionalExperienceDTO.class);

		LinkedInProfessionalExperienceDTO professionalDetails = professionalDetailsResponse.getBody();
		HashMap<String, Object> details = new HashMap<String, Object>(); 
		List<UserExperienceDTO> experienceList= userExperienceFacade.getLinkedProfessionalDetails(professionalDetails);
		User user = ltoSessionService.getLoggedInUser();
		details.put("loggedInUser", user);
		details.put("linkedInProfessionalDetails", experienceList);
		httpSession.setAttribute("profDetails", details);
		userDetails = userDetailsService.loadUserByUsername(user.getUserName());
		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,
				userDetails.getPassword(), userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		return UrlConstants.REDIRECT_URL + "/#/linkedIn/professionalDetails";


	}


}
