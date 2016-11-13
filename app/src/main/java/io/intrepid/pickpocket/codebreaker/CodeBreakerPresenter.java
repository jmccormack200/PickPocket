package io.intrepid.pickpocket.codebreaker;

import java.util.ArrayList;
import java.util.Map;

import io.intrepid.pickpocket.locks.LocalLock;
import io.intrepid.pickpocket.locks.LockInterface;

import static io.intrepid.pickpocket.locks.LockInterface.CLOSE;
import static io.intrepid.pickpocket.locks.LockInterface.CORRECT;

public class CodeBreakerPresenter implements CodeBreakerContract.Presenter {

    private ArrayList<String> guessCombination;
    private int position;
    private LockInterface lockInterface;

    private CodeBreakerContract.View view;

    CodeBreakerPresenter() {
        guessCombination = new ArrayList<>();

        guessCombination.add("");
        guessCombination.add("");
        guessCombination.add("");
        guessCombination.add("");
        lockInterface = new LocalLock();
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
        view.lock();
        countNearMatches();
    }

    private void countNearMatches() {
        Map<String, Integer> answerMap = lockInterface.checkAnswer(guessCombination);
        view.setNumberCorrect(answerMap.get(CORRECT), answerMap.get(CLOSE));
        if (answerMap.get(CORRECT) == guessCombination.size()){
            view.unlock();
        }
    }
}
