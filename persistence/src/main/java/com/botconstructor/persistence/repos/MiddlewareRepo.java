package com.botconstructor.persistence.repos;

import com.botconstructor.model.middleware.Middleware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MiddlewareRepo extends JpaRepository<Middleware, Integer> {
    @Query("SELECT block.middlewares FROM BotModel bot " +
           "JOIN bot.processingBlocks block " +
           "WHERE bot.id = :botId AND block.id = :blockId")
    List<Middleware> findAllInBotByBlockId(@Param("botId") UUID botId, @Param("blockId") int blockId);

    @Query("SELECT middleware FROM BotModel bot " +
           "JOIN bot.processingBlocks block " +
           "JOIN block.middlewares middleware " +
           "WHERE bot.id = :botId AND middleware.id = :id")
    Middleware findInBotById(
            @Param("id") int id,
            @Param("botId") UUID botId);

}
