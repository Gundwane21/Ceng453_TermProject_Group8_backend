package com.group8rhea.monopolyserver.model;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UserModel {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @CreatedDate
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date lastModifiedAt;

    public UserModel(){}
    public UserModel(Integer id, String username, String password, Date createdAt, Date lastModifiedAt){
        this.id=id;
        this.username = username;
        this.createdAt=createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.password=password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword(){return password;}

    public void setUsername(String username) {
        this.username = username;
    }
}
