package com.delicious.delicious.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tae-hwan on 8/6/16.
 */
public class RestaurantChannel {

    @SerializedName("channel")
    private LocalChannel<Restaurant> localChannel;


    public LocalChannel<Restaurant> getLocalChannel() {
        return localChannel;
    }

    public void setLocalChannel(LocalChannel<Restaurant> localChannel) {
        this.localChannel = localChannel;
    }
}
