package com.example.zeyupeng.smarthome.Model.Cloud;

import android.util.Log;

import com.amazonaws.AmazonClientException;
import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.models.nosql.ACData;
import com.amazonaws.models.nosql.ACDataConverter;
import com.amazonaws.models.nosql.AdjustableLightData;
import com.amazonaws.models.nosql.AdjustableLightDataConverter;
import com.amazonaws.models.nosql.ColorLightData;
import com.amazonaws.models.nosql.ColorLightDataConverter;
import com.amazonaws.models.nosql.CurtainData;
import com.amazonaws.models.nosql.CurtainDataConverter;
import com.amazonaws.models.nosql.GarageDoorData;
import com.amazonaws.models.nosql.GarageDoorDataConverter;
import com.amazonaws.models.nosql.HomeData;
import com.amazonaws.models.nosql.HomeDataConverter;
import com.amazonaws.models.nosql.NFCDoorData;
import com.amazonaws.models.nosql.NFCDoorDataConverter;
import com.amazonaws.models.nosql.OvenData;
import com.amazonaws.models.nosql.OvenDataConverter;
import com.amazonaws.models.nosql.PlanData;
import com.amazonaws.models.nosql.PlanDataConverter;
import com.amazonaws.models.nosql.RangeHoodData;
import com.amazonaws.models.nosql.RangeHoodDataConverter;
import com.amazonaws.models.nosql.RoomData;
import com.amazonaws.models.nosql.RoomDataConverter;
import com.example.zeyupeng.smarthome.Model.DataOnHub.DeviceDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnHub.HomeDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnHub.PlanDataOnHub;
import com.example.zeyupeng.smarthome.Model.DataOnHub.RoomDataOnHub;
import com.example.zeyupeng.smarthome.Model.MyDevices.AbstractDevice;
import com.example.zeyupeng.smarthome.Model.MyDevices.Access.GarageDoor;
import com.example.zeyupeng.smarthome.Model.MyDevices.Access.NFCDoor;
import com.example.zeyupeng.smarthome.Model.MyDevices.Cooking.Oven;
import com.example.zeyupeng.smarthome.Model.MyDevices.Cooking.RangeHood;
import com.example.zeyupeng.smarthome.Model.MyDevices.CoolingAndHeating.AC;
import com.example.zeyupeng.smarthome.Model.MyDevices.Lighting.AdjustableLight;
import com.example.zeyupeng.smarthome.Model.MyDevices.Lighting.ColorLight;
import com.example.zeyupeng.smarthome.Model.MyDevices.ProductName;
import com.example.zeyupeng.smarthome.Model.MyDevices.Shading.Curtain;
import com.example.zeyupeng.smarthome.Model.MyHome.AbstractHome;
import com.example.zeyupeng.smarthome.Model.MyHome.Home;
import com.example.zeyupeng.smarthome.Model.MyRooms.AbstractRoom;
import com.example.zeyupeng.smarthome.Model.MyRooms.Room;

import java.util.Map;

/**
 * Created by zeyu peng on 2017-07-21.
 */

public class UploadTool {
    public void uploadData(){
        final DynamoDBMapper dynamoDBMapper = AWSMobileClient.defaultMobileClient().getDynamoDBMapper();

        AmazonClientException lastException = null;
        try {
            Home home=(Home) HomeDataOnHub.getInstance().getHome();
            HomeDataConverter homeDataConverter=new HomeDataConverter();
            HomeData homeData=homeDataConverter.convertToMyHomeData(home);
            dynamoDBMapper.save(homeData);

            RoomDataConverter roomDataConverter = new RoomDataConverter();
            for(Map.Entry<String,AbstractRoom> entryForRooms: RoomDataOnHub.getInstance().getRoomDataMap().entrySet()){
                Room room=(Room) entryForRooms.getValue();
                RoomData roomData=roomDataConverter.converToRoomData(room);
                dynamoDBMapper.save(roomData);
            }

            for(Map.Entry<String,AbstractDevice> entryForDevices: DeviceDataOnHub.getInstance().getDeviceDataMap().entrySet()){
                switch (entryForDevices.getValue().getProductName()){
                    case ProductName.COLOR_LIGHT:
                        ColorLight colorLight = (ColorLight)entryForDevices.getValue();
                        ColorLightDataConverter colorLightDataConverter = new ColorLightDataConverter();
                        ColorLightData colorLightData=colorLightDataConverter.convertToDeviceData(colorLight);
                        dynamoDBMapper.save(colorLightData);
                        break;
                    case ProductName.ADJUSTABLE_LIGHT:
                        AdjustableLight adjustableLight = (AdjustableLight) entryForDevices.getValue();
                        AdjustableLightDataConverter converter = new AdjustableLightDataConverter();
                        AdjustableLightData deviceData=converter.convertToDeviceData(adjustableLight);
                        dynamoDBMapper.save(deviceData);
                        break;
                    case ProductName.NFC_DOOR:
                        NFCDoor nfcDoor = (NFCDoor) entryForDevices.getValue();
                        NFCDoorDataConverter nfcDoorDataConverter = new NFCDoorDataConverter();
                        NFCDoorData nfcDoorData=nfcDoorDataConverter.convertToDeviceData(nfcDoor);
                        dynamoDBMapper.save(nfcDoorData);
                        break;
                    case ProductName.GARAGE_DOOR:
                        GarageDoor garageDoor = (GarageDoor) entryForDevices.getValue();
                        GarageDoorDataConverter garageDoorDataConverter = new GarageDoorDataConverter();
                        GarageDoorData garageDoorData=garageDoorDataConverter.convertToDeviceData(garageDoor);
                        dynamoDBMapper.save(garageDoorData);
                        break;
                    case ProductName.OVEN:
                        Oven oven = (Oven) entryForDevices.getValue();
                        OvenDataConverter ovenDataConverter = new OvenDataConverter();
                        OvenData ovenData=ovenDataConverter.convertToDeviceData(oven);
                        dynamoDBMapper.save(ovenData);
                        break;
                    case ProductName.RANGEHOOD:
                        RangeHood rangeHood = (RangeHood) entryForDevices.getValue();
                        RangeHoodDataConverter rangeHoodDataConverter = new RangeHoodDataConverter();
                        RangeHoodData rangeHoodData=rangeHoodDataConverter.convertToDeviceData(rangeHood);
                        dynamoDBMapper.save(rangeHoodData);
                        break;
                    case ProductName.AC:
                        AC ac = (AC) entryForDevices.getValue();
                        ACDataConverter acDataConverter = new ACDataConverter();
                        ACData acData=acDataConverter.convertToDeviceData(ac);
                        dynamoDBMapper.save(acData);
                        break;
                    case ProductName.CURTAIN:
                        Curtain curtain = (Curtain) entryForDevices.getValue();
                        CurtainDataConverter curtainDataConverter = new CurtainDataConverter();
                        CurtainData curtainData=curtainDataConverter.convertToDeviceData(curtain);
                        dynamoDBMapper.save(curtainData);
                        break;
                }
            }
            PlanDataConverter planDataConverter= new PlanDataConverter();
            PlanData planData=planDataConverter.converToPlanData(PlanDataOnHub.getInstance().getPlanDataMap());
            dynamoDBMapper.save(planData);


        } catch (final AmazonClientException ex) {
            Log.i("dynamoDB..........", "Failed saving item : " + ex.getMessage(), ex);
            lastException = ex;
        }
    }
}
