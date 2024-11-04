package lu.uni.jakartaee;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lu.uni.jakartaee.jpa.SpellingError;
import lu.uni.jakartaee.jpa.SpellingErrorManager;

@Named("statisticsBean")
@RequestScoped
public class StatisticsBean implements Serializable {
    
    private int topX;
    private List<SpellingError> topErrors;

    @EJB
    private SpellingErrorManager spellingErrorManager;

    public void loadStatistics() {
        topErrors = spellingErrorManager.findTopErrors(topX);
    }

    // Getters and setters
    public int getTopX() {
        return topX;
    }

    public void setTopX(int topX) {
        this.topX = topX;
    }

    public List<SpellingError> getTopErrors() {
        return topErrors;
    }

    public void setTopErrors(List<SpellingError> topErrors) {
        this.topErrors = topErrors;
    }
}
