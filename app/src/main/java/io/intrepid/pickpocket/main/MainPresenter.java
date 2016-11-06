package io.intrepid.pickpocket.main;

public class MainPresenter implements MainContract.Presenter {

    MainContract.View view;

    private String[] currentCombination = { "1", "2", "3", "4" };
    private int position;
    private boolean locked;
    private boolean programMode;

    MainPresenter() {
        position = 0;
        locked = true;
        programMode = false;
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

    }

    private void checkDigits(String digit) {
        if (!locked) {
            locked = true;
            view.lock();
        }

        if (digit.equals(currentCombination[position])) {
            if (position != currentCombination.length - 1) {
                position++;
            } else {
                view.unlock();
                locked = false;
                position = 0;
            }
        } else if (digit.equals(currentCombination[0])) {
            position = 1;
        } else {
            locked = true;
        }
    }


    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }
}
