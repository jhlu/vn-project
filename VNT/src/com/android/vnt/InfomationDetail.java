package com.android.vnt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class InfomationDetail  extends Activity {
    /** Called when the activity is first created. */
	TextView infoDetailTitle;
	TextView infoDetailContent;
	TextView infoDetailTime;
	Bundle infoBundle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infomationdetail); 
        settingsVarry();
        settingsButtonFun();
    }
    private void settingsVarry(){
        infoBundle= this.getIntent().getExtras();
    	infoDetailTitle=(TextView)findViewById(R.id.infomationDetailTitleTextView);
    	infoDetailContent=(TextView)findViewById(R.id.infomationDetailContentTextView);
    	infoDetailTime=(TextView)findViewById(R.id.infomationDetailTimeTextView);
    	infoDetailTitle.setText(infoBundle.getString("KEY_TITLE"));
    	infoDetailContent.setText(infoBundle.getString("KEY_CONTENT"));
    	infoDetailTime.setText(infoBundle.getString("KEY_TIME"));
    }
    private void settingsButtonFun(){
    	Button returnButton =(Button) findViewById(R.id.settingsReturnButton);
    	returnButton.setOnClickListener(returnListener); 	
    }
    private OnClickListener returnListener = new OnClickListener(){
    	public void onClick(View v){
    		finish();
    	}
    };
}