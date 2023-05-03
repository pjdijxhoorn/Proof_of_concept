package com.runninggames.dungeon_run.Controllers;

import com.runninggames.dungeon_run.Dtos.ClanInputDto;
import com.runninggames.dungeon_run.Dtos.MessageInputDto;
import com.runninggames.dungeon_run.Models.Clan;
import com.runninggames.dungeon_run.Models.Message;
import com.runninggames.dungeon_run.Models.User;
import com.runninggames.dungeon_run.Repositories.UserRepository;
import com.runninggames.dungeon_run.Services.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.runninggames.dungeon_run.Utilities.Utilities.getErrorString;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/user")
    public  ResponseEntity<String> createMessageUser(@Valid @RequestBody MessageInputDto messageInputDto, BindingResult br) {

        // check sender is logged in user

        if (br.hasErrors()) {
            String errorString = getErrorString(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        }
        long createdId = messageService.createMessage(messageInputDto);
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/messages/" + createdId)
                        .toUriString());
        return ResponseEntity.created(uri).body("Messages is now send.");

    }

    @PostMapping("/clan")
    public  ResponseEntity<String> createMessageClan(@Valid @RequestBody MessageInputDto messageInputDto, BindingResult br) {

        // check sender is logged in user

        if (br.hasErrors()) {
            String errorString = getErrorString(br);
            return new ResponseEntity<>(errorString, HttpStatus.BAD_REQUEST);
        }
        long createdId = messageService.createMessageClan(messageInputDto);
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/messages/" + createdId)
                        .toUriString());
        return ResponseEntity.created(uri).body("Messages is now send.");

    }


}
