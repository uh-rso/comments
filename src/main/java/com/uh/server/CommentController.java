package com.uh.server;

import java.util.List;

import com.uh.server.dto.CommentDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1")
public class CommentController {

    private final CommentService commentService;

    public CommentController(final CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(path = "/media/{mediaId}/comments")
    public CommentDto createComment(
            @PathVariable("mediaId") final String mediaId,
            @RequestBody final CommentDto comment) {
        final String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return commentService.createComment(mediaId, userId, comment);
    }

    @GetMapping(path = "/media/{mediaId}/comments")
    public List<CommentDto> getAll(@PathVariable("mediaId") final String mediaId) {
        final String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return commentService.getAll(mediaId, userId);
    }

}
