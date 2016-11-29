package io.intrepid.pickpocket.codebreaker;

import java.util.List;

interface CodeBreakerContract {

    interface View {
        void showGuessInPositionOne(String guess);
        void showGuessInPositionTwo(String guess);
        void showGuessInPositionThree(String guess);
        void showGuessInPositionFour(String guess);
        void unlock();
        void lock();
        void setNumberCorrect(int numberCorrect, int numberInAnswer);
        void showAllCorrect();
    }

    interface Presenter {
        List<CodeBreakerGuess> getCodeBreakerGuessList();
        void setView(View codeBreakerActivity);
        void buttonClicked(String s);
        void onCheckAnswerClicked();
    }
}
