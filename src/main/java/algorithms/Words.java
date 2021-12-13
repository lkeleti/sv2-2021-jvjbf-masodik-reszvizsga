package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Words {
    private List<String> wordsList = new ArrayList<>();

    public List<String> getWords() {
        return wordsList;
    }

    public void addWord(String word) {
        if (isAllLowerCaseChar(word)) {
            if (isContainSpace(word)) {
                wordsList.add(word);
            } else {
                throw new IllegalArgumentException("It should be one word!");
            }
        } else {
            throw new IllegalArgumentException("Word should be lower case!");
        }
    }

    private boolean isContainSpace(String word) {
        return word.split(" ").length == 1;
    }

    private boolean isAllLowerCaseChar(String word) {
        return word.equals(word.toLowerCase(Locale.ROOT));
    }

    public boolean isThereAWordTwice() {
        for (int i = 0; i < wordsList.size(); i++) {
            for (int j = i + 1; j < wordsList.size() - 1; j++) {
                if (wordsList.get(i).equals(wordsList.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }
}
