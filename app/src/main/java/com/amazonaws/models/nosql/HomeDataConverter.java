package com.amazonaws.models.nosql;


import com.amazonaws.mobile.AWSMobileClient;
import com.example.zeyupeng.smarthome.Model.MyHome.Home;

/**
 * Created by zeyu peng on 2017-06-22.
 */

public class HomeDataConverter {
    private HomeData mHomeData;
    public HomeData convertToMyHomeData(Home home){
        this.mHomeData = new HomeData();

        this.mHomeData.setHomeID(AWSMobileClient.defaultMobileClient().getIdentityManager().getCachedUserID());
        this.mHomeData.setRoomMap(home.getRoomMap());

        return  this.mHomeData;
    }
}
