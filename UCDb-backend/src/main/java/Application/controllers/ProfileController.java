package Application.controllers;


import javax.servlet.ServletRequest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Application.model.Comment;

import Application.model.Profile;

import Application.model.RankingDTOList;
import Application.services.CommentService;
import Application.services.ProfileService;


@RestController
@RequestMapping({ "v1/profiles" })
public class ProfileController {

	private ProfileService profileService;

	private CommentService commentService;

	public ProfileController(ProfileService profileService, CommentService commentService) {

		this.commentService = commentService;

		this.profileService = profileService;
	}

	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<Profile> get(@PathVariable long id, ServletRequest request) {

		return new ResponseEntity<Profile>(this.profileService.getProfile(id, request), HttpStatus.OK);

	}

	@PostMapping(value = "/like/{profile}")
	@ResponseBody
	public ResponseEntity<Profile> createLike(@PathVariable Long profile, ServletRequest request) {

		return new ResponseEntity<Profile>(this.profileService.toLike(request,profile), HttpStatus.CREATED);

	}

	@PostMapping(value = "/comment/{profile}")
	@ResponseBody
	public ResponseEntity<Comment> userComment(@PathVariable long profile, ServletRequest request,
			@RequestBody Comment comment) {

		return new ResponseEntity<Comment>(this.commentService.toComment(profile, request, comment), HttpStatus.OK);
	}

	@PostMapping(value = "/reply/comment/{idParent}")
	@ResponseBody
	public ResponseEntity<Comment> replyComment(@PathVariable long idParent, ServletRequest request,
			@RequestBody Comment comment) {

		return new ResponseEntity<Comment>(this.commentService.toReplyComment(idParent, request, comment),
				HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/comment/{idParent}")
	@ResponseBody
	public ResponseEntity<Comment> deleteComment(@PathVariable long idParent) {

		return new ResponseEntity<Comment>(this.commentService.toDeleteComment(idParent), HttpStatus.OK);
	}

	@GetMapping(value = "/ranking")
	@ResponseBody
	public ResponseEntity<RankingDTOList> ranking() {

		return new ResponseEntity<RankingDTOList>(this.profileService.rankingTop10(), HttpStatus.OK);
	}
}
