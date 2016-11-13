package io.intrepid.pickpocket.lockapi;

import io.intrepid.pickpocket.models.GsonLock;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LockAPIEndpointInterface {

    @POST("checkcombination")
    Call<OnlineLockResultContainer> checkLock(@Body GsonLock gsonLock);
}
