package com.botconstructor.persistence.repos;

import com.botconstructor.model.middleware.Middleware;
import com.botconstructor.model.processingblock.ProcessingBlock;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MiddlewareRepo extends JpaRepository<Middleware, Integer> {
    @NotNull
    @Query("SELECT block.middlewares FROM BotModel bot " +
           "JOIN bot.processingBlocks block " +
           "WHERE bot.id = :botId AND block.id = :blockId")
    List<Middleware> findAllInBotByBlockId(@Param("botId") UUID botId, @Param("blockId") int blockId);
}
