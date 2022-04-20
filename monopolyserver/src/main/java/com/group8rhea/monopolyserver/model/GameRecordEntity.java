package com.group8rhea.monopolyserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.nio.ByteBuffer;
import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "game_record", schema = "project_group8", catalog = "")
public class GameRecordEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "game_id")
    @Getter
    @Setter
    private long gameId;
    @Basic
    @Column(name = "created_at")
    @Getter
    @Setter
    private Date createdAt;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameRecordEntity that = (GameRecordEntity) o;
        return gameId == that.gameId && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, createdAt);
    }
}
