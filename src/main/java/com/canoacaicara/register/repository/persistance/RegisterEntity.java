package com.canoacaicara.register.repository.persistance;

import com.canoacaicara.common.enums.ActivityType;
import com.canoacaicara.user.repository.persistance.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "registers")
public class RegisterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @NotNull
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    private int quantity;

    @NotNull
    @Enumerated(EnumType.STRING)
    ActivityType activityType;

    //Constructors
    public RegisterEntity() {}

    public RegisterEntity(UserEntity user, LocalDate date, int quantity, ActivityType activityType) {
        this.user = user;
        this.date = date;
        this.quantity = quantity;
        this.activityType = activityType;
    }

    public RegisterEntity(int id, UserEntity user, LocalDate date, int quantity, ActivityType activityType) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.quantity = quantity;
        this.activityType = activityType;
    }

    //Getter and setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public @NotNull LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull LocalDate date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public @NotNull ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(@NotNull ActivityType activityType) {
        this.activityType = activityType;
    }
}

