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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Controlador de comentários")
@RestController
@RequestMapping({"/v1/comments"})
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "Cadastra um novo comentário")
    @PostMapping(value = "/{profile}")
    @ResponseBody
    public ResponseEntity<Comment> userComment(@PathVariable long profile, ServletRequest request, @RequestBody Comment comment) {
        return new ResponseEntity<>(this.commentService.toComment(profile, request, comment), HttpStatus.OK);
    }

    @ApiOperation(value = "Cadastra uma nova resposta a um comentário")
    @PostMapping(value = "/reply/{idParent}")
    @ResponseBody
    public ResponseEntity<Comment> replyComment(@PathVariable long idParent, ServletRequest request,
                                                @RequestBody Comment comment) {

        return new ResponseEntity<>(this.commentService.toReplyComment(idParent, request, comment), HttpStatus.OK);
    }

    @ApiOperation(value = "Deleta um comentário")
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Comment> deleteComment(@PathVariable long id) {
        return new ResponseEntity<>(this.commentService.toDeleteComment(id), HttpStatus.OK);
    }

}
