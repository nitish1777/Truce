package truce.app.assign.truce.DataClass;

/**
 * Created by USER on 10-06-2016.
 */
public class VocabData {
    int id;
    double ratio;
    String word,meaning;

    public VocabData() {
    }

    public VocabData(int id, double ratio, String word, String meaning) {
        this.id = id;
        this.ratio = ratio;
        this.word = word;
        this.meaning = meaning;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public String toString() {
        return "VocabData{" +
                "id=" + id +
                ", ratio=" + ratio +
                ", word='" + word + '\'' +
                ", meaning='" + meaning + '\'' +
                '}';
    }
}
