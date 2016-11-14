package io.intrepid.pickpocket.codebreaker;

import java.util.List;

public class CodeBreakerGuess
{
    private List<String> guess;
    private int numberCorrect;
    private int numberInAnswer;

    public CodeBreakerGuess(List<String> guess, int numberCorrect, int numberInAnswer)
    {
        this.guess = guess;
        this.numberCorrect = numberCorrect;
        this.numberInAnswer = numberInAnswer;
    }

    public int getNumberCorrect()
    {
        return numberCorrect;
    }

    public int getNumberInAnswer()
    {
        return numberInAnswer;
    }

    public List<String> getGuess()
    {
        return guess;
    }
}