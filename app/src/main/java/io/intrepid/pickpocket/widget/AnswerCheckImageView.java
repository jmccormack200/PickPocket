package io.intrepid.pickpocket.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import io.intrepid.pickpocket.R;

public class AnswerCheckImageView extends ImageView {

    public AnswerCheckImageView(Context context) {
        super(context);
        setImageWrong();
    }

    public AnswerCheckImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setImageWrong();
    }

    public AnswerCheckImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setImageWrong();
    }

    public void setImageWrong(){
        setBackground(getContext().getDrawable(R.drawable.ic_check_box_outline_blank_black_24px));
    }

    public void setImageCorrectValueWrongLocation(){
        setBackground(getContext().getDrawable(R.drawable.ic_indeterminate_check_box_black_24px));
    }

    public void setImageCorrect(){
        setBackground(getContext().getDrawable(R.drawable.ic_check_box_black_24px));
    }
}
