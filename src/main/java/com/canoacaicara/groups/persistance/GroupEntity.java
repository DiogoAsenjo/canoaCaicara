package com.canoacaicara.groups.persistance;

import java.util.HashSet;
import java.util.Set;

import com.canoacaicara.common.enums.ActivityType;
import com.canoacaicara.user.repository.persistance.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
    private ActivityType activity_type;
    
    @NotNull
    private String day_of_week;
    
    @NotNull
    private String time;

    @ManyToMany(mappedBy = "groups")
    private Set<UserEntity> users = new HashSet<>();

    public GroupEntity(){}

    public GroupEntity(ActivityType activityType, String dayOfWeek, String time){
        this.activity_type = activityType;
        this.day_of_week = dayOfWeek;
        this.time = time;
    }
}
