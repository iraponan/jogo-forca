package br.eti.inovareti.jogoforca.game;

import br.eti.inovareti.jogoforca.core.Dictionary;
import br.eti.inovareti.jogoforca.core.Word;

public class Game {
    public void start() {
        Dictionary d = Dictionary.getInstance();
        Word w1 = d.nextWord();
        System.out.println(w1.getOriginalWord());
        System.out.println(w1);
    }
}
