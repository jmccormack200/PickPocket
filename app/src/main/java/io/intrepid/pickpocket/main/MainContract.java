package io.intrepid.pickpocket.main;

public interface MainContract {

    interface View {
        void unlock();
        void lock();
    }

    interface Presenter{
        void setView(MainContract.View view);
        void buttonClicked(String s);
        void programSwitchToggled(boolean checked);
    }

}
