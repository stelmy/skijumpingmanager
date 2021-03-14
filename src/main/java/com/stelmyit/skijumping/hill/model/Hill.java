package com.stelmyit.skijumping.hill.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hill")
public class Hill {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String city;
    private int kpoint;
    private int hillSize;
}
