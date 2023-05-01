package com.runninggames.dungeon_run.Repositories;

import com.runninggames.dungeon_run.Models.Clan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClanRepository extends JpaRepository<Clan, String> {
}
