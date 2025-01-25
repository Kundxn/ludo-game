package com.game.ludo.service;

import com.game.ludo.model.*;
import com.game.ludo.repository.*;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class LudoGameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final Random random = new Random();

    public LudoGameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public Game createGame() {
        Game game = new Game();
        game.setId(UUID.randomUUID().toString());
        game.setStatus("WAITING");
        return gameRepository.save(game);
    }

    public Game joinGame(String gameId, String playerName, String color) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));
        if (game.getPlayers().size() >= 4) {
            throw new RuntimeException("Game is full");
        }

        Player player = new Player();
        player.setId(UUID.randomUUID().toString());
        player.setName(playerName);
        player.setColor(color);
        player.setGame(game);

        game.getPlayers().add(player);
        playerRepository.save(player);
        return gameRepository.save(game);
    }

    public int rollDice(String gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));
        int diceValue = random.nextInt(6) + 1;
        game.setDiceValue(diceValue);
        gameRepository.save(game);
        return diceValue;
    }

    public Game getGame(String gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));
    }
}
