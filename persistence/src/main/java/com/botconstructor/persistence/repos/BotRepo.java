package com.botconstructor.persistence.repos;

import com.botconstructor.model.BotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BotRepo extends JpaRepository<BotModel, UUID> {
    @Query("SELECT bot FROM BotModel bot WHERE bot.owner.userName = :username")
    List<BotModel> findAllByOwner(String username);
}
