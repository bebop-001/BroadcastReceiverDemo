/*
 *  Copyright 2018 Steven Smith kana-tutor.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *  either express or implied.
 *
 *  See the License for the specific language governing permissions
 *  and limitations under the License.
 */
package com.kana_tutor.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class BroadcastReceiverDemo extends AppCompatActivity {
    private static final String TAG = BroadcastReceiverDemo.class.getSimpleName();


    public class InnerBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String testString = intent.getStringExtra("testString");
            String bogusString = intent.getStringExtra("bogusString");
            Toast.makeText(context, "Inner class intent received", Toast.LENGTH_LONG)
                 .show();
            Log.d(TAG, "Inner class intent received:" + intent.toString());
        }
    }


    private static final String customIntentName = "com.kana_tutor.CUSTOM_INTENT";
    // broadcast a custom intent.
    public void broadcastIntent(View view){
        Intent intent = new Intent();
        intent.setAction(customIntentName);
        intent.putExtra("testString", "broadcast receiver test");
        sendBroadcast(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        class IBC extends BroadcastReceiver {
            @Override
            public void onReceive(Context context, Intent intent) {
                String testString = intent.getStringExtra("testString");
                String bogusString = intent.getStringExtra("bogusString");
                Toast.makeText(context, "Inner class intent received", Toast.LENGTH_LONG)
                     .show();
                Log.d(TAG, "OnCreate inner class intent received:" + intent.toString());
            }
        }


        setContentView(R.layout.broadcast_receiver_demo);

        BroadcastReceiver outerBR = new CustomBroadcastReceiver();
        BroadcastReceiver innerBR = new InnerBroadcastReceiver();
        BroadcastReceiver innerInnerBR = new IBC();

        IntentFilter filter = new IntentFilter(customIntentName);
        filter.addAction(customIntentName);

        this.registerReceiver(outerBR, filter);
        this.registerReceiver(innerBR, filter);
        this.registerReceiver(innerInnerBR, filter);

        findViewById(R.id.do_broadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                broadcastIntent(v);
            }
        });
    }
}
