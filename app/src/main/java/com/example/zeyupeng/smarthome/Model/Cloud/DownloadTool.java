package com.example.zeyupeng.smarthome.Model.Cloud;

import android.util.Log;

import com.amazonaws.AmazonClientException;
import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.models.nosql.ACData;
import com.amazonaws.models.nosql.AdjustableLightData;
import com.amazonaws.models.nosql.ColorLightData;
import com.amazonaws.models.nosql.CurtainData;
import com.amazonaws.models.nosql.GarageDoorData;
import com.amazonaws.models.nosql.HomeData;
import com.amazonaws.models.nosql.NFCDoorData;
import com.amazonaws.models.nosql.OvenData;
import com.amazonaws.models.nosql.PlanData;
import com.amazonaws.models.nosql.RangeHoodData;
import com.amazonaws.models.nosql.RoomData;
import com.example.zeyupeng.smarthome.Model.DataOnHub.DeviceDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnHub.HomeDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnHub.PlanDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnHub.RoomDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.DeviceDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.HomeDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.PlanDataOnMobile;
import com.example.zeyupeng.smarthome.Model.DataOnMobile.RoomDataOnMobile;
import com.example.zeyupeng.smarthome.Model.MyDevices.Access.GarageDoor;
import com.example.zeyupeng.smarthome.Model.MyDevices.Access.NFCDoor;
import com.example.zeyupeng.smarthome.Model.MyDevices.Cooking.Oven;
import com.example.zeyupeng.smarthome.Model.MyDevices.Cooking.RangeHood;
import com.example.zeyupeng.smarthome.Model.MyDevices.CoolingAndHeating.AC;
import com.example.zeyupeng.smarthome.Model.MyDevices.Lighting.AdjustableLight;
import com.example.zeyupeng.smarthome.Model.MyDevices.Lighting.ColorLight;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductName;
import com.example.zeyupeng.smarthome.Model.MyDevices.Shading.Curtain;
import com.example.zeyupeng.smarthome.Model.MyHome.Home;
import com.example.zeyupeng.smarthome.Model.MyPlans.Plan;
import com.example.zeyupeng.smarthome.Model.MyRooms.Room;

import java.util.Map;

/**
 * Created by zeyu peng on 2017-07-21.
 */

public class DownloadTool {
    public static final int DOWNLOAD_MOBILE_DATA=0;
    public static final int DOWNLOAD_HUB_DATA=1;
    private int downloadOption;

    public DownloadTool(int downloadOption) {
        this.downloadOption = downloadOption;
    }


    public void downloadData(){

        final DynamoDBMapper dynamoDBMapper = AWSMobileClient.defaultMobileClient().getDynamoDBMapper();
        AmazonClientException lastException = null;

        try {
            HomeData homeData =dynamoDBMapper.load(HomeData.class,AWSMobileClient.defaultMobileClient().getIdentityManager().getCachedUserID());
            if (homeData!=null){
                Home home =new Home() ;
                home.importData(homeData);
                if(downloadOption==DOWNLOAD_HUB_DATA){
                    HomeDataOnHub.getInstance().modifyHome(home);
                }
                if(downloadOption==DOWNLOAD_MOBILE_DATA){
                    HomeDataOnMobile.getInstance().modifyHome(home);
                }
                for(Map.Entry<String, String> entryForHome: home.getRoomMap().entrySet()){
                    Log.i("gggggggggggetkey",entryForHome.getKey());
                    RoomData roomData = dynamoDBMapper.load(RoomData.class,entryForHome.getKey());
                    if(roomData!=null){
                        Room room =new Room();
                        room.importData(roomData);
                        if(downloadOption==DOWNLOAD_HUB_DATA){
                            RoomDataOnHub.getInstance().addRoom(room);
                        }
                        if(downloadOption==DOWNLOAD_MOBILE_DATA){
                            RoomDataOnMobile.getInstance().addRoom(room);
                        }


                        for (Map.Entry<String, String> entryForRoom: room.getDeviceMap().entrySet()) {
                            switch (entryForRoom.getValue()){
                                case ProductName.COLOR_LIGHT:
                                    ColorLightData colorLightData = dynamoDBMapper.load(ColorLightData.class,entryForRoom.getKey());
                                    if(colorLightData!=null){
                                        ColorLight colorLight =new ColorLight();
                                        colorLight.importData(colorLightData);
                                        if(downloadOption==DOWNLOAD_HUB_DATA){
                                            DeviceDataOnHub.getInstance().addDevice(colorLight);
                                        }
                                        if(downloadOption==DOWNLOAD_MOBILE_DATA){
                                            DeviceDataOnMobile.getInstance().addDevice(colorLight);
                                        }
                                    }
                                    break;
                                case ProductName.ADJUSTABLE_LIGHT:
                                    AdjustableLightData adjustableLightData
                                            = dynamoDBMapper.load(AdjustableLightData.class,entryForRoom.getKey());
                                    if(adjustableLightData!=null){
                                        AdjustableLight adjustableLight =new AdjustableLight();
                                        adjustableLight.importData(adjustableLightData);
                                        if(downloadOption==DOWNLOAD_HUB_DATA){
                                            DeviceDataOnHub.getInstance().addDevice(adjustableLight);
                                        }
                                        if(downloadOption==DOWNLOAD_MOBILE_DATA){
                                            DeviceDataOnMobile.getInstance().addDevice(adjustableLight);
                                        }
                                    }
                                    break;
                                case ProductName.NFC_DOOR:
                                    NFCDoorData nfcDoorData = dynamoDBMapper.load(NFCDoorData.class,entryForRoom.getKey());
                                    if(nfcDoorData!=null){
                                        NFCDoor nfcDoor =new NFCDoor();
                                        nfcDoor.importData(nfcDoorData);
                                        if(downloadOption==DOWNLOAD_HUB_DATA){
                                            DeviceDataOnHub.getInstance().addDevice(nfcDoor);
                                        }
                                        if(downloadOption==DOWNLOAD_MOBILE_DATA){
                                            DeviceDataOnMobile.getInstance().addDevice(nfcDoor);
                                        }
                                    }
                                    break;
                                case ProductName.GARAGE_DOOR:
                                    GarageDoorData garageDoorData = dynamoDBMapper.load(GarageDoorData.class,entryForRoom.getKey());
                                    if(garageDoorData!=null){
                                        GarageDoor garageDoor =new GarageDoor();
                                        garageDoor.importData(garageDoorData);
                                        if(downloadOption==DOWNLOAD_HUB_DATA){
                                            DeviceDataOnHub.getInstance().addDevice(garageDoor);
                                        }
                                        if(downloadOption==DOWNLOAD_MOBILE_DATA){
                                            DeviceDataOnMobile.getInstance().addDevice(garageDoor);
                                        }
                                    }
                                    break;
                                case ProductName.OVEN:
                                    OvenData ovenData = dynamoDBMapper.load(OvenData.class,entryForRoom.getKey());
                                    if(ovenData!=null){
                                        Oven oven =new Oven();
                                        oven.importData(ovenData);
                                        if(downloadOption==DOWNLOAD_HUB_DATA){
                                            DeviceDataOnHub.getInstance().addDevice(oven);
                                        }
                                        if(downloadOption==DOWNLOAD_MOBILE_DATA){
                                            DeviceDataOnMobile.getInstance().addDevice(oven);
                                        }
                                    }
                                    break;
                                case ProductName.RANGEHOOD:
                                    RangeHoodData rangeHoodData = dynamoDBMapper.load(RangeHoodData.class,entryForRoom.getKey());
                                    if(rangeHoodData!=null){
                                        RangeHood rangeHood =new RangeHood();
                                        rangeHood.importData(rangeHoodData);
                                        if(downloadOption==DOWNLOAD_HUB_DATA){
                                            DeviceDataOnHub.getInstance().addDevice(rangeHood);
                                        }
                                        if(downloadOption==DOWNLOAD_MOBILE_DATA){
                                            DeviceDataOnMobile.getInstance().addDevice(rangeHood);
                                        }
                                    }
                                    break;
                                case ProductName.AC:
                                    ACData acData = dynamoDBMapper.load(ACData.class,entryForRoom.getKey());
                                    if(acData!=null){
                                        AC ac =new AC();
                                        ac.importData(acData);
                                        if(downloadOption==DOWNLOAD_HUB_DATA){
                                            DeviceDataOnHub.getInstance().addDevice(ac);
                                        }
                                        if(downloadOption==DOWNLOAD_MOBILE_DATA){
                                            DeviceDataOnMobile.getInstance().addDevice(ac);
                                        }


                                    }
                                    break;
                                case ProductName.CURTAIN:
                                    CurtainData curtainData = dynamoDBMapper.load(CurtainData.class,entryForRoom.getKey());
                                    if(curtainData!=null){
                                        Curtain curtain =new Curtain();
                                        curtain.importData(curtainData);
                                        if(downloadOption==DOWNLOAD_HUB_DATA){
                                            DeviceDataOnHub.getInstance().addDevice(curtain);
                                        }
                                        if(downloadOption==DOWNLOAD_MOBILE_DATA){
                                            DeviceDataOnMobile.getInstance().addDevice(curtain);
                                        }
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
            PlanData planData = dynamoDBMapper.load(PlanData.class,AWSMobileClient.defaultMobileClient().getIdentityManager().getCachedUserID());
            if (planData!=null) {
                for (Map.Entry<String, Plan> entryForPlan : planData.getPlanMap().entrySet()) {
                    if(downloadOption==DOWNLOAD_HUB_DATA){
                        PlanDataOnHub.getInstance().addPlan(entryForPlan.getValue());
                    }
                    if(downloadOption==DOWNLOAD_MOBILE_DATA){
                        PlanDataOnMobile.getInstance().addPlan(entryForPlan.getValue());
                    }
                    Log.i("asyyyyyyyntask", "onProgressUpdate" + entryForPlan.getValue().getPlanID());
                }
                Log.i("asyyyyyyyntask", "onProgressUpdate");
            }
        }
        catch (final AmazonClientException ex) {
            Log.i("dynamoDB..........", "Failed getting item : " + ex.getMessage(), ex);
            lastException = ex;
        }
    }
}
