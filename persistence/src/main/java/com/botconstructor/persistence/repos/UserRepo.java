package com.botconstructor.persistence.repos;

import com.botconstructor.model.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<UserModel, UUID> {
    @Query("SELECT user FROM UserModel user JOIN user.bots bot WHERE bot.id = :botId")
    Optional<UserModel> findByBotId (@Param("botId") UUID botId);

    Optional<UserModel> findByUserName(String userName);
}
