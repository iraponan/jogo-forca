package br.eti.inovareti.jogoforca.game;

import br.eti.inovareti.jogoforca.core.Config;
import br.eti.inovareti.jogoforca.core.Dictionary;
import br.eti.inovareti.jogoforca.core.Word;
import br.eti.inovareti.jogoforca.exceptions.InvalidCharacterException;
import br.eti.inovareti.jogoforca.ui.UI;

import java.util.HashSet;
import java.util.Set;

public class Game {

    //private static final int MAX_ERROS = 5;

    public void start(String[] args) {
        UI.print("Bem vindo ao Jogo da Forca!");

        Dictionary dictionary = Dictionary.getInstance();
        UI.print("Dicionário usado: " + dictionary.getName());

        Word word = dictionary.nextWord();

        UI.print("A palavra tem " + word.size() + " letras.");

        Set<Character> usedChars = new HashSet<>();
        int erroCount = 0;

        if (args.length > 0) {
            Config.setMaxErros(args[0]);
        }

        int maxErros = Integer.parseInt(Config.get("maxErros"));
        UI.print("Você pode errar no máximo '" + maxErros + "' vez(es).");

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
                    if (erroCount < maxErros) {
                        UI.print("Você errou!\nVocê ainda por errar " + (maxErros - erroCount) + " vez(es).");
                    }
                }

                UI.printNewLine();

                if (word.discovered()) {
                    UI.print("PARABÊNS!!!\nVocê acertou a palavra correta: " + word.getOriginalWord());
                    UI.print("Fim de Jogo!");
                    break;
                }

                if (erroCount == maxErros) {
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
