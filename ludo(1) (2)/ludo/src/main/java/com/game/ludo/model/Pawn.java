package com.game.ludo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pawn {
    @Id
    private String id;
    private int position;
    private boolean isHome;
    private boolean isFinished;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
}
