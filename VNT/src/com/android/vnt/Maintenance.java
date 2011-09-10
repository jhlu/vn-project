package com.android.vnt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Maintenance  extends Activity {
    /** Called when the activity is first created. */
	TextView speedTextView;
	JNIReadCanBus jniReadCanBus;

	static{
		System.loadLibrary("jniReadCanBus");
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintenance);   
        settingsVarry();
        settingsButtonFun();    
    }
    
    private void settingsVarry(){
    	jniReadCanBus=new JNIReadCanBus();
    	speedTextView=(TextView)findViewById(R.id.maintenanceSpeedInfoTextView);
    	speedTextView.setText(jniReadCanBus.readCanBusSpeed());	
    }
    
    private void sendToServer(){
		// TODO,send the GPS message to Server
    }
    
    private void settingsButtonFun(){
		Button sendButton = (Button) findViewById(R.id.maintenanceSendButton);
		sendButton.setOnClickListener(sendListener);
    	Button returnButton =(Button) findViewById(R.id.maintenanceReturnButton);
    	returnButton.setOnClickListener(returnListener); 	
    }
    
    private OnClickListener sendListener = new OnClickListener(){
    	public void onClick(View v){
			sendToServer();
			Toast.makeText(Maintenance.this,
					R.string.maintenanceSend_nosd_string, Toast.LENGTH_LONG)
					.show();
    	}
    };
    
    private OnClickListener returnListener = new OnClickListener(){
    	public void onClick(View v){
    		finish();
    	}
    };
}