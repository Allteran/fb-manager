package io.allteran.fbmanager;

import io.allteran.fbmanager.domain.Player;
import io.allteran.fbmanager.repo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepo;

    @Autowired
    public PlayerService(PlayerRepository playerRepo) {
        this.playerRepo = playerRepo;
    }

    public List<Player> findAllByTeam(Long teamId) {
        return playerRepo.findAllByTeamId(teamId);
    }

    public List<Player> findAllByTeam(String teamName) {
        return playerRepo.findAllByTeamName(teamName);
    }
}
