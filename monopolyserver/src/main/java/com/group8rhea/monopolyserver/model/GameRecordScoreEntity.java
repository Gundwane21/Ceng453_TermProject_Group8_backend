package com.group8rhea.monopolyserver.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "game_record_score", schema = "project_group8", catalog = "")
public class GameRecordScoreEntity {
    @Id
    @Column(name = "uuid")
    @Type(type = "uuid-char")
    private UUID uuid = UUID.randomUUID();

    @Basic
    @Column(name = "game_id")
    private long gameId;
    @Basic
    @Column(name = "created_at")
    private Date createdAt;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "score")
    private Long score;

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameRecordScoreEntity that = (GameRecordScoreEntity) o;
        return gameId == that.gameId && userId == that.userId && Objects.equals(createdAt, that.createdAt) && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, createdAt, userId, score);
    }
}
