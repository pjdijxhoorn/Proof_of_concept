package com.runninggames.dungeon_run.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "clans")
public class Clan {
    @Id
    @Column(nullable = false, unique = true)
    private String clan_name;


    //relations.........................................
    @OneToOne
    private User clanleader;

    @OneToMany(mappedBy = "clan")
    // groups between 5 and 12 are best for workgroups Harris, T. E., & Sherblom, J. C. (2011). Small Group and Team Communication. Pearson Education.
    @Size(max = 12)
    private List<User> clan_members;

}
