package io.intrepid.pickpocket.codebreaker;

import java.util.ArrayList;
import java.util.List;

public class CodeBreakerPresenter implements CodeBreakerContract.Presenter {

    private List<String> secretCombination;
    private List<String> guessCombination;
    private int position;
    private int numberCorrect;
    private int numberInAnswer;
    private List<CodeBreakerGuess> codeBreakerGuessList;

    private CodeBreakerContract.View view;

    CodeBreakerPresenter() {
        secretCombination = new ArrayList<>();
        guessCombination = new ArrayList<>();
        codeBreakerGuessList = new ArrayList<>();
        secretCombination.add("1");
        secretCombination.add("2");
        secretCombination.add("3");
        secretCombination.add("4");
        guessCombination.add("");
        guessCombination.add("");
        guessCombination.add("");
        guessCombination.add("");
    }

    public List<CodeBreakerGuess> getCodeBreakerGuessList() {
        return codeBreakerGuessList;
    }

    @Override
    public void setView(CodeBreakerContract.View view) {
        this.view = view;
    }

    @Override
    public void buttonClicked(String s) {
        switch (position) {
            case 0:
                view.showGuessInPositionOne(s);
                break;
            case 1:
                view.showGuessInPositionTwo(s);
                break;
            case 2:
                view.showGuessInPositionThree(s);
                break;
            case 3:
                view.showGuessInPositionFour(s);
                break;
        }
        guessCombination.set(position, s);
        if (position == 3) {
            position = 0;
        } else {
            position++;
        }
    }

    @Override
    public void onCheckAnswerClicked() {
        numberCorrect = 0;
        numberInAnswer = 0;
        view.lock();
        countNearMatches();
    }

    private void showUnlock() {
        view.unlock();
    }

    // guess: "2 2 1 1"
    // actual: "1 1 1 2"
    // result: G N N X
    private void countNearMatches() {
        ArrayList<String> remainingGuesses = new ArrayList<>();
        remainingGuesses.addAll(guessCombination);

        ArrayList<String> remainingSecretCombination = new ArrayList<>();
        remainingSecretCombination.addAll(secretCombination);

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
                if (remainingGuesses.get(i).equals("x")) {
                    break;
                } else {
                    if (remainingSecretCombination.contains(remainingGuesses.get(i))) {
                        remainingSecretCombination.set(
                                remainingSecretCombination.indexOf(remainingGuesses.get(i)), "x");
                        numberInAnswer++;
                    }
                }
            }
        } else {
            showAllCorrect();
        }
        storeGuess(guessCombination, numberCorrect, numberInAnswer);
        clearGuessesIfCorrect(numberCorrect);
        setAnswerHints();
    }

    private void showAllCorrect() {
        view.showAllCorrect();
    }

    private void setAnswerHints() {
        view.setNumberCorrect(numberCorrect, numberInAnswer);
        if (numberCorrect == 4){
            showUnlock();
        }
    }

    private void clearGuessesIfCorrect(int numCorrect) {
        if (numCorrect == 4) {
            codeBreakerGuessList.clear();
        }
    }

    private void storeGuess(List<String> guess, int numberCorrect, int numberInAnswer) {
        List guessCopy = (List) ((ArrayList<String>) guess).clone();
        codeBreakerGuessList.add(new CodeBreakerGuess(guessCopy, numberCorrect, numberInAnswer));
    }
}
