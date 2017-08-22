package com.example.zeyupeng.smarthome.PubNub.PushNotification;

/**
 * Created by zeyu peng on 2017-07-20.
 */

public class NotificationPayload {
    private Data data;

    public NotificationPayload() {
        data = new Data();
    }

    public Data getData() {

        return data;
    }

    public void setData(Data data) {

        this.data = data;
    }

    public class Data{
        private String content;
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }



        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
