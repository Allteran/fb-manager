package io.allteran.fbmanager.controller;

import io.allteran.fbmanager.domain.Team;
import io.allteran.fbmanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/team")
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("list")
    public ResponseEntity<List<Team>> listAllTeams() {
        List<Team> teams = teamService.findAll();
        if(teams != null) {
            return new ResponseEntity<>(teams, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Team> findOne(@PathVariable("id") Team team) {
        if(team != null) {
            return new ResponseEntity<>(team, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("new")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        if(team != null) {
            return new ResponseEntity<>(teamService.createTeam(team), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("new/list")
    public ResponseEntity<List<Team>> createTeamList(@RequestBody List<Team> teams) {
        if(teams != null && !teams.isEmpty()) {
            teamService.createTeamList(teams);
            return new ResponseEntity<>(teams, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable("id") Team teamFromDb,
                                           @RequestBody Team team) {
        if(team != null || teamFromDb != null) {
            return new ResponseEntity<>(teamService.updateTeam(teamFromDb, team), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteTeam(@PathVariable("id") Team team) {
        if(team != null) {
            teamService.delete(team);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
