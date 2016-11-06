package io.intrepid.pickpocket.launch;

public interface LaunchContract {

    interface View {
        void startClassicMode();
        void startCodeBreakingMode();
        void startOnlineMode();
    }

    interface Presenter {
        void setView(LaunchContract.View view);
        void classicModeClicked();
        void codeBreakingModeClicked();
        void onlineModeClicked();
    }

}
