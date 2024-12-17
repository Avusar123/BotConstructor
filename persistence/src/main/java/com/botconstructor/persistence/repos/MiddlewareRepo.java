package com.botconstructor.persistence.repos;

import com.botconstructor.model.middleware.Middleware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MiddlewareRepo extends JpaRepository<Middleware, UUID> {
    @Query("SELECT block.middlewares FROM ProcessingBlock block " +
           "WHERE block.id = :blockId")
    List<Middleware> findAllInBlock(@Param("blockId") UUID blockId);
}
