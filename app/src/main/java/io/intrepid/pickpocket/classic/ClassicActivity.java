package io.intrepid.pickpocket.classic;

import android.content.Context;
import android.content.Intent;
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
import butterknife.OnTouch;
import io.intrepid.pickpocket.R;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class ClassicActivity extends AppCompatActivity implements ClassicContract.View {

    @BindView(R.id.lock_icon)
    CheckedTextView lockIcon;

    @BindView(R.id.programming_mode_switch)
    Switch programmingModeSwitch;

    @BindView(R.id.programming_mode_label)
    TextView programmingModeLabel;

    private ClassicContract.Presenter presenter;

    public static Intent makeIntent(Context context){
        return new Intent(context, ClassicActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic);
        ButterKnife.bind(this);

        presenter = new ClassicPresenter();
        presenter.setView(this);
        setTitle(R.string.classic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @OnTouch(R.id.programming_mode_switch)
    public boolean onTouch(){
        presenter.programSwitchToggled(!programmingModeSwitch.isChecked());
        return false;
    }
}
