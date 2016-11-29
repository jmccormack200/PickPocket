package io.intrepid.pickpocket.locks;

import java.util.List;

import io.intrepid.pickpocket.lockapi.LockAPIEndpointInterface;
import io.intrepid.pickpocket.lockapi.LockResultContainer;
import io.intrepid.pickpocket.models.GsonCombinationAttempt;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OnlineLock implements LockInterface {

    public static final String BASE_URL = "https://g557v08nj5.execute-api.us-east-1.amazonaws.com/test/";

    private LockAPIEndpointInterface api;

    public OnlineLock(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        api = retrofit.create(LockAPIEndpointInterface.class);

    }

    @Override
    public rx.Observable<LockResultContainer> checkAnswer(List<String> guessStringList) {
        String guestString = listOfStringsToString(guessStringList);
        final GsonCombinationAttempt gsonCombinationAttempt = new GsonCombinationAttempt(guestString, "Dad");
        return api.checkLock(gsonCombinationAttempt);
    }

    private String listOfStringsToString(List<String> stringList){
        String outputString = "";
        for (String digit : stringList){
            outputString = outputString + digit + " ";
        }
        return outputString;
    }
}
