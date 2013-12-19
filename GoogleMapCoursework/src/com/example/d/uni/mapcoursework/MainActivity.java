package com.example.d.uni.mapcoursework;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity implements OnItemSelectedListener,
		OnClickListener {

	// variable for the google map.
	GoogleMap map;

	// variables for the Spinner,editText and Button used in the main layout.
	private Spinner spinner;
	private EditText location1, location2;
	private Button User;

	// variable for the location manager
	LocationManager lm;

	// string values that are parsed.
	private String Loc1;
	private String Loc2;

	// the various latitudes and longitudes of the commonwealth hosts.

	static final LatLng GLASGOW = new LatLng(55.8580, -4.2590);
	static final LatLng DELHI = new LatLng(28.61, 77.23);
	static final LatLng MELBOURNE = new LatLng(-37.813611, 144.963056);
	static final LatLng MANCHESTER = new LatLng(53.466667, -2.233333);
	static final LatLng KUALALUMPUR = new LatLng(3.1475, 101.693333);
	static final LatLng VICTORIA = new LatLng(48.428611, -123.365556);
	static final LatLng AUCKLAND = new LatLng(-36.840417, 174.739869);
	static final LatLng BRISBANE = new LatLng(-27.467917, 153.027778);
	static final LatLng EDMONTON = new LatLng(53.533333, -113.5);
	static final LatLng CHRISTCHURCH = new LatLng(-43.53, 172.620278);
	static final LatLng EDINBURGH = new LatLng(55.939, -3.172);

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// creates an instance of the location manager to access the location
		// service.
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// creates the map fragment from the main activity xml.
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		// settings for the location on the map.
		map.setMyLocationEnabled(true);
		map.getUiSettings().setZoomControlsEnabled(true);
		map.getUiSettings().setMyLocationButtonEnabled(true);

		// used to create both editTexts that are required and also the button.
		location1 = (EditText) findViewById(R.id.editText1);
		location2 = (EditText) findViewById(R.id.editText2);
		User = (Button) findViewById(R.id.button);

		// sets the on click listener.
		User.setOnClickListener(this);

		// method for the location listener.
		LocationListener ll = new LocationListener() {

			@Override
			public void onProviderDisabled(String provider) {

			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub

			}

			@Override
			// method for when the location on the map is changed.
			public void onLocationChanged(Location location) {

				// adds a new marker with a position,title,snippet and custom
				// icon
				map.addMarker(new MarkerOptions()
						.position(
								new LatLng(location.getLatitude(), location
										.getLongitude()))
						.title("Location")
						.snippet("YOU ARE HERE")
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.pic)));

				// used to animate the camera to move to the new location when
				// it is changed.
				map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
						location.getLatitude(), location.getLongitude()), 10));

			}

		};
		// location manager requesting updates from the gps provider for the
		// maps.
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);

		// set the current map type.
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

		// move the map to a certain latitude and longitude with a zoom on the
		// map.
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(55.8580,
				-4.2590), 13));

		// adds a marker for Glasgow with the position,custom icon and snippet.
		map.addMarker(new MarkerOptions()
				.position(new LatLng(55.8580, -4.2590))
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.glasgow))
				.snippet("Commenwealth Games 2014").title("GLASGOW"));

		// adds a marker for the SECC with the position,custom icon and snippet.
		map.addMarker(new MarkerOptions()
				.position(new LatLng(55.8607, -4.2871))
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.boxing))
				.snippet(
						"Boxing,Gymnastics,Judo,Netball,Wrestling,Weightlifting ")
				.title("SECC"));

		// adds a marker for the Barry Buddon Shooting Centre with the
		// position,custom icon and snippet.
		map.addMarker(new MarkerOptions()
				.position(new LatLng(56.499, -2.7543))
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.shooting))
				.snippet("Shooting").title("BARRY BUDDON SHOOTING CENTRE"));

		// adds a marker for Parkhead stadium with the position,custom icon and
		// snippet.
		map.addMarker(new MarkerOptions()
				.position(new LatLng(55.8497, -4.2055))
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.july))
				.snippet("Opening Ceremony").title("PARKHEAD STADIUM"));

		// adds a marker for Cathkin Braes Mountain Bike Trails with the
		// position,custom icon and snippet.
		map.addMarker(new MarkerOptions()
				.position(new LatLng(55.79434, -4.2193))
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.mountain))
				.snippet("Mountain Biking")
				.title("CATHKIN BRAES MOUNTAIN BIKE TRAILS"));

		// adds a marker for the Velodrome with the position,custom icon and
		// snippet.
		map.addMarker(new MarkerOptions().position(new LatLng(55.847, -4.2076))
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.cycling))
				.snippet("Badminton,Cycling").title("VELODROME"));

		// adds a marker for the Glasgow National Hockey Centre with the
		// position,custom icon and snippet.
		map.addMarker(new MarkerOptions().position(new LatLng(55.8447, -4.236))
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.hockey))
				.snippet("Hockey").title("GLASGOW NATIONAL HOCKEY CENTRE"));

		// adds a marker for Hampden Park with the position,custom icon and
		// snippet.
		map.addMarker(new MarkerOptions()
				.position(new LatLng(55.8255, -4.2520))
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.athletics))
				.snippet("Atheltics").title("HAMPDEN PARK"));

		// adds a marker for Ibrox Stadium with the position,custom icon and
		// snippet.
		map.addMarker(new MarkerOptions().position(new LatLng(55.853, -4.309))
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.rugby))
				.snippet("Rugby 7's").title("IBROX STADIUM"));

		// adds a marker for the Kelvingrove Lawn Bowls Centre with the
		// position,custom icon and snippet.
		map.addMarker(new MarkerOptions().position(new LatLng(55.867, -4.2871))
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.bowls))
				.snippet("Bowls").title("KELVINGROVE LAWN BOWLS CENTRE"));

		// adds a marker for Scotstoun with the position,custom icon and
		// snippet.
		map.addMarker(new MarkerOptions()
				.position(new LatLng(55.8813, -4.3405))
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.squash))
				.snippet("Squash,Table Tennis").title("SCOTSTOUN"));

		// adds a marker for Tollcross International Swimming Centre with the
		// position,custom icon and snippet.
		map.addMarker(new MarkerOptions()
				.position(new LatLng(55.845, -4.177))
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.swimming))
				.snippet("Swimming")
				.title("TOLLCROSS INTERNATIONAL SWIMMING CENTRE"));

		// adds a marker for Strathclyde Country Park with the position,custom
		// icon and snippet.
		map.addMarker(new MarkerOptions()
				.position(new LatLng(55.7971971, -4.0342997))
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.triathlon))
				.snippet("Triathlon").title("STRATHCLYDE COUNTRY PARK"));

		// adds a marker for the Royal Commonwealth Pool with the
		// position,custom icon and snippet.
		map.addMarker(new MarkerOptions().position(new LatLng(55.939, -3.172))
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.diving))
				.snippet("Diving").title("ROYAL COMMENWEALTH POOL,EDINBURGH"));

		// finds the spinner in the main view.
		spinner = (Spinner) findViewById(R.id.spinner1);

		// creates an array adapter for the spinner.
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.cities_arrays,
				android.R.layout.simple_spinner_item);

		// sets a drop down view for the spinner.
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// sets the adapter for the spinner.
		spinner.setAdapter(adapter);

		// sets the selected item listener.
		spinner.setOnItemSelectedListener(this);

		// finds the radio group by its id in the view.
		RadioGroup rgViews = (RadioGroup) findViewById(R.id.rg_views);

		// creates a method for the onCheckedChangeListener.
		rgViews.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			// method for checking which button is checked in the radio group
			// and performs the action required.
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.rb_normal) {
					map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				} else if (checkedId == R.id.rb_satellite) {
					map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
				} else if (checkedId == R.id.rb_terrain) {
					map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
				}
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	// method for checking which item is selected in the spinner and finding it
	// in the array.
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {

		// sets the map over Glasgow.
		map.moveCamera(CameraUpdateFactory.newLatLng(GLASGOW));

		// if spinner is set on Delhi the required information is taken in.
		if (spinner.getSelectedItem().toString().equals("DELHI"))

		{
			map.addMarker(new MarkerOptions()
					.position(new LatLng(28.61, 77.23))
					.snippet("Scottish Medals Won Gold:9,Silver:10,Bronze:7")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.india))
					.title("DELHI(2010 Commonwealth Games)"));
			map.moveCamera(CameraUpdateFactory.newLatLng(DELHI));

		}

		// if spinner is set on Melbourne the required information is taken in.
		else if (spinner.getSelectedItem().toString().equals("MELBOURNE")) {

			map.addMarker(new MarkerOptions()
					.position(new LatLng(-37.813611, 144.963056))
					.snippet("Scottish Medals Won Gold:11,Silver:7,Bronze:11")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.australia))
					.title("MELBOURNE(2006 Commonwealth Games)"));
			map.moveCamera(CameraUpdateFactory.newLatLng(MELBOURNE));
		}

		// if spinner is set on Manchester the required information is taken in.
		else if (spinner.getSelectedItem().toString().equals("MANCHESTER")) {
			map.addMarker(new MarkerOptions()
					.position(new LatLng(53.466667, -2.233333))
					.snippet("Scottish Medals Won Gold:6,Silver:8,Bronze:15")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.england))
					.title("MANCHESTER(2002 Commonwealth Games)"));
			map.moveCamera(CameraUpdateFactory.newLatLng(MANCHESTER));
		}

		// if spinner is set on Kualalumpur the required information is taken
		// in.
		else if (spinner.getSelectedItem().toString().equals("KUALALUMPUR")) {
			map.addMarker(new MarkerOptions()
					.position(new LatLng(3.1475, 101.693333))
					.snippet("Scottish Medals Won Gold:3,Silver:2,Bronze:7")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.malaysia))
					.title("KUALALUMPUR(1998 Commonwealth Games)"));
			map.moveCamera(CameraUpdateFactory.newLatLng(KUALALUMPUR));
		}

		// if spinner is set on Victoria the required information is taken in.
		else if (spinner.getSelectedItem().toString().equals("VICTORIA")) {
			map.addMarker(new MarkerOptions()
					.position(new LatLng(48.428611, -123.365556))
					.snippet("Scottish Medals Won Gold:6,Silver:3,Bronze:11")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.australia))
					.title("VICTORIA(1994 Commonwealth Games)"));
			map.moveCamera(CameraUpdateFactory.newLatLng(VICTORIA));
		}

		// if spinner is set on Auckland the required information is taken in.
		else if (spinner.getSelectedItem().toString().equals("AUCKLAND")) {
			map.addMarker(new MarkerOptions()
					.position(new LatLng(-36.840417, 174.739869))
					.snippet("Scottish Medals Won Gold:5,Silver:10,Bronze:10")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.newzealand))
					.title("AUCKLAND(1990 Commonwealth Games)"));
			map.moveCamera(CameraUpdateFactory.newLatLng(AUCKLAND));
		}

		// if spinner is set on Brisbane the required information is taken in.
		else if (spinner.getSelectedItem().toString().equals("BRISBANE")) {
			map.addMarker(new MarkerOptions()
					.position(new LatLng(-27.467917, 153.027778))
					.snippet("Scottish Medals Won Gold:8,Silver:6,Bronze:12")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.australia))
					.title("BRISBANE(1982 Commonwealth Games)"));
			map.moveCamera(CameraUpdateFactory.newLatLng(BRISBANE));
		}

		// if spinner is set on Edmonton the required information is taken in.
		else if (spinner.getSelectedItem().toString().equals("EDMONTON")) {
			map.addMarker(new MarkerOptions()
					.position(new LatLng(53.533333, -113.5))
					.snippet("Scottish Medals Won Gold:3,Silver:6,Bronze:5")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.canada))
					.title("EDMONTON(1978 Commonwealth Games"));
			map.moveCamera(CameraUpdateFactory.newLatLng(EDMONTON));
		}

		// if spinner is set on Christchurch the required information is taken
		// in.
		else if (spinner.getSelectedItem().toString().equals("CHRISTCHURCH")) {
			map.addMarker(new MarkerOptions()
					.position(new LatLng(-43.53, 172.620278))
					.snippet("Scottish Medals Won Gold:3,Silver:5,Bronze:11")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.newzealand))
					.title("CHRISTCHURCH(1974 Commonwealth Games)"));
			map.moveCamera(CameraUpdateFactory.newLatLng(CHRISTCHURCH));
		}

		// if spinner is set on Edinburgh the required information is taken in.
		else if (spinner.getSelectedItem().toString().equals("EDINBURGH")) {
			map.addMarker(new MarkerOptions()
					.position(new LatLng(55.939, -3.172))
					.snippet("Scottish Medals Won Gold:3,Silver:12,Bronze:18")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.scotland))
					.title("EDINBURGH(1986 Commonwealth Games)"));
			map.moveCamera(CameraUpdateFactory.newLatLng(EDINBURGH));
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	// the on click method for when the button is pressed to update the
	// location.
	public void onClick(View v) {
		// gets the text information for location1 and location2.
		Loc1 = (location1.getText().toString());
		Loc2 = (location2.getText().toString());

		// parses the strings to double values.
		double lat = Double.parseDouble(Loc1);
		double lng = Double.parseDouble(Loc2);

		// what happens when the button is pressed.
		if (v == User) {
			// creates a new position with latitude and longitude.
			LatLng pos = new LatLng(lat, lng);

			// adds a marker to the map with the relevant information.
			map.addMarker(new MarkerOptions().title("Location")
					.snippet("YOU ARE HERE")
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.pic))
					.position(pos));
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 10));

			// creates a toast message showing the current latitude and
			// longitude.
			Toast.makeText(this, +lat + "," + lng, Toast.LENGTH_LONG).show();

		}

	}

}
