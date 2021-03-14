package com.stelmyit.skijumping.competition.model;

import com.stelmyit.skijumping.hill.model.Hill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "competition")
public class Competition {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Hill hill;

    private int baseGate;
}
