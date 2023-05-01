package com.runninggames.dungeon_run.Repositories;

import com.runninggames.dungeon_run.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
