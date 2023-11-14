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
    private String word1;
    private String word2;
    private ArrayList<String> dictionaryToCompare;

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
        this.word1 = "teste";
        this.word2 = "false";
        this.dictionaryToCompare = new ArrayList<>(Arrays.asList("Teste", "Bool", "false", "palavraQueNaoExiste"));
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
        // ! Questão 4
        System.out.println("Entre as palavras a seguir \"" + spellChecker.dictionaryToCompare
                + "\" , as seguintes palavras não estão presentes no dicionário:"
                + spellChecker.difference(spellChecker.dictionaryToCompare));

        // ! Questão 5
        System.out
                .println("O custo entre as palavras \"" + spellChecker.word1 + "\" e \"" + spellChecker.word2 + "\" é: "
                        + spellChecker.distance(spellChecker.word1, spellChecker.word2) + ".");
    }

    public int getNumberOfWords() {
        this.numberOfWords = dicionario.getDictionary().size();
        return numberOfWords;
    }

    public boolean isKnownWord(String word) {
        for (int i = 0; i < dicionario.getDictionary().size(); i++) {
            if (dicionario.getDictionary().get(i).equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
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

    // !Questão 4: Rodar um for nos dois dicionários com o mesmo index e levando em
    // consideração que os dois estão
    // ! em ordem alfabética as palavras repetidas devem bater, caso não bater
    // adicione a plavra de um no outro

    public ArrayList<String> difference(ArrayList<String> dictionaryToCompare) {
        ArrayList<String> listToCompare = new ArrayList<>();

        for (int i = 0; i < dictionaryToCompare.size(); i++) {

            if (this.isKnownWord(dictionaryToCompare.get(i))) {
                listToCompare.add(dictionaryToCompare.get(i));
            }
        }
        return listToCompare;
    }

    public int distance(String word1, String word2) {
        int distance = 0;

        if (word1.length() != word2.length()) {
            System.out.println("Tamanho das palavras é diferente");
            return -1;
        } else {
            for (int i = 0; i < word1.length(); i++) {
                if (word1.charAt(i) != word2.charAt(i)) {
                    distance++;
                }
            }
        }
        return distance;
    }
}
