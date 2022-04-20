package com.group8rhea.monopolyserver.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "game_score", schema = "project_group8", catalog = "")
@IdClass(GameScoreEntityPK.class)
public class GameScoreEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "game_id")
    private long gameId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
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
        GameScoreEntity that = (GameScoreEntity) o;
        return gameId == that.gameId && userId == that.userId && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, userId, score);
    }
}
