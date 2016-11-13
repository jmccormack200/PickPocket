package io.intrepid.pickpocket.locks;

import java.util.List;
import java.util.Map;

public interface LockInterface {
    String CORRECT = "CORRECT";
    String CLOSE = "CLOSE";
    Map<String, Integer> checkAnswer(List<String> guessString);
}
