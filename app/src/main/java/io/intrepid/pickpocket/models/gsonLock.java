package io.intrepid.pickpocket.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class GsonLock {

    @SerializedName("guess")
    @Expose
    private String guess;
    @SerializedName("user")
    @Expose
    private String user;

    public GsonLock(String guess, String user){
        this.guess = guess;
        this.user = user;
    }

    /**
     * @return The guess
     */
    public String getGuess() {
        return guess;
    }

    /**
     * @param guess The guess
     */
    public void setGuess(String guess) {
        this.guess = guess;
    }

    /**
     * @return The user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user The user
     */
    public void setUser(String user) {
        this.user = user;
    }

}
