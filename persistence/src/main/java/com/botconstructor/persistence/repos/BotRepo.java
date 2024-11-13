package com.botconstructor.persistence.repos;

import com.botconstructor.model.BotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BotRepo extends JpaRepository<BotModel, UUID> {

}
