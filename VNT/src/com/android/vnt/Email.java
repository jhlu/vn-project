package com.android.vnt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Email  extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);   
        settingsVarry();
        settingsButtonFun();
    }
    private void settingsVarry(){
    	
    }
    private void settingsButtonFun(){
    	Button returnButton =(Button) findViewById(R.id.emailReturnButton);
    	returnButton.setOnClickListener(returnListener); 	
    }
    private OnClickListener returnListener = new OnClickListener(){
    	public void onClick(View v){
    		finish();
    	}
    };
}