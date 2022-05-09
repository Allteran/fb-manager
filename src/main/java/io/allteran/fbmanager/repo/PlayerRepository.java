package io.allteran.fbmanager.repo;

import io.allteran.fbmanager.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("select p from Player p where p.team.id = :teamId")
    List<Player> findAllByTeamId(Long teamId);

    @Query("select p from Player p where p.team.name = :teamName")
    List<Player> findAllByTeamName(String teamName);
}
