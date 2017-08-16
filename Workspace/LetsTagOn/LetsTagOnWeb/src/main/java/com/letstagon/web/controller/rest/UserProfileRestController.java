package com.letstagon.web.controller.rest;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.letstagon.dao.model.User;
import com.letstagon.exception.NonFatalException;
import com.letstagon.facade.UserExperienceFacade;
import com.letstagon.facade.UserFacade;
import com.letstagon.facade.UserInterestFacade;
import com.letstagon.facade.UserPersonalInformationFacade;
import com.letstagon.facade.dto.AjaxErrorDTO;
import com.letstagon.facade.dto.AjaxResponseDTO;
import com.letstagon.facade.dto.CauseDTO;
import com.letstagon.facade.dto.JobTypeDTO;
import com.letstagon.facade.dto.PaginatedResponseDTO;
import com.letstagon.facade.dto.PartyCauseXrefDTO;
import com.letstagon.facade.dto.PartyJobTypeXrefDTO;
import com.letstagon.facade.dto.ProfileCompletionStatusDTO;
import com.letstagon.facade.dto.UserDTO;
import com.letstagon.facade.dto.UserExperienceDTO;
import com.letstagon.facade.dto.UserTypeXrefDTO;
import com.letstagon.web.constant.LetsTagOnwebConstants.SearchConstans;
import com.letstagon.web.controller.ControllerConstants;
import com.letstagon.web.session.LtoSessionService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserProfileRestController.
 */
@RestController(value = "/profile")
public class UserProfileRestController {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(UserProfileRestController.class);

	/** The user experience facade. */
	@Autowired
	private UserExperienceFacade userExperienceFacade;

	/** The user interest facade. */
	@Autowired
	private UserInterestFacade userInterestFacade;

	/** The personal information facade. */
	@Autowired
	private UserPersonalInformationFacade personalInformationFacade;

	/** The user facade. */
	@Autowired
	private UserFacade userFacade;

	/** The lto session service. */
	@Autowired
	private LtoSessionService ltoSessionService;
	
	/** The http session. */
	@Autowired
	private HttpSession httpSession;

	/**
	 * Gets the user details.
	 *
	 * @param userId the user id
	 * @return the user details
	 */
	@RequestMapping(value = "/profile/user/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResponseDTO getUserDetails(@PathVariable long userId) {
		LOG.info("Registartion process initiated for a customer name:" + userId);
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		try {
			HashMap<String, Object> response = personalInformationFacade
					.getUserDetails(ltoSessionService.getLoggedInUser().getId());
			responseDTO.setData(response);
		} catch (UsernameNotFoundException une) {
			LOG.info("User Name not exist: " + userId);
			AjaxErrorDTO errorDTO = new AjaxErrorDTO();
			errorDTO.setErrorCode(ControllerConstants.ErrorCodes.BAD_REQUEST);
			errorDTO.setErrorMessage(ControllerConstants.ErrorMessages.RESOURCE_NOT_FOUND);
			responseDTO.setError(errorDTO);
		}

		return responseDTO;

	}

	/**
	 * Complete profile of user.
	 *
	 * @param user the user
	 * @return the ajax response DTO
	 * @throws DataAccessException the data access exception
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/profile/user/saveOrUpdate/personalInformation", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponseDTO completeProfileOfUser(@RequestBody UserDTO user) throws DataAccessException, Exception {
		LOG.info("complete process initiated for a customer name:" + user.getName() + " and with email:"
				+ user.getEmailAddress());
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		UserDTO userDTO = personalInformationFacade.saveOrUpdateProfileForm(user);
		responseDTO.setData(userDTO);
		LOG.info("complete process completed for a customer name:" + user.getName() + " and with email:"
				+ user.getEmailAddress() + " and userId is:" + user.getId());

		return responseDTO;

	}

	/**
	 * Capture user experience details.
	 *
	 * @param user the user
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/profile/user/saveOrUpdate/userExperience", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponseDTO captureUserExperienceDetails(@RequestBody UserExperienceDTO user) {
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		UserExperienceDTO userExperienceDTO = userExperienceFacade.saveExperienceDetailsOfUser(user);
		responseDTO.setData(userExperienceDTO);

		return responseDTO;

	}

	/**
	 * Gets the volunteer interest details.
	 *
	 * @param userId the user id
	 * @return the volunteer interest details
	 */
	@RequestMapping(value = "/profile/interests/user/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResponseDTO getVolunteerInterestDetails(@PathVariable long userId) {
		LOG.info("process to get user volunteer histroy page details for user with id:" + userId);
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		HashMap<String, Object> map = userInterestFacade
				.getVolunteerInterestDetails(ltoSessionService.getLoggedInUser().getId());
		responseDTO.setData(map);
		LOG.info(
				"process to get user volunteer histroy page details for user successfully completed with id:" + userId);

		return responseDTO;
	}

	/**
	 * Gets the user experience details.
	 *
	 * @param userId the user id
	 * @return the user experience details
	 */
	@RequestMapping(value = "/profile/experience/user/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResponseDTO getUserExperienceDetails(@PathVariable long userId) {
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		HashMap<String, Object> map = userExperienceFacade
				.getUserExperienceList(ltoSessionService.getLoggedInUser().getId());
		responseDTO.setData(map);
		return responseDTO;
	}

	/**
	 * Capture user skills.
	 *
	 * @param jobType the job type
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/profile/user/save/skills", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponseDTO captureUserSkills(@RequestBody List<PartyJobTypeXrefDTO> jobType) {
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		List<JobTypeDTO> userSkills = userInterestFacade.saveSkillsOfUser(jobType);
		responseDTO.setData(userSkills);

		return responseDTO;
	}

	/**
	 * Capture user causes.
	 *
	 * @param causeXrefDTOs the cause xref DT os
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/profile/user/save/causes", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponseDTO captureUserCauses(@RequestBody List<PartyCauseXrefDTO> causeXrefDTOs) {
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		List<CauseDTO> userSkills = userInterestFacade.saveCausesOfUser(causeXrefDTOs);
		responseDTO.setData(userSkills);

		return responseDTO;
	}

	/**
	 * Capture user type.
	 *
	 * @param userTypeXrefDTO the user type xref DTO
	 * @param userId the user id
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/profile/user/save/userType/{userId}", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponseDTO captureUserType(@RequestBody UserTypeXrefDTO userTypeXrefDTO, @PathVariable long userId) {
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		try {
			HashMap<String, Object> typeXrefDTO = userInterestFacade.saveOrUpdateUserType(userTypeXrefDTO,
					ltoSessionService.getLoggedInUser().getId());
			responseDTO.setData(typeXrefDTO);
		} catch (NonFatalException de) {
			LOG.info("DataAccessException. Some issue in saving user causes.");
			AjaxErrorDTO errorDTO = new AjaxErrorDTO();
			errorDTO.setErrorCode(ControllerConstants.ErrorCodes.RESOURCE_NOT_FOUND);
			errorDTO.setErrorMessage(de.getLocalizedMessage());
			responseDTO.setError(errorDTO);
		}
		return responseDTO;
	}

	/**
	 * Gets the user profile information.
	 *
	 * @param userId the user id
	 * @return the user profile information
	 */
	@RequestMapping(value = "/profile/user/info/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResponseDTO getUserProfileInformation(@PathVariable long userId) {
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();

		HashMap<String, Object> userProfileMap = userFacade.getUserProfileDetails(userId);
		responseDTO.setData(userProfileMap);

		return responseDTO;

	}

	/**
	 * Gets the user list.
	 *
	 * @param userId the user id
	 * @param page the page
	 * @param size the size
	 * @return the user list
	 */
	@RequestMapping(value = "/profile/user/{userId}/suggestion", method = RequestMethod.GET)
	@ResponseBody
	public PaginatedResponseDTO getUserList(@PathVariable long userId,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = SearchConstans.PAGE_SIZE_DEFAULT_STRING) int size) {
		UserDTO volunteer = new UserDTO(ltoSessionService.getLoggedInUser().getId());
		return userFacade.getUserList(volunteer, page, size);

	}

	/**
	 * Save or update user additional attributes.
	 *
	 * @param user the user
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/profile/user/save/additional/attributes", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponseDTO saveOrUpdateUserAdditionalAttributes(@RequestBody UserDTO user) {
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		try {
			userFacade.saveAdditionalAttributesValuesForUser(user);
			responseDTO.setData("Save Successfull");
		} catch (NonFatalException e) {
			LOG.info("DataAccessException. Some issue in saving user causes.");
			AjaxErrorDTO errorDTO = new AjaxErrorDTO();
			errorDTO.setErrorCode(ControllerConstants.ErrorCodes.RESOURCE_NOT_FOUND);
			errorDTO.setErrorMessage(e.getLocalizedMessage());
			responseDTO.setError(errorDTO);
		}
		return responseDTO;
	}

	/**
	 * Gets the user details.
	 *
	 * @return the user details
	 */
	@RequestMapping(value = "/profile/user/completion", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResponseDTO getUserDetails() {
		AjaxResponseDTO responseDTO = new AjaxResponseDTO();
		ProfileCompletionStatusDTO response = personalInformationFacade
				.getProfileCompletionStatus(ltoSessionService.getLoggedInUser().getId());
		responseDTO.setData(response);

		return responseDTO;

	}
	
	/**
	 * Gets the linked in professional details.
	 *
	 * @return the linked in professional details
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/profile/linkedin/session/professionalDetail", method = RequestMethod.GET)
	public AjaxResponseDTO getLinkedInProfessionalDetails() {
		AjaxResponseDTO response = new AjaxResponseDTO();
		User user = ltoSessionService.getLoggedInUser();
		HashMap<String, Object> details = (HashMap<String, Object>) httpSession.getAttribute("profDetails");
		if(!details.isEmpty()){
			User userDetails = (User) details.get("loggedInUser");
			if(userDetails != null){
				if(user.equals(userDetails)){
					List<UserExperienceDTO> experienceList = (List<UserExperienceDTO>) details.get("linkedInProfessionalDetails");
					response.setData(experienceList);
				}
			}
		}		
		return response;
	}
	

	/**
	 * Save professional details from linked in.
	 *
	 * @param experience the experience
	 * @return the ajax response DTO
	 */
	@RequestMapping(value = "/profile/linkedin/save/professionalDetail", method = RequestMethod.POST)
	public AjaxResponseDTO saveProfessionalDetailsFromLinkedIn(@RequestBody List<UserExperienceDTO> experience) {
		AjaxResponseDTO response = new AjaxResponseDTO();
		UserDTO user = new UserDTO(ltoSessionService.getLoggedInUser().getId());
		response.setData(userExperienceFacade.saveProfessionalExperienceDetailsOfUser(experience, user));
		return response;
	}
	

}
