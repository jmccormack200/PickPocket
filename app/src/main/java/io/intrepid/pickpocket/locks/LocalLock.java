package io.intrepid.pickpocket.locks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalLock implements LockInterface{

    private ArrayList<String> correctAnswer;

    public LocalLock(){
        correctAnswer = new ArrayList<>();
        correctAnswer.add("1");
        correctAnswer.add("2");
        correctAnswer.add("3");
        correctAnswer.add("4");
    }

    @Override
    public Map<String, Integer> checkAnswer(List<String> guessString) {
        int numberCorrect = 0;
        int numberClose = 0;

        ArrayList<String> remainingGuesses = new ArrayList<>();
        remainingGuesses.addAll(guessString);

        ArrayList<String> remainingSecretCombination = new ArrayList<>();
        remainingSecretCombination.addAll(correctAnswer);

        // First remove exact matches;
        for (int i = 0; i < remainingSecretCombination.size(); i++) {
            if (remainingGuesses.get(i).equals(remainingSecretCombination.get(i))) {
                numberCorrect++;
                remainingGuesses.set(i, "x");
                remainingSecretCombination.set(i, "x");
            }
        }

        if (numberCorrect != remainingSecretCombination.size()) {
            for (int i = 0; i < remainingGuesses.size(); i++) {
                if (!remainingGuesses.get(i).equals("x")) {
                    if (remainingSecretCombination.contains(remainingGuesses.get(i))) {
                        remainingSecretCombination.set(
                                remainingSecretCombination.indexOf(remainingGuesses.get(i)), "x");
                        numberClose++;
                    }
                }
            }
        }
        Map<String, Integer> answerMap = new HashMap<>();
        answerMap.put(CORRECT, numberCorrect);
        answerMap.put(CLOSE, numberClose);
        return answerMap;
    }
}
