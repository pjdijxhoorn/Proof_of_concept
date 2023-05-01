package com.runninggames.dungeon_run.Repositories;

import com.runninggames.dungeon_run.Models.DungeonRun;
import com.runninggames.dungeon_run.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DungeonRunRepository extends JpaRepository<DungeonRun, Long> {
    List<DungeonRun> findByUser(User user);
}
