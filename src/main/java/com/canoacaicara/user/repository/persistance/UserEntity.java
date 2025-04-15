package com.canoacaicara.user.repository.persistance;

import java.util.HashSet;
import java.util.Set;

import com.canoacaicara.common.enums.Roles;
import com.canoacaicara.groups.persistance.GroupEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotNull
    private String name;
    
    @NotNull
    @Column(unique = true)
    private String email;
    
    @NotNull
    private String password;
    
    @NotNull
    private String whatsapp;
    
    @NotNull
    private String pix;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private Roles role;

    @ManyToMany
    @JoinTable(
        name= "user-group",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<GroupEntity> groups = new HashSet<>();

    //Constructors
    public UserEntity() {}

    public UserEntity(String name, String email, String password, String whatsapp, String pix, Roles role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.whatsapp = whatsapp;
        this.pix = pix;
        this.role = role;
    }

    //Getter and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getPix() {
        return pix;
    }

    public void setPix(String pix) {
        this.pix = pix;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Set<GroupEntity> getGroups(){
        return groups;
    }

    public void setGroups(Set<GroupEntity> groups){
        this.groups = groups;
    }
}
