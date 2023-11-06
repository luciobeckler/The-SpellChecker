import java.util.ArrayList;
import java.util.Arrays;

public class SpellChecker {
    private String filename;
    private DictReader dicionario;
    private int numberOfWords;
    private ArrayList<String> words;
    private String word;

    public SpellChecker(String filename) {
        this.filename = filename;
        this.word = "false";
        this.words = new ArrayList<>(Arrays.asList("true", "false"));
        dicionario = new DictReader(this.filename); // Inicialize no construtor

    }

    public static void main(String[] args) throws Exception {
        SpellChecker spellChecker = new SpellChecker("./words.txt");
        System.err.println("O total de palavras é: " + spellChecker.getNumberOfWords() + " palavras"); // !Questão 1
        System.err.println("A palavra \"" + spellChecker.word + "\" está presente no dicionário? (true/false): "
                + spellChecker.isKnownWord(spellChecker.word)); // ! Questão 2
        System.err.println("A palavra \"" + spellChecker.word + "\" está presente no dicionário? (true/false): "
                + spellChecker.allKnown(spellChecker.words)); // ! Questão 3

    }

    public int getNumberOfWords() {
        this.numberOfWords = dicionario.getDictionary().size();
        return numberOfWords;
    }

    public boolean isKnownWord(String word) {
        if (!this.dicionario.getDictionary().contains(word))
            return false;
        else
            return true;

    }

    public boolean allKnown(ArrayList<String> words) {
        for (String word : words) {
            if (!this.dicionario.getDictionary().contains(word))
                return false;
        }
        return true;
    }
}
