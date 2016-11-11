package io.intrepid.pickpocket.widget.answerboxes;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.intrepid.pickpocket.R;
import io.intrepid.pickpocket.widget.AnswerCheckImageView;

public class AnswerBoxView extends LinearLayout implements AnswerBoxContract.View {

    @BindView(R.id.answer_digit_one)
    AnswerCheckImageView answerCheckOne;
    @BindView(R.id.answer_digit_two)
    AnswerCheckImageView answercheckTwo;
    @BindView(R.id.answer_digit_three)
    AnswerCheckImageView answerCheckThree;
    @BindView(R.id.answer_digit_four)
    AnswerCheckImageView answerCheckFour;

    private AnswerBoxContract.Presenter presenter;

    public AnswerBoxView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setView(context, attrs);

        presenter = new AnswerBoxPresenter();
        presenter.setView(this);
    }

    private void setView(Context context, AttributeSet attrs) {
        inflate(context, R.layout.answer_box_layout, this);
        ButterKnife.bind(this);
    }

    public void setNumberCorrect(int numberCorrectRightPosition, int numberCorrectWrongPosition) {
        presenter.setNumberCorrect(numberCorrectRightPosition, numberCorrectWrongPosition);
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
    public void showAllCorrect() {
        presenter.setAllCorrect();
    }

    @Override
    public void showIncorrectAtPositionOne() {
        answerCheckOne.setImageWrong();
    }

    @Override
    public void showIncorrectAtPositionTwo() {
        answercheckTwo.setImageWrong();
    }

    @Override
    public void showIncorrectAtPositionThree() {
        answerCheckThree.setImageWrong();
    }

    @Override
    public void showIncorrectAtPositionFour() {
        answerCheckFour.setImageWrong();
    }
}
