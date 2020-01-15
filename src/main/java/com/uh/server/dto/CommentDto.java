package com.uh.server.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class CommentDto implements Serializable {

    @JsonCreator
    public CommentDto() {}

    public CommentDto(final String id) {
        this.id = id;
    }

    private String id;
    private String mediaId;
    private String authorId;
    private String comment;
    private LocalDateTime createDateTime;
}
