package io.intrepid.pickpocket.historydrawer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.intrepid.pickpocket.R;
import io.intrepid.pickpocket.codebreaker.CodeBreakerGuess;
import io.intrepid.pickpocket.widget.answerboxes.AnswerBoxView;

public class ViewAnimator
{
    private final int ANIMATION_DURATION = 300;
    private Activity activity;
    private List<CodeBreakerGuess> list;
    private List<View> viewList = new ArrayList<>();
    private DrawerLayout drawerLayout;
    private ViewAnimatorListener animatorListener;

    public ViewAnimator(Activity activity,
                        List<CodeBreakerGuess> items,
                        final DrawerLayout drawerLayout,
                        ViewAnimatorListener animatorListener)
    {
        this.activity = activity;
        this.list = items;
        this.drawerLayout = drawerLayout;
        this.drawerLayout.setScrimColor(Color.BLACK);
        this.animatorListener = animatorListener;
        this.drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener()
        {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset)
            {

            }

            @Override
            public void onDrawerOpened(View drawerView)
            {
                drawerLayout.setZ(1);
            }

            @Override
            public void onDrawerClosed(View drawerView)
            {
                drawerLayout.setZ(-1);
            }

            @Override
            public void onDrawerStateChanged(int newState)
            {

            }
        });
    }

    public void showMenuContent()
    {
        viewList.clear();
        animatorListener.removeViewsFromContainers();
        double size = list.size();
        for (int i = 0; i < size; i++)
        {
            View viewMenu = activity.getLayoutInflater().inflate(R.layout.menu_list_item, null);
            ((TextView) viewMenu.findViewById(R.id.menu_item_label)).setText(TextUtils.join("",
                    list.get(i).getGuess()));
            ((AnswerBoxView) viewMenu.findViewById(R.id.answer_check)).setNumberCorrect(list.get(i).getNumberCorrect(), list.get(i).getNumberInAnswer());
            viewMenu.setVisibility(View.GONE);
            viewMenu.setEnabled(false);
            viewList.add(viewMenu);
            animatorListener.addViewToContainer(viewMenu);
            final double position = i;
            final double delay = 3 * ANIMATION_DURATION * (position / size);
            new Handler().postDelayed(new Runnable()
            {
                public void run()
                {
                    if (position < viewList.size())
                    {
                        animateView((int) position);
                    }
                }
            }, (long) delay);
        }
    }

    private void animateView(int position)
    {
        final View view = viewList.get(position);
        view.setVisibility(View.VISIBLE);
        FlipAnimation rotation =
                new FlipAnimation(90, 0, 0.0f, view.getHeight() / 2.0f);
        rotation.setDuration(ANIMATION_DURATION);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                view.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }
        });

        view.startAnimation(rotation);
    }

    public interface ViewAnimatorListener
    {
        public void addViewToContainer(View view);

        public void removeViewsFromContainers();
    }
}