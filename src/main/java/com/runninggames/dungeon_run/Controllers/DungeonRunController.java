package com.runninggames.dungeon_run.Controllers;

import com.runninggames.dungeon_run.Dtos.DungeonRunInputDto;
import com.runninggames.dungeon_run.Services.DungeonRunService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.runninggames.dungeon_run.Utilities.Utilities.getErrorString;

@RestController
@RequestMapping("/dungeonrun")
public class DungeonRunController {

    private final DungeonRunService dungeonRunService;

    public DungeonRunController(DungeonRunService dungeonRunService) {
        this.dungeonRunService = dungeonRunService;
    }


    @PostMapping("/{id}")
    public ResponseEntity<String> CreateDungeonRun(@Valid @RequestBody DungeonRunInputDto dungeonRunInputDto, BindingResult br, @PathVariable String id){
        if (br.hasErrors()) {
            String errorString = getErrorString(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        }
        long createdId = dungeonRunService.createDungeonRun(dungeonRunInputDto, id);
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/dungeonrun/" + createdId)
                        .toUriString());
        return ResponseEntity.created(uri).body("DungeonRun Is now stored");

    }


}
