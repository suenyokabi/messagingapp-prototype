package com.marville001.sendsms;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_phone, et_message;
    private Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_phone = findViewById(R.id.et_phone);
        et_message = findViewById(R.id.et_message);
        btn_send = findViewById(R.id.btn_send);


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){

                    if(checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                        sendSms();
                    }else{
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                    }
                }
                sendSms();
            }
        });

    }

    private void sendSms() {
        String phone = et_phone.getText().toString().trim();
        String message = et_message.getText().toString().trim();

        String[] phones = phone.split(",");

        for (int i=0; i<phone.length();i++){
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phones[i],null,message, null,null);
                Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
            }catch (Exception e){

            }
        }




    }
}