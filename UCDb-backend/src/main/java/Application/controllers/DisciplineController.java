package Application.controllers;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import Application.model.Discipline;

import Application.services.DisciplineService;


@RestController
@RequestMapping({"/v1/disciplines"})
public class DisciplineController {
	
	private DisciplineService disciplineService;
	
	
	public DisciplineController(DisciplineService disciplineService){
		this.disciplineService = disciplineService;
		
	}
	
	
	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<Discipline> create(@RequestBody Discipline dis) {

	
		return new ResponseEntity<Discipline>(this.disciplineService.create(dis), HttpStatus.CREATED);
	}
	
	@SuppressWarnings({ "rawtypes" })
	@GetMapping(value = "/{substring}")
	@ResponseBody
	public ResponseEntity<List> getAll(@PathVariable String substring) {
		List discipline = disciplineService.findBySubstring(substring);

		return new ResponseEntity<List>(discipline,HttpStatus.OK);
	}
	
	
	
	
	

}
