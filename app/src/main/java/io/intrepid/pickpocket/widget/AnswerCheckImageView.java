package io.intrepid.pickpocket.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import io.intrepid.pickpocket.R;

public class AnswerCheckImageView extends ImageView {
    private boolean colorWhite;

    public AnswerCheckImageView(Context context) {
        super(context);
        setImageWrong();
    }

    public AnswerCheckImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AnswerCheckImageView, 0, 0);
        try {
            colorWhite = typedArray.getBoolean(R.styleable.AnswerCheckImageView_colorWhite, false);
        } finally {
            typedArray.recycle();
        }
        setImageWrong();
    }

    public AnswerCheckImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setImageWrong();
    }

    public void setImageWrong() {
        setBackground(getContext().getDrawable(colorWhite ? R.drawable.ic_check_box_outline_blank_white_24px
                                                       : R.drawable.ic_check_box_outline_blank_black_24px));
    }

    public void setImageCorrectValueWrongLocation() {
        setBackground(getContext().getDrawable(colorWhite ? R.drawable.ic_indeterminate_check_box_white_24px
                                                       : R.drawable.ic_indeterminate_check_box_black_24px));
    }

    public void setImageCorrect() {
        setBackground(getContext().getDrawable(colorWhite ? R.drawable.ic_check_box_white_24px
                                                       : R.drawable.ic_check_box_black_24px));
    }
}
