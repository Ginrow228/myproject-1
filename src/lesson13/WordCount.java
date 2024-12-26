package lesson13;

public class WordCount {

    protected String word;
    protected int count;

    public WordCount(String word, int count) {
        this.word = word;
        this.count = count;
    }

    @Override
    public String toString() {
        return word + ": " + count;
    }
}
