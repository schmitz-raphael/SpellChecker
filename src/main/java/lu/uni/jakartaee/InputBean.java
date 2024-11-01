package lu.uni.jakartaee;


import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lu.uni.jakartaee.jpa.SpellCheckService;
import lu.uni.jakartaee.jpa.SpellingError;
import lu.uni.jakartaee.jpa.SpellingErrorRepository;


@Named("inputBean")  // This makes it accessible in JSF as #{inputBean}
@SessionScoped       // Maintains the bean state across multiple requests in the session
public class InputBean implements Serializable {

    private String userInput;
    private List<SpellingError> errors;

    @EJB
    private SpellCheckService spellCheckService;

    @EJB
    private SpellingErrorRepository spellingErrorRepository;

    public void checkText() {
        try {
            errors = spellCheckService.checkSpelling(userInput);
            for (SpellingError error: errors){
                spellingErrorRepository.save(error);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters and setters for userInput and errors
    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public List<SpellingError> getErrors() {
        return errors;
    }

    public void setErrors(List<SpellingError> errors) {
        this.errors = errors;
    }
}
