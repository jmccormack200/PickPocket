package io.intrepid.pickpocket.codebreaker;

import java.util.ArrayList;

public class CodeBreakerPresenter implements CodeBreakerContract.Presenter {

    private ArrayList<String> secretCombination;
    private ArrayList<String> guessCombination;
    private int position;
    private boolean locked;
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
        showAllIncorrect();
        countNearMatches();
    }

    private void showAllIncorrect() {
        view.showAllIncorrect();
    }

    private void showAllCorrect() {
        view.showCorrectAtPositionOne();
        view.showCorrectAtPositionTwo();
        view.showCorrectAtPositionThree();
        view.showCorrectAsPositionFour();
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

    private void setAnswerHints() {
        // We combine together both sets of matches and set all to the 'nearly correct' state
        // we then switch the correct answers after. This is a hack to make the logic nicer.
        int totalHintsToShow = numberCorrect + numberInAnswer;
        if (totalHintsToShow == 4){
            view.showRightNumberWrongPositionFour();
        }
        if (totalHintsToShow >= 3){
            view.showRightNumberWrongPositionThree();
        }
        if (totalHintsToShow >= 2){
            view.showRightNumberWrongPositionTwo();
        }
        if (totalHintsToShow >= 1){
            view.showRightNumberWrongPositionOne();
        }

        // switch any 'half-right' answers to full answers if needed.
        if (numberCorrect == 4){
            view.showCorrectAsPositionFour();
            view.unlock();
        }
        if (numberCorrect >= 3){
            view.showCorrectAtPositionThree();
        }
        if (numberCorrect >= 2){
            view.showCorrectAtPositionTwo();
        }
        if (numberCorrect >= 1){
            view.showCorrectAtPositionOne();
        }
    }
}
