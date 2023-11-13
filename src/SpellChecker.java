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
    private String newWordToRemove;
    private String isWordPalindrome;
    private String isAnagram;

    public SpellChecker(String filename) {
        this.filename = filename;
        this.word = "false";
        this.words = new ArrayList<>(Arrays.asList("true", "false"));
        this.prefix = "false";
        this.text = "sleep";
        this.newWord = "Aluno";
        this.newWordToRemove = "book";
        this.isWordPalindrome = "dog";
        this.isAnagram = "listen";
    }

    public void setDicionario() throws Exception {
        dicionario = new DictReader(this.filename); // Inicialize no construtor

    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        SpellChecker spellChecker = new SpellChecker("./words.txt");
        spellChecker.setDicionario();
        System.out.println("O total de palavras é: " + spellChecker.getNumberOfWords() + " palavras"); // !Questão 1

        System.out.println("A palavra \"" + spellChecker.word + "\" está presente no dicionário? (true/false): "
                + spellChecker.isKnownWord(spellChecker.word) + "\n"); // ! Questão 1

        System.out.println("A palavra \"" + spellChecker.words + "\" está presente no dicionário? (true/false): "
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
                                                                                                              // 2

        System.err.println("\n\n");

        spellChecker.insert(spellChecker.newWord); // ! Questão 3

        spellChecker.remove(spellChecker.newWordToRemove); // ! Questão 3

        // ! Questão 4
        System.out.println("A palavra: " + spellChecker.isWordPalindrome + "é um palíndromo? "
                + spellChecker.isPalindrome(spellChecker.isWordPalindrome));
        // ! Questão 4
        System.out.println("A palavra: " + spellChecker.isAnagram + "é um anagrama de quais palavras? "
                + spellChecker.anagrams(spellChecker.isAnagram));

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
            if (palavra.toLowerCase().equals(text.toLowerCase())) {
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
    }

    public boolean remove(String newWordToRemove) {
        ArrayList<String> retorno = dicionario.getDictionary();

        for (int i = 0; i < retorno.size(); i++) {
            if (retorno.get(i).equals(newWordToRemove)) {
                retorno.remove(i);
                dicionario.save(retorno);
                System.out.println("A palavra: " + this.newWordToRemove + "foi removida com sucesso!");
                return true;
            }
        }
        System.out.println("A palavra não estava presente no dicionário");
        return false;
    }

    // !Questão 3
    public void save(ArrayList<String> newDicionario) {
        dicionario.save(newDicionario);
    }

    public boolean isPalindrome(String palindromeWord) {
        String reversed = new StringBuilder(palindromeWord).reverse().toString();

        if (!this.isKnownWord(palindromeWord)) {
            System.out.println("A palavra sequer existe no dicionário");
            return false;
        }
        if (dicionario.getDictionary().stream().anyMatch(word -> word.equalsIgnoreCase(reversed))) {
            return true;
        }

        return false;
    }

    public ArrayList<String> anagrams(String isAnagram) {
        char[] character = isAnagram.toCharArray();
        Arrays.sort(character);

        ArrayList<String> anagrams = new ArrayList<String>();

        dicionario.getDictionary().forEach(word -> {
            char[] wordSort = word.toCharArray();
            Arrays.sort(wordSort);
            if (Arrays.equals(wordSort, character)) {
                anagrams.add(word);
            }
        });

        return anagrams;
    }

}
