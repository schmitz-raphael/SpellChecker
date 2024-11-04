package lu.uni.jakartaee;


import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lu.uni.jakartaee.jpa.SpellCheckService;
import lu.uni.jakartaee.jpa.SpellingError;
import lu.uni.jakartaee.jpa.SpellingErrorManager;


@Named("inputBean")  // This makes it accessible in JSF as #{inputBean}
@SessionScoped       // Maintains the bean state across multiple requests in the session
public class InputBean implements Serializable {

    private String userInput;
    private List<SpellingError> errors;

    @EJB
    private SpellCheckService spellCheckService;

    @EJB
    private SpellingErrorManager spellingErrorManager;

    //function to process the text input from the user by pressing the button
    public String checkText() {
        try {
            //retrieve the errors and add them to the db
            errors = spellCheckService.checkSpelling(userInput);
            for (SpellingError error: errors){
                spellingErrorManager.save(error);
            }
            //set the userInput to an empty string, so that when the spell checker is loaded the next time, the input field is empty
            userInput = "";
            //return success to initiate the navigation rule
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return "failure";
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
