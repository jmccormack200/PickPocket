package io.intrepid.pickpocket.launch;

public class LaunchPresenter implements LaunchContract.Presenter{

    LaunchContract.View view;

    @Override
    public void classicModeClicked() {
        view.startClassicMode();
    }

    @Override
    public void codeBreakingModeClicked() {
        view.startCodeBreakingMode();
    }

    @Override
    public void onlineModeClicked() {
        view.startOnlineMode();
    }

    @Override
    public void setView(LaunchContract.View view) {
        this.view = view;
    }
}
