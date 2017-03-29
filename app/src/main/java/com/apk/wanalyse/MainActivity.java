package com.apk.wanalyse;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static WifiManager wifiManager;
    static TextView strengthValue;
    static Button enterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiManager = (WifiManager)this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);

        strengthValue = (TextView) findViewById(R.id.signalValue_textView);
        enterButton = (Button) findViewById(R.id.enter_button);
    }
    public void onReceive(WifiManager wifiManager) {
        int numberOfLevels=5;
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        //int level=WifiManager.calculateSignalLevel(wifiInfo.getRssi(), numberOfLevels);
        strengthValue.setText(Integer.toString(wifiInfo.getRssi())+" dbm");
    }

    public void onClickEnter(View view){
        onReceive(wifiManager);
    }
}