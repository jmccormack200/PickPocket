package io.intrepid.pickpocket.codebreaker;

public interface CodeBreakerContract {

    interface View {

    }

    interface Presenter {
        void setView(View codeBreakerActivity);
    }
}
