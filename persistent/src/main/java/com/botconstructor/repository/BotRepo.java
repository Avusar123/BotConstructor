package com.botconstructor.repository;

import com.botconstructor.model.BotModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BotRepo extends JpaRepository<BotModel, UUID> {
}
