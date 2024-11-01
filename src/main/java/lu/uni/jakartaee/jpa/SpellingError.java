package lu.uni.jakartaee.jpa;

import jakarta.persistence.*;

@Entity
public class SpellingError {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String wrongWord;
    private String correctWord;

    
    public SpellingError(String wrongWord, String correctWord) {
        this.wrongWord = wrongWord;
        this.correctWord = correctWord;
    }


    public String getWrongWord() {
        return wrongWord;
    }


    public void setWrongWord(String wrongWord) {
        this.wrongWord = wrongWord;
    }


    public String getCorrectWord() {
        return correctWord;
    }


    public void setCorrectWord(String correctWord) {
        this.correctWord = correctWord;
    }

    
}
