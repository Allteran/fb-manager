package io.allteran.fbmanager.repo;

import io.allteran.fbmanager.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findTeamById(Long id);
}
