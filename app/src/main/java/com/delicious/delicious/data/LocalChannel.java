package com.delicious.delicious.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tae-hwan on 8/6/16.
 */
public class LocalChannel<T> {

    @SerializedName("item")
    private List<T> items;

    @SerializedName("info")
    private LocalInfo localInfo;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public LocalInfo getLocalInfo() {
        return localInfo;
    }

    public void setLocalInfo(LocalInfo localInfo) {
        this.localInfo = localInfo;
    }
}
