package com.runninggames.dungeon_run.Services;

import com.runninggames.dungeon_run.Dtos.ClanInputDto;
import com.runninggames.dungeon_run.Models.Clan;
import com.runninggames.dungeon_run.Repositories.ClanRepository;
import org.springframework.stereotype.Service;

@Service
public class ClanService {
    private final ClanRepository clanRepository;

    public ClanService(ClanRepository clanRepository) {
        this.clanRepository = clanRepository;
    }


    public  String createClan(ClanInputDto clanInputDto) {

        // check if clan name is taken change clanleader to logged in user

        Clan newclan = new Clan();

        newclan.setClan_name(clanInputDto.getClan_name());
        Clan clan = clanRepository.save(newclan);

        return clan.getClan_name();
    }
}
