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

import java.util.ArrayList;
import java.util.Vector;

import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity {
    static WifiManager wifiManager;
    static TextView strengthValue;
    static Button enterButton;
    ArrayList<String> results = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);

        strengthValue = (TextView) findViewById(R.id.signalValue_textView);
        enterButton = (Button) findViewById(R.id.enter_button);

        results.add("0");
        results.add("0");
        results.add("0");

    }

    public void onReceive(WifiManager wifiManager) {
        int numberOfLevels = 5;
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        //int level=WifiManager.calculateSignalLevel(wifiInfo.getRssi(), numberOfLevels);
        strengthValue.setText(Integer.toString(wifiInfo.getRssi()) + " dbm");
    }

    public void onClickEnter(View view) {
        onReceive(wifiManager);
        String data = strengthValue.getText().toString();

        results.add(data);
        TextView history1 = (TextView) findViewById(R.id.history1);
        TextView history2 = (TextView) findViewById(R.id.history2);
        TextView history3 = (TextView) findViewById(R.id.history3);

        String listRepresentation = "";
        for (String result: results)
        {
            if ("".equals(listRepresentation))
                listRepresentation = result;
            else
                listRepresentation = ", "+result;
        }

        history1.setText(results.get(results.size()-1));
        history2.setText(results.get(results.size()-2));
        history3.setText(results.get(results.size()-3));


    }

}