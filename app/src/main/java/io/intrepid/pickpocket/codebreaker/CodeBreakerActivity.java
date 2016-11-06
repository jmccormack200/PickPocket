package io.intrepid.pickpocket.codebreaker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import io.intrepid.pickpocket.R;

public class CodeBreakerActivity extends AppCompatActivity implements CodeBreakerContract.View {

    private CodeBreakerContract.Presenter presenter;

    public static Intent makeIntent(Context context){
        return new Intent(context, CodeBreakerActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_breaker);
        ButterKnife.bind(this);

        presenter = new CodeBreakerPresenter();
        presenter.setView(this);
    }

}
