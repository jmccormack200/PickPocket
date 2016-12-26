package io.intrepid.pickpocket.launch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.intrepid.pickpocket.R;
import io.intrepid.pickpocket.classic.ClassicActivity;
import io.intrepid.pickpocket.codebreaker.CodeBreakerActivity;

public class LaunchActivity extends AppCompatActivity implements LaunchContract.View {

    @BindView(R.id.classic_mode)
    Button button;
    private LaunchContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ButterKnife.bind(this);
        presenter = new LaunchPresenter();
        presenter.setView(this);
    }

    @OnClick(R.id.classic_mode)
    public void classicModeClicked() {
        presenter.classicModeClicked();
    }

    @OnClick(R.id.code_breaking_mode)
    public void codeBreakingModeClicked() {
        presenter.codeBreakingModeClicked();
    }

    @Override
    public void startClassicMode() {
        startActivity(ClassicActivity.makeIntent(this));
    }

    @Override
    public void startCodeBreakingMode() {
        startActivity(CodeBreakerActivity.makeLocalIntent(this));
    }

    @Override
    public void startOnlineMode() {
        startActivity(CodeBreakerActivity.makeOnlineIntent(this));
    }

    @OnClick(R.id.online_mode)
    public void onlineModeClicked() {
        presenter.onlineModeClicked();
    }
}
