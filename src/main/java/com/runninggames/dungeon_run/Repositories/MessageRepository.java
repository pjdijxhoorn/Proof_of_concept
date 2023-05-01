package com.runninggames.dungeon_run.Repositories;

import com.runninggames.dungeon_run.Models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, String> {
}
