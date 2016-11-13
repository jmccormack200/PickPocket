package io.intrepid.pickpocket.locks;

import java.util.List;

import io.intrepid.pickpocket.lockapi.LockResultContainer;

public interface LockInterface {
    String CORRECT = "CORRECT";
    String CLOSE = "CLOSE";
    rx.Observable<LockResultContainer> checkAnswer(List<String> guessString);
}
