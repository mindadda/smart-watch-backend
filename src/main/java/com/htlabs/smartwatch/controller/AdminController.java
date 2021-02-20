package com.htlabs.smartwatch.controller;

import com.htlabs.smartwatch.dto.CountryDTO;
import com.htlabs.smartwatch.dto.ResponseDTO;
import com.htlabs.smartwatch.dto.ResponseUserIdDTO;
import com.htlabs.smartwatch.dto.UserDetailsDTO;
import com.htlabs.smartwatch.exceptions.UserException;
import com.htlabs.smartwatch.service.CountryService;
import com.htlabs.smartwatch.service.UserService;
import com.htlabs.smartwatch.utils.ErrorMessages;
import com.htlabs.smartwatch.utils.Roles;
import com.htlabs.smartwatch.utils.SuccessMessages;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Validated
@Slf4j
@Component
public class AdminController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private CountryService countryService;

    @ApiOperation(value = "Create a user on signup. And the roles is assigned based on the path variable 'role'." +
            " Roles available currently "
            + ": {ADMIN, USER}. Value should  be used in smallcases in the URL")
    @PostMapping(path = "/{role}/signup", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseUserIdDTO signUpUser(@PathVariable String role,
                                        @RequestParam String name,
                                        @RequestParam String email,
                                        @RequestParam String phoneNo,
                                        @RequestParam String password)
            throws UserException {
        try {

            String roleString = Roles.valueOf(role.toUpperCase()).name();
            verifyAdminPrivilegeForAdminCreation(roleString);

            UserDetailsDTO dto = new UserDetailsDTO();
            dto.setName(name);
            dto.setEmail(email);
            dto.setPhoneNo(phoneNo);
            dto.setPassword(password);

            String userId = userService.createUser(dto, roleString);
            return new ResponseUserIdDTO(HttpStatus.OK.value(), userId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    String.format(ErrorMessages.INVALID_ROLE_TYPE));
        }
    }

    private void verifyAdminPrivilegeForAdminCreation(String roleString) {
        Authentication auth = getAuthentication();
        if (roleString.equals(Roles.ADMIN.name()) && (auth == null || auth.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals(Roles.ADMIN.getRoleValue()))))
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    String.format(ErrorMessages.PERMISSION_PROHIBITED, roleString));
    }

    @ApiOperation(value = "Authenticates the sign in and returns the .")
    @PostMapping(path = "/signin", produces = { MediaType.APPLICATION_JSON_VALUE })
    public UserDetailsDTO signInUser(@RequestParam(required = false) String userEmail,
                                     @RequestParam(required = false) String userPhoneNo)
            throws UserException {
        if (StringUtils.isNotEmpty(userPhoneNo)) {
            return userService.userAuthenticationByPhone(userPhoneNo);
        } else if (StringUtils.isNotEmpty(userEmail)) {
            return userService.userAuthenticationByEmail(userEmail);
        }
        throw new UserException(ErrorMessages.PHONE_OR_MAIL_EMPTY);
    }

    @ApiOperation(value = "We can find details of all the users.")
    @GetMapping(path = "/findAllUsers", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<UserDetailsDTO>> getUserDetails() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @ApiOperation(value = "We can find details of the users.")
    @GetMapping(path = "/findUserById", produces = { MediaType.APPLICATION_JSON_VALUE })
    public UserDetailsDTO getUserDetailsById(@RequestParam String userId) {
        return userService.getUserDetailsById(userId);
    }

    @ApiOperation(value = "We can create a new Country.")
    @PostMapping(path = "/createCountry", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseDTO createCountry(@RequestParam String countryName ) {
        countryService.createCountry(countryName);
        return new ResponseDTO(HttpStatus.OK.value(), String.format(SuccessMessages.COUNTRY_CREATED, countryName));
    }

    @ApiOperation(value = "We can update details of the Country.")
    @PostMapping(path = "/updateCountry" , produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseDTO updateCountry(@RequestParam String countryId ,
                                     @RequestParam String countryName){
        countryService.updateCountry(countryId , countryName);
        return new ResponseDTO(HttpStatus.OK.value(), String.format(SuccessMessages.COUNTRY_UPDATED, countryName));
    }

    @ApiOperation(value = "Get details of Country")
    @GetMapping(path = "/findAllCountries", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<CountryDTO> getAllCountries() {
        return countryService.getAllCountries();
    }

    @ApiOperation(value = "We can find details of the Country.")
    @GetMapping(path = "/findCountryById", produces = { MediaType.APPLICATION_JSON_VALUE })
    public CountryDTO getCountryById(@RequestParam String countryId) {
        return countryService.getCountryById(countryId);
    }

    @ApiOperation(value = "We can find details of the Country.")
    @GetMapping(path = "/findCountryByName", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<CountryDTO> getCountryByName(@RequestParam String countryName) {
        return countryService.getCountryByName(countryName);
    }

    @ApiOperation(value = "Delete country countryId")
    @GetMapping(path = "/deleteCountry",produces ={MediaType.APPLICATION_JSON_VALUE} )
    public ResponseDTO deleteCountry(@RequestParam String countryId ){
        countryService.deleteCountry(countryId);
        return new ResponseDTO(HttpStatus.OK.value(), String.format(SuccessMessages.COUNTRY_REMOVED));

    }

}
