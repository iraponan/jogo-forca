package br.eti.inovareti.jogoforca.game;

import br.eti.inovareti.jogoforca.core.Word;

public class Game {
    public void start() {
        Word word = new Word("casa");
        word.hasChar('a');
        word.hasChar('c');
        word.hasChar('s');
        System.out.println(word.discovered());
        System.out.print(word);
    }
}
