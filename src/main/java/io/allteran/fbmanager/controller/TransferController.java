package io.allteran.fbmanager.controller;

import io.allteran.fbmanager.domain.Player;
import io.allteran.fbmanager.domain.Team;
import io.allteran.fbmanager.domain.Transfer;
import io.allteran.fbmanager.exception.CustomException;
import io.allteran.fbmanager.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transfer")
public class TransferController {
    private final TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("new/from={from}&to={to}")
    public ResponseEntity<Transfer> transferPlayer(@PathVariable("from") Team from,
                                                   @PathVariable("to") Team to,
                                                   @RequestBody Player player) {
        try {
            Transfer t = transferService.transferPlayer(from, to, player);
            System.out.println("-----------Transfer player------------");
            System.out.println("Player name is " + player.getFirstName() + " " + player.getLastName());
            System.out.println("From team: " + from.getName());
            System.out.println("From to: " + to.getName());
            System.out.println("----------------DONE------------------");
            return new ResponseEntity<>(t, HttpStatus.OK);
        } catch (CustomException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/history/player={player}")
    public ResponseEntity<List<Transfer>> listPlayerHistory (@PathVariable("player") Player player) {
        List<Transfer> transferList = transferService.transferPlayerHistory(player);
        if(transferList != null && !transferList.isEmpty()) {
            return new ResponseEntity<>(transferList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/history/team={team}")
    public ResponseEntity<List<Transfer>> listTeamHistory(@PathVariable("team") Team team) {
        List<Transfer> transferList = transferService.transferTeamHistory(team);
        if(transferList != null && !transferList.isEmpty()) {
            return new ResponseEntity<>(transferList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
