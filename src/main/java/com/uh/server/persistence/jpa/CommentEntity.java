package com.uh.server.persistence.jpa;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
@Entity
public class CommentEntity {
    @Id
    @GeneratedValue
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    @Getter
    @Setter
    private String mediaId;

    @Getter
    @Setter
    private String authorId;

    @Getter
    @Setter
    private String comment;

    @CreationTimestamp
    @Getter
    @Setter
    private LocalDateTime createDateTime;

}
