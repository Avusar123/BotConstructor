package com.botconstructor.persistence.repos;

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
public interface ProcessingBlockRepo extends JpaRepository<ProcessingBlock, Integer> {
    @Query("SELECT b.processingBlocks FROM BotModel b WHERE b.Id = :botId")
    List<ProcessingBlock> findBlocks(@Param("botId") UUID botId);

    @NotNull
    @Query("SELECT block FROM BotModel bot " +
           "JOIN bot.processingBlocks block " +
           "WHERE bot.id = :botId AND block.id = :blockId")
    Optional<ProcessingBlock> findByIdInBot(@Param("botId") UUID botId, @Param("blockId") UUID blockId);
}
