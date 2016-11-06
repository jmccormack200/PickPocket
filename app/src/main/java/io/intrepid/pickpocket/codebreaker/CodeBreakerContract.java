package io.intrepid.pickpocket.codebreaker;

public interface CodeBreakerContract {

    interface View {
        void showGuessInPositionOne(String guess);
        void showGuessInPositionTwo(String guess);
        void showGuessInPositionThree(String guess);
        void showGuessInPositionFour(String guess);
        void showCorrectAtPositionOne();
        void showCorrectAtPositionTwo();
        void showCorrectAtPositionThree();
        void showCorrectAsPositionFour();
        void unlock();
        void showRightNumberWrongPositionFour();
        void showRightNumberWrongPositionThree();
        void showRightNumberWrongPositionTwo();
        void showRightNumberWrongPositionOne();
        void showAllIncorrect();
        void lock();
    }

    interface Presenter {
        void setView(View codeBreakerActivity);
        void buttonClicked(String s);
        void onCheckAnswerClicked();
    }
}
