package com.example.zeyupeng.smarthome.Model.DataOnMobile;

/**
 * Created by zeyu peng on 2017-06-27.
 */

public interface Observer {
    
    void update(ObserverActions action,Object object);
}
