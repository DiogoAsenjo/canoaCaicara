package com.canoacaicara.groups.infrastructure.persistance;

import com.canoacaicara.common.enums.PracticeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "groups")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PracticeType practice_type;
    @NotNull
    private String day_of_week;
    @NotNull
    private String time;

    public GroupEntity(){}

}

