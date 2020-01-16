package com.uh.server;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.uh.server.dto.CommentDto;
import com.uh.server.persistence.jpa.CommentEntity;
import com.uh.server.persistence.jpa.CommentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Component
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final RestTemplate restTemplate;
    private final String mediaApiUrl;

    public CommentService(
            final CommentRepository commentRepository,
            final RestTemplate restTemplate,
            @Value("${media-api-url:#{null}}") final String mediaApiUrl) {
        this.commentRepository = commentRepository;
        this.restTemplate = restTemplate;
        this.mediaApiUrl = mediaApiUrl;
    }

    public CommentDto createComment(final String mediaId, final String userId, final CommentDto comment) {
        // Check that media exists (returns 200)
        restTemplate.getForObject(mediaApiUrl + "/v1/media/" + mediaId, Object.class);

        CommentEntity entity = new CommentEntity();
        entity.setAuthorId(userId);
        entity.setMediaId(mediaId);
        entity.setComment(comment.getComment());
        entity.setCreateDateTime(LocalDateTime.now());
        CommentEntity saved = commentRepository.save(entity);

        return mapToDto(saved);
    }

    public List<CommentDto> getAll(final String mediaId, final String userId) {
        return commentRepository.findByMediaId(mediaId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private CommentDto mapToDto(CommentEntity entity) {
        CommentDto dto = new CommentDto();
        dto.setAuthorId(entity.getAuthorId());
        dto.setMediaId(entity.getMediaId());
        dto.setId(entity.getId().toString());
        dto.setComment(entity.getComment());
        dto.setCreateDateTime(entity.getCreateDateTime());
        return dto;
    }
}
