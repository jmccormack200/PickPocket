package io.intrepid.pickpocket.locks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.intrepid.pickpocket.lockapi.LockAPIEndpointInterface;
import io.intrepid.pickpocket.lockapi.OnlineLockResultContainer;
import io.intrepid.pickpocket.lockapi.Result;
import io.intrepid.pickpocket.models.GsonLock;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class OnlineLock implements LockInterface {

    public static final String BASE_URL = "https://g557v08nj5.execute-api.us-east-1.amazonaws.com/test/";

    private LockAPIEndpointInterface api;

    public OnlineLock(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(LockAPIEndpointInterface.class);

    }

    @Override
    public Map<String, Integer> checkAnswer(List<String> guessString) {
        final Map<String, Integer> resultMap = new HashMap<>();
        String listAsString = guessString.toString();
        final GsonLock gsonLock = new GsonLock("2 2 2 2", "Dad");
        Call<OnlineLockResultContainer> call = api.checkLock(gsonLock);

        call.enqueue(new Callback<OnlineLockResultContainer>() {
            @Override
            public void onResponse(Call<OnlineLockResultContainer> call, Response<OnlineLockResultContainer> response) {
                Result result = response.body().getResult();
                resultMap.put(CORRECT, result.getCorrect());
                resultMap.put(CLOSE, result.getClose());
            }

            @Override
            public void onFailure(Call<OnlineLockResultContainer> call, Throwable t) {
                Timber.e("Nope nope nope");
            }
        });
        return resultMap;
    }

    /*
    User user = new User(123, "John Doe");
Call<User> call = apiService.createuser(user);
call.enqueue(new Callback<User>() {
  @Override
  public void onResponse(Call<User> call, Response<User> response) {

  }

  @Override
  public void onFailure(Call<User> call, Throwable t) {

  }
     */
}
