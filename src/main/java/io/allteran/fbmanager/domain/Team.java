package io.allteran.fbmanager.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Table(name = "team")
public class Team {
    @Id
    private Long id;
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "Country cannot be empty")
    private String country;
    @Max(value = 99, message = "Maximum value is 99")
    @Min(value = 1, message = "Minimum value is 1")
    private double transferFeeRate;
    private double account;

}
