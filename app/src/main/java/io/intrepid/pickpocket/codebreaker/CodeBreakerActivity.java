package io.intrepid.pickpocket.codebreaker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.intrepid.pickpocket.R;
import io.intrepid.pickpocket.widget.AnswerCheckImageView;

public class CodeBreakerActivity extends AppCompatActivity implements CodeBreakerContract.View {

    @BindView(R.id.answer_digit_one)
    AnswerCheckImageView answerCheckOne;
    @BindView(R.id.answer_digit_two)
    AnswerCheckImageView answercheckTwo;
    @BindView(R.id.answer_digit_three)
    AnswerCheckImageView answerCheckThree;
    @BindView(R.id.answer_digit_four)
    AnswerCheckImageView answerCheckFour;
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
    private CodeBreakerContract.Presenter presenter;

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
    public void showCorrectAtPositionOne() {
        answerCheckOne.setImageCorrect();
    }

    @Override
    public void showCorrectAtPositionTwo() {
        answercheckTwo.setImageCorrect();
    }

    @Override
    public void showCorrectAtPositionThree() {
        answerCheckThree.setImageCorrect();
    }

    @Override
    public void showCorrectAsPositionFour() {
        answerCheckFour.setImageCorrect();
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
    public void showRightNumberWrongPositionFour() {
        answerCheckFour.setImageCorrectValueWrongLocation();
    }

    @Override
    public void showRightNumberWrongPositionThree() {
        answerCheckThree.setImageCorrectValueWrongLocation();
    }

    @Override
    public void showRightNumberWrongPositionTwo() {
        answercheckTwo.setImageCorrectValueWrongLocation();
    }

    @Override
    public void showRightNumberWrongPositionOne() {
        answerCheckOne.setImageCorrectValueWrongLocation();
    }

    @Override
    public void showAllIncorrect() {
        answerCheckOne.setImageWrong();
        answercheckTwo.setImageWrong();
        answerCheckThree.setImageWrong();
        answerCheckFour.setImageWrong();
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
}
