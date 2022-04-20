package com.group8rhea.monopolyserver.model;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Table(name = "user_model_copy", schema = "project_group8", catalog = "")
public class UserModelEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "last_modified_at")
    @UpdateTimestamp
    private Date lastModifiedAt;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "resettoken")
    private Integer resettoken;


    public UserModelEntity(String username, String password,String email){
        this.username = username;
        this.password=password;
        this.email=email;
        this.resettoken = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Date lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getResettoken() {return resettoken;}

    public void setResettoken(Integer resettoken) {this.resettoken = resettoken;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModelEntity that = (UserModelEntity) o;
        return id == that.id && Objects.equals(createdAt, that.createdAt) && Objects.equals(email, that.email) && Objects.equals(lastModifiedAt, that.lastModifiedAt) && Objects.equals(password, that.password) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, email, lastModifiedAt, password, username);
    }
}
