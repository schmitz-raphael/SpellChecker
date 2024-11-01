package lu.uni.jakartaee.jpa;

import jakarta.ejb.Stateless;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.languagetool.JLanguageTool;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.RuleMatch;

@Stateless
public class SpellCheckService {
    private JLanguageTool langTool = new JLanguageTool(new BritishEnglish());

    public List<SpellingError> checkSpelling(String text) throws IOException {
        List<SpellingError> errors = new ArrayList<>();
        for (RuleMatch match : langTool.check(text)) {
            String wrongWord = text.substring(match.getFromPos(), match.getToPos());
            String correctWord = match.getSuggestedReplacements().isEmpty() ? null : match.getSuggestedReplacements().get(0);
            errors.add(new SpellingError(wrongWord, correctWord));
        }
        return errors;
    }
}
