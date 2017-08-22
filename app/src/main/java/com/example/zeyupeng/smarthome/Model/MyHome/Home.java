package com.example.zeyupeng.smarthome.Model.MyHome;

import com.amazonaws.models.nosql.HomeData;

/**
 * Created by zeyu peng on 2017-06-22.
 */

public class Home extends AbstractHome {
    public void importData(HomeData homeData){
        this.setHomeID(homeData.getHomeID());
        this.setRoomMap(homeData.getRoomMap());
    }
}
