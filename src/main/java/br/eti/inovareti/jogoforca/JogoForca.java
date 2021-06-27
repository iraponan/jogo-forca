package br.eti.inovareti.jogoforca;

import br.eti.inovareti.jogoforca.game.Game;

import java.util.Arrays;

public class JogoForca {
    public static void main(String[] args) {

        //System.out.println("===> " + Arrays.toString(args));

        Game game = new Game();
        game.start(args);
    }
}
