package io.intrepid.pickpocket.widget.answerboxes;

public interface AnswerBoxContract {

    interface View {
        void showCorrectAtPositionOne();
        void showCorrectAtPositionTwo();
        void showCorrectAtPositionThree();
        void showCorrectAsPositionFour();
        void showRightNumberWrongPositionFour();
        void showRightNumberWrongPositionThree();
        void showRightNumberWrongPositionTwo();
        void showRightNumberWrongPositionOne();
        void showAllCorrect();
        void showIncorrectAtPositionOne();
        void showIncorrectAtPositionTwo();
        void showIncorrectAtPositionThree();
        void showIncorrectAtPositionFour();
    }

    interface Presenter {
        void setView(AnswerBoxContract.View view);
        void setNumberCorrect(int numberCorrectRightPosition, int numberCorrectWrongPosition);
        void setAllCorrect();
        void showAllIncorrect();
    }
}
