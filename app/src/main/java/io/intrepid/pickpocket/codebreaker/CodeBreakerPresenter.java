package io.intrepid.pickpocket.codebreaker;

import java.util.ArrayList;

import io.intrepid.pickpocket.lockapi.LockResultContainer;
import io.intrepid.pickpocket.lockapi.Result;
import io.intrepid.pickpocket.locks.LockInterface;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class CodeBreakerPresenter implements CodeBreakerContract.Presenter {

    private ArrayList<String> guessCombination;
    private int position;
    private LockInterface lockInterface;

    private CodeBreakerContract.View view;

    CodeBreakerPresenter(CodeBreakerActivity.CODE_BREAKER_MODE codeBreakerMode) {
        guessCombination = new ArrayList<>();

        guessCombination.add("");
        guessCombination.add("");
        guessCombination.add("");
        guessCombination.add("");
        lockInterface = codeBreakerMode.createLock();
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
        Observable<LockResultContainer> answerMap = lockInterface.checkAnswer(guessCombination);

        answerMap.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(this::showResult, throwable -> {
                    Timber.e("Error fetching data %s: ", throwable.getMessage());
                });
    }

    private void showResult(LockResultContainer success) {
        Result result = success.getResult();
        view.setNumberCorrect(result.getCorrect(), result.getClose());
        if (result.getClose() == guessCombination.size()) {
            view.unlock();
        }
    }
}
