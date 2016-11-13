package io.intrepid.pickpocket.locks;

import java.util.List;
import java.util.Map;

public interface LockInterface {
    static final String CORRECT = "CORRECT";
    static final String CLOSE = "CLOSE";
    Map<String, Integer> checkAnswer(List<String> guessString);
}
