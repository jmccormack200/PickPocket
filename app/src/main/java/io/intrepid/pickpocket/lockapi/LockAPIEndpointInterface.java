package io.intrepid.pickpocket.lockapi;


import io.intrepid.pickpocket.models.GsonCombinationAttempt;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface LockAPIEndpointInterface {

    @POST("checkcombination")
    Observable<LockResultContainer> checkLock(@Body GsonCombinationAttempt gsonCombinationAttempt);
}
