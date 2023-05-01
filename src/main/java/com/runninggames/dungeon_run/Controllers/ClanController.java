package com.runninggames.dungeon_run.Controllers;

import com.runninggames.dungeon_run.Dtos.ClanInputDto;
import com.runninggames.dungeon_run.Dtos.UserInputDto;
import com.runninggames.dungeon_run.Services.ClanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clan")
public class ClanController {
    private final ClanService clanService;

    public ClanController(ClanService clanService) {
        this.clanService = clanService;
    }


    @PostMapping(value = "")
    public ResponseEntity<UserInputDto> createUser(@RequestBody ClanInputDto clanInputDto) {;

        // logged in user becomes clanleader

        String clan_name = clanService.createClan(clanInputDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{clan_name}")
                .buildAndExpand(clan_name).toUri();

        return ResponseEntity.created(location).build();
    }


}
