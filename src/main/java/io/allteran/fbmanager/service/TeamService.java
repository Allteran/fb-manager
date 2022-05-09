package io.allteran.fbmanager.service;

import io.allteran.fbmanager.domain.Team;
import io.allteran.fbmanager.repo.TeamRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepo;
    private final PlayerService playerService;

    @Autowired
    public TeamService(TeamRepository teamRepo, PlayerService playerService) {
        this.teamRepo = teamRepo;
        this.playerService = playerService;
    }

    public List<Team> findAll() {
        return teamRepo.findAll();
    }

    public Team createTeam(Team team) {
        return teamRepo.save(team);
    }

    public Team findById(Long id) {
        return teamRepo.findTeamById(id);
    }

    public Team updateTeam(Team teamFromDb, Team team) {
        BeanUtils.copyProperties(team, teamFromDb, "id");
        return teamRepo.save(teamFromDb);
    }

    public void delete(Team team) {
        teamRepo.delete(team);
    }

    public List<Team> createTeamList(List<Team> teams) {
        return teamRepo.saveAll(teams);
    }
}
