package br.eti.inovareti.jogoforca.game;

import br.eti.inovareti.jogoforca.core.Dictionary;
import br.eti.inovareti.jogoforca.core.Word;
import br.eti.inovareti.jogoforca.exceptions.InvalidCharacterException;
import br.eti.inovareti.jogoforca.ui.UI;

import java.util.HashSet;
import java.util.Set;

public class Game {

    private static final int MAX_ERROS = 5;

    public void start() {
        UI.print("Bem vindo ao Jogo da Forca!");

        Dictionary dictionary = Dictionary.getInstance();
        Word word = dictionary.nextWord();

        UI.print("A palavra tem " + word.size() + " letras.");

        Set<Character> usedChars = new HashSet<>();
        int erroCount = 0;

        while (true) {
            UI.print(word);
            UI.printNewLine();

            char c;

            try {
                c = UI.readChar("Digite uma letra: ");

                if (usedChars.contains(c)) {
                    throw new InvalidCharacterException("A letra '" + c + "' já foi utilizada!");
                }

                usedChars.add(c);

                if (word.hasChar(c)) {
                    UI.print("Você acertou uma letra!");
                }
                else {
                    erroCount++;
                    if (erroCount < MAX_ERROS) {
                        UI.print("Você erro!\nVocê ainda por errar " + (MAX_ERROS - erroCount) + " vez(es).");
                    }
                }

                UI.printNewLine();

                if (word.discovered()) {
                    UI.print("PARABÊNS!!!\nVocê acertou a palavra correta: " + word.getOriginalWord());
                    UI.print("Fim de Jogo!");
                    break;
                }

                if (erroCount == MAX_ERROS) {
                    UI.print("VOCÊ PERDEU O JOGO!!!\nA palavra correta era: " + word.getOriginalWord());
                    UI.print("Fim de Jogo!");
                    break;
                }
            } catch (InvalidCharacterException e) {
                UI.print("Erro: " + e.getMessage());
                UI.printNewLine();
            }
        }
    }
}
