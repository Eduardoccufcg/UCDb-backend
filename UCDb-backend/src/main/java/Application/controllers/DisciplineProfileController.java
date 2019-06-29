package Application.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Application.model.Comment;
import Application.model.DisciplineProfile;
import Application.services.DisciplineProfileService;

@RestController
@RequestMapping({ "v1/profiles" })
public class DisciplineProfileController {

	private DisciplineProfileService disciplineProfileService;

	public DisciplineProfileController(DisciplineProfileService profileService) {

		this.disciplineProfileService = profileService;
	}

	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<Iterable<DisciplineProfile>> create(
			@RequestBody Iterable<DisciplineProfile> Disciplineprofile) {
		return new ResponseEntity<Iterable<DisciplineProfile>>(this.disciplineProfileService.create(Disciplineprofile),
				HttpStatus.CREATED);

	}

	@GetMapping(value = "/{id}/{email}")
	@ResponseBody
	public ResponseEntity<DisciplineProfile> get(@PathVariable long id, @PathVariable String email) {
		return new ResponseEntity<DisciplineProfile>(this.disciplineProfileService.getProfile(id, email),
				HttpStatus.OK);

	}

	@SuppressWarnings({ "rawtypes" })
	@GetMapping("/search")
	@ResponseBody
	public ResponseEntity<List> getBySubstring(@RequestParam(name = "substring") String substring) {

		List discipline = disciplineProfileService.findBySubstring(substring.toUpperCase());

		return new ResponseEntity<List>(discipline, HttpStatus.OK);
	}

	@PostMapping(value = "/like/{profile}/{email}")
	@ResponseBody
	public ResponseEntity<DisciplineProfile> createLike(@PathVariable String email, @PathVariable Long profile) {

		return new ResponseEntity<DisciplineProfile>(this.disciplineProfileService.toLike(email, profile),
				HttpStatus.CREATED);

	}

	@PostMapping(value = "/comment/{profile}/{email}")
	@ResponseBody
	public ResponseEntity<DisciplineProfile> usuarioComentou(@PathVariable long profile, @PathVariable String email,
			@RequestBody Comment comment) {

		return new ResponseEntity<DisciplineProfile>(this.disciplineProfileService.toComment(profile, email, comment),
				HttpStatus.OK);
	}
}
