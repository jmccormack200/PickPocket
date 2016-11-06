package io.intrepid.pickpocket.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.intrepid.pickpocket.R;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.lock_icon)
    CheckedTextView lockIcon;

    @BindView(R.id.programming_mode_switch)
    Switch programmingModeSwitch;

    @BindView(R.id.programming_mode_label)
    TextView programmingModeLabel;

    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter();
        presenter.setView(this);
    }

    @Override
    public void unlock() {
        lockIcon.setChecked(true);
        programmingModeLabel.setVisibility(VISIBLE);
        programmingModeSwitch.setVisibility(VISIBLE);
    }

    @Override
    public void lock() {
        lockIcon.setChecked(false);
        programmingModeLabel.setVisibility(INVISIBLE);
        programmingModeSwitch.setVisibility(INVISIBLE);
    }

    @OnClick({R.id.button_one, R.id.button_two, R.id.button_three, R.id.button_four, R.id.button_five, R.id.button_six})
    public void onDigitClicked(View view){
        Button button = (Button) view;
        presenter.buttonClicked(button.getText().toString());
    }
}
