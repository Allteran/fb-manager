package io.allteran.fbmanager.service;

import io.allteran.fbmanager.domain.Player;
import io.allteran.fbmanager.repo.PlayerRepository;
import org.springframework.beans.BeanUtils;
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

    public List<Player> findAll() {
        return playerRepo.findAll();
    }

    public List<Player> findAllByTeam(Long teamId) {
        return playerRepo.findAllByTeamId(teamId);
    }

    public List<Player> findAllByTeam(String teamName) {
        return playerRepo.findAllByTeamName(teamName);
    }

    public Player createPlayer(Player player) {
        return playerRepo.save(player);
    }

    public Player updatePlayer(Player playerFromDb, Player player) {
        BeanUtils.copyProperties(player, playerFromDb, "id", "team");
        return playerRepo.save(playerFromDb);
    }

    public void deletePlayer(Player player) {
        playerRepo.delete(player);
    }

    public List<Player> createPlayerList(List<Player> players) {
        return playerRepo.saveAll(players);
    }
}
