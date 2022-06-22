package com.group8rhea.sessionlibrary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto implements Serializable {
    private static final long serialVersionUID = 2737714290822048885L;

    public PlayerDto(Integer ID){
        this.ID = ID;
    }

    @Getter
    @Setter
    private int ID;

    @Getter
    @Setter
    private int money;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private double x;
    @Getter
    @Setter
    private double y;
    @Getter
    @Setter
    private int onThisTileId;


}
