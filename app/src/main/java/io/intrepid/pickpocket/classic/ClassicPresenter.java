package io.intrepid.pickpocket.classic;

import com.example.CustomAnnotation;
import com.example.annotationprocessor.generated.GeneratedClass;

import java.util.ArrayList;

public class ClassicPresenter implements ClassicContract.Presenter {

    ClassicContract.View view;

    private ArrayList<String> currentCombination;
    private ArrayList<String> newCombination;
    private int position;
    private boolean locked;
    private boolean programMode;

    ClassicPresenter() {
        position = 0;
        locked = true;
        programMode = false;
        String one = getPositionOne();
        currentCombination = new ArrayList<>();
        currentCombination.add("1");
        currentCombination.add("2");
        currentCombination.add("3");
        currentCombination.add("4");
        String two = getPositionOne();
    }

    @Override
    public void buttonClicked(String digit) {
        if (!programMode) {
            checkDigits(digit);
        } else {
            programDigits(digit);
        }
    }

    private void programDigits(String digit) {
        newCombination.add(digit);
    }

    @Override
    public void programSwitchToggled(boolean checked) {
        programMode = checked;
        position = 0;

        if (programMode) {
            newCombination = new ArrayList<>();
        } else {
            view.lock();
            locked = true;
            if (!newCombination.isEmpty()) {
                currentCombination.clear();
                currentCombination.addAll(newCombination);
            }
        }
    }

    @CustomAnnotation(method = "currentCombination.get(0)")
    public String getPositionOne() {
        GeneratedClass generatedClass = new GeneratedClass();
        return (String) generatedClass.getMessage(currentCombination, "Hi");
    }

    private void checkDigits(String digit) {
        if (!locked) {
            locked = true;
            view.lock();
        }

        if (digit.equals(currentCombination.get(position))) {
            if (position != currentCombination.size() - 1) {
                position++;
            } else {
                view.unlock();
                locked = false;
                position = 0;
            }
        } else if (digit.equals(currentCombination.get(0))) {
            position = 1;
        } else {
            locked = true;
        }
    }


    @Override
    public void setView(ClassicContract.View view) {
        this.view = view;
    }
}
