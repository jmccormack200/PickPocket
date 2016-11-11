package io.intrepid.pickpocket.widget.answerboxes;

public class AnswerBoxPresenter implements AnswerBoxContract.Presenter {

    AnswerBoxContract.View view;

    @Override
    public void setView(AnswerBoxContract.View view) {
        this.view = view;
    }

    @Override
    public void setAllCorrect() {
        view.showCorrectAtPositionOne();
        view.showCorrectAtPositionTwo();
        view.showCorrectAtPositionThree();
        view.showCorrectAsPositionFour();
    }

    @Override
    public void showAllIncorrect() {
        view.showIncorrectAtPositionOne();
        view.showIncorrectAtPositionTwo();
        view.showIncorrectAtPositionThree();
        view.showIncorrectAtPositionFour();
    }

    @Override
    public void setNumberCorrect(int numberCorrectRightPosition, int numberCorrectWrongPosition) {
        // We combine together both sets of matches and set all to the 'nearly correct' state
        // we then switch the correct answers after. This is a hack to make the logic nicer.
        showAllIncorrect();
        int totalHintsToShow = numberCorrectRightPosition + numberCorrectWrongPosition;
        if (totalHintsToShow == 4) {
            view.showRightNumberWrongPositionFour();
        }
        if (totalHintsToShow >= 3) {
            view.showRightNumberWrongPositionThree();
        }
        if (totalHintsToShow >= 2) {
            view.showRightNumberWrongPositionTwo();
        }
        if (totalHintsToShow >= 1) {
            view.showRightNumberWrongPositionOne();
        }

        // switch any 'half-right' answers to full answers if needed.
        if (numberCorrectRightPosition == 4) {
            view.showCorrectAsPositionFour();
        }
        if (numberCorrectRightPosition >= 3) {
            view.showCorrectAtPositionThree();
        }
        if (numberCorrectRightPosition >= 2) {
            view.showCorrectAtPositionTwo();
        }
        if (numberCorrectRightPosition >= 1) {
            view.showCorrectAtPositionOne();
        }
    }
}
