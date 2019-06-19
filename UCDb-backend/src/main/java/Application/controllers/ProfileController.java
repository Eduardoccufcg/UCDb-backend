package Application.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Application.model.Profile;
import Application.services.ProfileService;

@RestController
@RequestMapping({"v1/profiles"})
public class ProfileController {
	
	private ProfileService profileService;
	
	public ProfileController(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	@PostMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<Profile> create(@PathVariable long id,@RequestBody Profile profile){
		return new ResponseEntity<Profile>(this.profileService.create(id,profile),HttpStatus.CREATED);
		
	}
	

}
