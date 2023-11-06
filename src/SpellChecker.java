public class SpellChecker {
    private String filename;
    private DictReader dicionario;
    private int numberOfWords;

    public SpellChecker(String filename) {
        this.filename = filename;
        dicionario = new DictReader(this.filename); // Inicialize no construtor
    }

    public static void main(String[] args) throws Exception {
        SpellChecker spellChecker = new SpellChecker("./words.txt");
        System.err.println("O total de palavras é: " + spellChecker.getNumberOfWords() + " palavras");
    }

    public int getNumberOfWords() {
        this.numberOfWords = dicionario.getDictionary().size();
        return numberOfWords;
    }
}
