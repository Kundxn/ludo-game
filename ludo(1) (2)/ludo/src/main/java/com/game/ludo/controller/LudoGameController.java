package com.game.ludo.controller;

import com.game.ludo.model.Game;
import com.game.ludo.service.LudoGameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ludo")
public class LudoGameController {

    private final LudoGameService ludoGameService;

    public LudoGameController(LudoGameService ludoGameService) {
        this.ludoGameService = ludoGameService;
    }

    @GetMapping("/")
    public String home() {
        return "index"; // Renders index.html
    }

    @PostMapping("/create-game")
    public String createGame(Model model) {
        Game game = ludoGameService.createGame();
        model.addAttribute("game", game);
        return "game"; // Renders game.html
    }

    @PostMapping("/join-game")
    public String joinGame(
            @RequestParam String gameId,
            @RequestParam String playerName,
            @RequestParam String color,
            Model model) {
        Game game = ludoGameService.joinGame(gameId, playerName, color);
        model.addAttribute("game", game);
        return "game"; // Renders game.html
    }

    @PostMapping("/roll-dice")
    public String rollDice(@RequestParam String gameId, Model model) {
        int diceRoll = ludoGameService.rollDice(gameId);
        Game game = ludoGameService.getGame(gameId); // Assuming a method to fetch game details
        model.addAttribute("diceRoll", diceRoll);
        model.addAttribute("game", game);
        return "game"; // Renders game.html
    }
}
