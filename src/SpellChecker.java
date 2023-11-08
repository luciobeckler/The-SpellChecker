import java.util.ArrayList;
import java.util.Arrays;

public class SpellChecker {
    private String filename;
    private DictReader dicionario;
    private int numberOfWords;
    private ArrayList<String> words;
    private String word;
    private String prefix;
    private String text;
    private String newWord;

    public SpellChecker(String filename) {
        this.filename = filename;
        this.word = "false";
        this.words = new ArrayList<>(Arrays.asList("true", "false"));
        this.prefix = "fal";
        this.text = "face";
        this.newWord = "lucio";
        dicionario = new DictReader(this.filename); // Inicialize no construtor

    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        SpellChecker spellChecker = new SpellChecker("./words.txt");
        System.out.println("O total de palavras é: " + spellChecker.getNumberOfWords() + " palavras"); // !Questão 1
        System.out.println("A palavra \"" + spellChecker.word + "\" está presente no dicionário? (true/false): "
                + spellChecker.isKnownWord(spellChecker.word) + "\n"); // ! Questão 1
        System.out.println("A palavra \"" + spellChecker.word + "\" está presente no dicionário? (true/false): "
                + spellChecker.allKnown(spellChecker.words) + "\n"); // ! Questão 1
        System.out
                .println("As palavras presentes no dicionário que possuem o prefixo \"" + spellChecker.prefix
                        + "\" são: ");
        spellChecker.wordsStartingWith(spellChecker.prefix).forEach(palavra -> System.out.print(palavra + ", ")); // !Questão
                                                                                                                  // 2

        System.err.println("\n\n");
        System.out
                .println("As palavras presentes no dicionário que contém o texto \"" + spellChecker.text + "\" são: ");
        spellChecker.wordsContaining(spellChecker.text).forEach(palavra -> System.out.print(palavra + ", ")); // !Questão
                                                                                                              // 2 // 2
        System.err.println("\n\n");

        spellChecker.insert(spellChecker.newWord); // ! Questão 3

    }

    /**
     * @return int
     */
    public int getNumberOfWords() {
        this.numberOfWords = dicionario.getDictionary().size();
        return numberOfWords;
    }

    /**
     * @param word
     * @return boolean
     */
    public boolean isKnownWord(String word) {
        if (!this.dicionario.getDictionary().contains(word))
            return false;
        else
            return true;

    }

    /**
     * @param words
     * @return boolean
     */
    public boolean allKnown(ArrayList<String> words) {
        for (String word : words) {
            if (!this.dicionario.getDictionary().contains(word))
                return false;
        }
        return true;
    }

    public ArrayList<String> wordsStartingWith(String prefix) {
        ArrayList<String> result = new ArrayList<>();

        dicionario.getDictionary().forEach(palavra -> {
            if (palavra.toLowerCase().startsWith(prefix.toLowerCase())) {
                result.add(palavra);
            }
        });
        return result;
    }

    public ArrayList<String> wordsContaining(String text) {
        ArrayList<String> result = new ArrayList<>();

        dicionario.getDictionary().forEach(palavra -> {
            if (palavra.toLowerCase().contains(text.toLowerCase())) {
                result.add(palavra);
            }
        });
        return result;
    }

    public void insert(String newWord) {
        ArrayList<String> retorno = dicionario.getDictionary();
        if (!dicionario.getDictionary().contains(newWord)) {
            retorno.add(newWord);
            retorno.sort(null);
            dicionario.save(retorno);
        } else {
            System.err.println("Palavra nova já está presente no dicionário!\n");
        }
        System.out.println(dicionario.getDictionary());
    }

    public boolean remove(String newWordToRemove){
            ArrayList<String> retorno = dicionario.getDictionary();

        if(dicionario.getDictionary().contains(newWordToRemove))
            dicionario.
    }

}
