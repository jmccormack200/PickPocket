package io.intrepid.pickpocket.codebreaker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.intrepid.pickpocket.R;
import io.intrepid.pickpocket.historydrawer.ViewAnimator;
import io.intrepid.pickpocket.widget.AnswerCheckImageView;
import io.intrepid.pickpocket.widget.answerboxes.AnswerBoxView;

import static android.R.id.list;

public class CodeBreakerActivity extends AppCompatActivity implements CodeBreakerContract.View, ViewAnimator.ViewAnimatorListener {


    @BindView(R.id.guess_digit_one)
    TextView guessDigitOne;
    @BindView(R.id.guess_digit_two)
    TextView guessDigitTwo;
    @BindView(R.id.guess_digit_three)
    TextView guessDigitThree;
    @BindView(R.id.guess_digit_four)
    TextView guessDigitFour;
    @BindView(R.id.lock_icon)
    CheckedTextView lockIcon;
    @BindView(R.id.answer_boxes)
    AnswerBoxView answerBoxView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.left_drawer)
    LinearLayout linearLayout;


    private CodeBreakerContract.Presenter presenter;
    private ViewAnimator viewAnimator;

    public static Intent makeIntent(Context context) {
        return new Intent(context, CodeBreakerActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_breaker);
        ButterKnife.bind(this);

        presenter = new CodeBreakerPresenter();
        presenter.setView(this);
        drawerLayout.setScrimColor(Color.BLACK);
        lockIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                drawerLayout.openDrawer(Gravity.LEFT);
                viewAnimator.showMenuContent();
            }
        });

        viewAnimator = new ViewAnimator(this, presenter.getCodeBreakerGuessList(), drawerLayout, this);
    }

    // TODO: this could be reduced with a custom class that handles this
    @Override
    public void showGuessInPositionOne(String guess) {
        guessDigitOne.setText(guess);
    }

    @Override
    public void showGuessInPositionTwo(String guess) {
        guessDigitTwo.setText(guess);
    }

    @Override
    public void showGuessInPositionThree(String guess) {
        guessDigitThree.setText(guess);
    }

    @Override
    public void showGuessInPositionFour(String guess) {
        guessDigitFour.setText(guess);
    }

    @Override
    public void unlock() {
        lockIcon.setChecked(true);
    }

    @Override
    public void lock() {
        lockIcon.setChecked(false);
    }

    @Override
    public void setNumberCorrect(int numberCorrect, int numberInAnswer) {
        answerBoxView.setNumberCorrect(numberCorrect, numberInAnswer);
    }

    @Override
    public void showAllCorrect() {
        answerBoxView.showAllCorrect();
    }

    @OnClick({ R.id.button_one, R.id.button_two, R.id.button_three, R.id.button_four, R.id.button_five, R.id.button_six })
    public void onDigitClicked(View view) {
        Button button = (Button) view;
        presenter.buttonClicked(button.getText().toString());
    }

    @OnClick(R.id.check_answer)
    public void onCheckAnswerClicked() {
        presenter.onCheckAnswerClicked();
    }

    @Override
    public void addViewToContainer(View view)
    {
        linearLayout.addView(view);
    }

    @Override
    public void removeViewsFromContainers()
    {
        if(linearLayout != null)
        {
            linearLayout.removeAllViews();
            linearLayout.invalidate();
        }
    }
}
