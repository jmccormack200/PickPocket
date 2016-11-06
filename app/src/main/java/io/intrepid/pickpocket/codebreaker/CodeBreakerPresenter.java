package io.intrepid.pickpocket.codebreaker;

public class CodeBreakerPresenter implements CodeBreakerContract.Presenter {

    CodeBreakerContract.View view;

    @Override
    public void setView(CodeBreakerContract.View view) {
        this.view = view;
    }
}
