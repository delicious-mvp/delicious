package com.delicious.delicious.ui.restaurants.adpater.displayitem;


//phone: "02-3472-6073",
//newAddress: "서울 관악구 남부순환로 2082-25",
//thumbnailUrl: "http://cfile190.uf.daum.net/image/276EEE4254852ADA365553",
//direction: "남동",
//zipcode: "151800",
//placeUrl: "http://place.map.daum.net/26344965",
//id: "26344965",
//title: "고베그릴",
//distance: "225",
//category: "음식점 > 한식 > 육류,고기",
//address: "서울 관악구 남현동 1060-10",
//longitude: "126.9810205750391",
//latitude: "37.476048571605155",
//addressBCode: "1162010300"
public class RestaurantDisplayItem implements DisplayItem {

    private String title;
    private String address;
    private String category;
    private String thumbnailUrl;
    private String distance;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
