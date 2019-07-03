package Application.controllers;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Application.model.Comment;
import Application.services.CommentService;

@RestController
@RequestMapping({ "/v1/comments" })
public class CommentController {

	private CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
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

	@DeleteMapping(value = "/delete/{id}")
	@ResponseBody
	public ResponseEntity<Comment> deleteComment(@PathVariable long id) {

		return new ResponseEntity<Comment>(this.commentService.toDeleteComment(id), HttpStatus.OK);
	}

}
