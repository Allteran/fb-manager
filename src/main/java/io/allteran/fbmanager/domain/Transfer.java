package io.allteran.fbmanager.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Table(name = "transfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "team_from_id")
    private Team teamFrom;
    @OneToOne
    @JoinColumn(name = "team_to_id")
    private Team teamTo;

    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Min(value = 1, message = "Price cannot be empty")
    private double price;
    private double totalAmount;
}
