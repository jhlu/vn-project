package com.android.vnt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Internet  extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internet);   
        settingsVarry();
        settingsButtonFun();
    }
    private void settingsVarry(){
    	
    }
    private void settingsButtonFun(){
    	Button returnButton =(Button) findViewById(R.id.internetReturnButton);
    	returnButton.setOnClickListener(returnListener); 	
    }
    private OnClickListener returnListener = new OnClickListener(){
    	public void onClick(View v){
    		finish();
    	}
    };
}