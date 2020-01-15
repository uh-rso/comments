package com.uh.server.persistence.jpa;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<CommentEntity, Long> {

    Set<CommentEntity> findByMediaId(final String mediaId);

    Set<CommentEntity> findByAuthorId(final String ownerId);

}
