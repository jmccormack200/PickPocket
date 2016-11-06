package io.intrepid.pickpocket.classic;

public interface ClassicContract {

    interface View {
        void unlock();
        void lock();
    }

    interface Presenter{
        void setView(ClassicContract.View view);
        void buttonClicked(String s);
        void programSwitchToggled(boolean checked);
    }

}
