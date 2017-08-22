package com.example.zeyupeng.smarthome.NFC;

/**
 * Created by zeyu peng on 2017-08-07.
 */

public class CardPassword {
    private static volatile CardPassword ourInstance;

    public static CardPassword getInstance() {
        if (ourInstance == null) {
            synchronized (CardPassword.class) {
                if (ourInstance == null) {
                    ourInstance = new CardPassword();
                }
            }
        }
        return ourInstance;
    }



    private String password;

    public String getPassword() {
        if(password!=null){
            return password;
        }else {
            return "";
        }

    }

    public void setPassword(String password) {

        this.password = password;
    }
}
