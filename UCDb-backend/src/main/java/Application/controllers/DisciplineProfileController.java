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

import Application.model.DisciplineProfile;
import Application.model.Grade;
import Application.services.DisciplineProfileService;
import Application.services.GradeService;

@RestController
@RequestMapping({ "v1/profiles" })
public class DisciplineProfileController {

	private DisciplineProfileService disciplineProfileService;

	private GradeService gradeService;

	public DisciplineProfileController(DisciplineProfileService profileService, GradeService gradeService) {
		this.gradeService = gradeService;
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
	public ResponseEntity<DisciplineProfile> get(@PathVariable long id,@PathVariable String email) {
		return new ResponseEntity<DisciplineProfile>(this.disciplineProfileService.getProfile(id,email), HttpStatus.OK);

	}

	@SuppressWarnings({ "rawtypes" })
	@GetMapping("/search")
	@ResponseBody
	public ResponseEntity<List> getBySubstring(@RequestParam(name = "substring") String substring) {

		List discipline = disciplineProfileService.findBySubstring(substring.toUpperCase());

		return new ResponseEntity<List>(discipline, HttpStatus.OK);
	}

	@PostMapping(value = "/grade/{idPerfil}/{email}")
	@ResponseBody
	public ResponseEntity<DisciplineProfile> createGrade(@PathVariable String email, @PathVariable Long idPerfil,
			@RequestBody Grade grade) {

		return new ResponseEntity<DisciplineProfile>(this.gradeService.create(idPerfil, email, grade), HttpStatus.CREATED);

	}
	@PostMapping(value = "/like/{idPerfil}/{email}")
	@ResponseBody
	public ResponseEntity<DisciplineProfile> createLike(@PathVariable String email, @PathVariable Long idPerfil) {

		return new ResponseEntity<DisciplineProfile>(this.disciplineProfileService.toLike(email, idPerfil), HttpStatus.CREATED);

	}
}
