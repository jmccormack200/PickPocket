package io.intrepid.pickpocket.lockapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Result {

    @SerializedName("close")
    @Expose
    private Integer close;
    @SerializedName("correct")
    @Expose
    private Integer correct;

    /**
     * @return The close
     */
    public Integer getClose() {
        return close;
    }

    /**
     * @param close The close
     */
    public void setClose(Integer close) {
        this.close = close;
    }

    /**
     * @return The correct
     */
    public Integer getCorrect() {
        return correct;
    }

    /**
     * @param correct The correct
     */
    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

}