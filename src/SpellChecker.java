public class SpellChecker {
    private String filename;
    private DictReader dicionario;
    private int numberOfWords;

    public SpellChecker(String filename) {
        this.filename = filename;
        dicionario = new DictReader(filename); // Inicialize no construtor
    }

    public static void main(String[] args) throws Exception {
        SpellChecker spellChecker = new SpellChecker("words.txt");
        spellChecker.getNumberOfWords();
    }

    public int getNumberOfWords() {
        System.out.println(dicionario.getDictionary());
        return numberOfWords;
    }
}
