package com.group8rhea.monopolyserver.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "game_record_score", schema = "project_group8", catalog = "")
@IdClass(GameScoreEntityPK.class)
public class GameRecordScoreEntity {
    @Basic
    @Column(name = "game_id")
    @Id
    private long gameId;
    @Basic
    @Column(name = "created_at")
    private Date createdAt;
    @Basic
    @Column(name = "user_id")
    @Id
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
        System.out.println('test-pr')
        return userId;
    }

    public void setUserId(int userId) {
        System.out.println('test-pr')

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
