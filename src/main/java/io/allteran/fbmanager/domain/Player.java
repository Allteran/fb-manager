package io.allteran.fbmanager.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Table(name = "player")
public class Player {
    @Id
    private Long id;
    @NotBlank(message = "First name cannot be empty")
    private String firstName;
    @NotBlank(message = "Last name cannot be empty")
    private String lastName;
    @Min(value = 1, message = "Experience cannot be 0")
    private int experienceMonth;
    @Min(value = 18, message = "Player should be above 18 y.o.")
    private int ageYear;
    @NotBlank(message = "Player must have a position")
    private String position;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
