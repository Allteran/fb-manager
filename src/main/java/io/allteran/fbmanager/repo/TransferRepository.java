package io.allteran.fbmanager.repo;

import io.allteran.fbmanager.domain.Player;
import io.allteran.fbmanager.domain.Team;
import io.allteran.fbmanager.domain.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
    @Query("select t from Transfer t where t.player = :player")
    List<Transfer> findAllByPlayer(Player player);

    @Query(value = "select t from Transfer t where t.teamFrom = :team or t.teamTo = :team")
    List<Transfer> findAllByTeam(Team team);
}
