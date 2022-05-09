package io.allteran.fbmanager.controller;

import io.allteran.fbmanager.domain.Player;
import io.allteran.fbmanager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/player")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    @GetMapping("list")
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> list = playerService.findAll();
        if(list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable("id") Player player) {
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping("new")
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        playerService.createPlayer(player);
        if(player != null) {
            return new ResponseEntity<>(player, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("new/list")
    public ResponseEntity<List<Player>> createPlayerList(@RequestBody List<Player> players) {
        playerService.createPlayerList(players);
        if(players != null && !players.isEmpty()) {
            return new ResponseEntity<>(players, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable("id") Player playerFromDb,
                                               @RequestBody Player player) {
        playerService.updatePlayer(playerFromDb, player);
        return new ResponseEntity<>(playerFromDb, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deletePlayer (@PathVariable("id") Player player) {
        if(player != null) {
            playerService.deletePlayer(player);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
