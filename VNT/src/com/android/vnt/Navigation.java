package com.android.vnt;

import java.util.List;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

//import com.message.ConnectionConfigFile;

public class Navigation extends MapActivity {
	/** Called when the activity is first created. */

	public double dLat = 39.908354;
	public double dLng = 116.394997;

	MapView navigationMap;
	MapController navigationCtrlMap;
	Button mapInButton;
	Button mapOutButton;
	Button mapSwitchOnButton;
	Button mapSwitchOffButton;

	TextView myActiveMQStringTextView;
	TextView myLocationText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navigation);
		Log.i("lujianhua", "00000");
		settingsVarry();
		Log.i("lujianhua", "111111");
		settingsButtonFun();
		Log.i("lujianhua", "222222");
		mapCtrlFun();
		Log.i("lujianhua", "333333");
		receiverFromActivemq();
		Log.i("lujianhua", "444444");
	}

	public void showStringFormActiveMQFun(String textFromActive) {
		Log.i("showStringFormActiveMQFun_befor", "00000");
		myActiveMQStringTextView.setText(textFromActive);
		Log.i("showStringFormActiveMQFun_after", "11111");
	}

	private void receiverFromActivemq() {

	}

	private void settingsVarry() {
		myActiveMQStringTextView = (TextView) findViewById(R.id.navigationMapLocationTextView);
		myLocationText = (TextView) findViewById(R.id.navigationMapLocationTextView);
	}

	private void settingsButtonFun() {
		Button returnButton = (Button) findViewById(R.id.navigationReturnButton);
		returnButton.setOnClickListener(returnListener);
		Button weatherSubmitButton = (Button) findViewById(R.id.navigationWeatherSubmitButton);
		weatherSubmitButton.setOnClickListener(weatherSubmitListener);
	}

	protected boolean isRouteDisplayed() {
		return false;
	}

	private OnClickListener returnListener = new OnClickListener() {
		public void onClick(View v) {
			finish();
		}
	};

	private OnClickListener weatherSubmitListener = new OnClickListener() {
		public void onClick(View v) {
			try {
				TextView weatherTextView = (TextView) findViewById(R.id.navigationWeatherTextView);
				weatherTextView.setText("当前天气" + "   ");
				TextView WeatherTemperatureTextView = (TextView) findViewById(R.id.navigationTemperatureTextView);
				WeatherTemperatureTextView.setText("温度：   " + "湿度：  ");
				TextView WeatherHumidityTextView = (TextView) findViewById(R.id.navigationHumidityTextView);
				WeatherHumidityTextView.setText("风向：   " + "风速：    ");
			} catch (Exception e) {
				Log.e("weatherSubmitListener", e.toString());
			}
		}
	};

	private void mapCtrlFun() {
		navigationMap = (MapView) findViewById(R.id.myMapView);
		List<Overlay> overlays = navigationMap.getOverlays();
		MyLocationOverlay myLocation = new MyLocationOverlay(this,
				navigationMap);
		myLocation.enableMyLocation();
		overlays.add(myLocation);
		navigationCtrlMap = navigationMap.getController();
		mapInButton = (Button) findViewById(R.id.navigationMapInButton);
		mapOutButton = (Button) findViewById(R.id.navigationMapOutButton);
		mapSwitchOnButton = (Button) findViewById(R.id.navigationMapSwitchOnButton);
		mapSwitchOffButton = (Button) findViewById(R.id.navigationMapSwitchOffButton);

		OnClickListener mapInOutSwitchlistener = new OnClickListener() {
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.navigationMapInButton:
					navigationCtrlMap.zoomIn();
					break;
				case R.id.navigationMapOutButton:
					navigationCtrlMap.zoomOut();
					break;
				case R.id.navigationMapSwitchOnButton:
					navigationMap.setSatellite(true);
					break;
				case R.id.navigationMapSwitchOffButton:
					navigationMap.setSatellite(false);
					break;
				default:
					break;
				}
			}
		};
		mapInButton.setOnClickListener(mapInOutSwitchlistener);
		mapOutButton.setOnClickListener(mapInOutSwitchlistener);
		mapSwitchOnButton.setOnClickListener(mapInOutSwitchlistener);
		mapSwitchOffButton.setOnClickListener(mapInOutSwitchlistener);

		LocationManager locationManager;
		String context = Context.LOCATION_SERVICE;
		locationManager = (LocationManager) getSystemService(context);

		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		String provider = locationManager.getBestProvider(criteria, true);

		Location location = locationManager.getLastKnownLocation(provider);
		updateWithNewLocation(location);
		locationManager.requestLocationUpdates(provider, 2000, 10,locationListener);

		goDefaultLocation();
	}

	private void goDefaultLocation() {
		GeoPoint p = new GeoPoint((int) (dLat * 1E6), (int) (dLng * 1E6));
		navigationMap.displayZoomControls(true);
		navigationCtrlMap.animateTo(p);
		navigationCtrlMap.setZoom(10);
	}

	private final LocationListener locationListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			updateWithNewLocation(location);
		}

		public void onProviderDisabled(String provider) {
			updateWithNewLocation(null);
		}

		public void onProviderEnabled(String provider) {
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	};

	private void updateWithNewLocation(Location location) {
		/*		
 		String latLongString;
		if (location != null) {
			double lat = location.getLatitude();
			double lng = location.getLongitude();
			latLongString = "经度:" + lat + "纬度:" + lng;
			navigationCtrlMap.animateTo(new GeoPoint((int) (lat * 1E6),(int) (lng * 1E6)));
		} else {
			latLongString = "无法获得地里";
		}
		myLocationText.setText("当前位置：" + latLongString);
		*/
	}
}