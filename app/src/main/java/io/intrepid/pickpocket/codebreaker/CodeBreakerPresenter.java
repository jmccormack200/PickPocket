package io.intrepid.pickpocket.codebreaker;

import java.util.ArrayList;

public class CodeBreakerPresenter implements CodeBreakerContract.Presenter {

    private ArrayList<String> secretCombination;
    private ArrayList<String> guessCombination;
    private int position;
    private int numberCorrect;
    private int numberInAnswer;

    private CodeBreakerContract.View view;

    CodeBreakerPresenter() {
        secretCombination = new ArrayList<>();
        guessCombination = new ArrayList<>();
        secretCombination.add("1");
        secretCombination.add("2");
        secretCombination.add("3");
        secretCombination.add("4");
        guessCombination.add("");
        guessCombination.add("");
        guessCombination.add("");
        guessCombination.add("");
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
}
