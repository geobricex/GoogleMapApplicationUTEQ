package com.example.googlemapapplicationuteq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.googlemapapplicationuteq.Models.University;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    GoogleMap googleMap;
    FloatingActionsMenu FloatMenu;
    FloatingActionButton predeterminado;
    FloatingActionButton satelital;
    FloatingActionButton hibrido;
    String jsonFileString = "";
    List<University> universities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        OnClickListener();
        loadData();
        supportMapFragment();

    }

    private void initialize() {
        FloatMenu = findViewById(R.id.grpMenu);
        predeterminado = findViewById(R.id.fgbtnNormal);
        satelital = findViewById(R.id.fgbtnSatellite);
        hibrido = findViewById(R.id.fgbtnHybrid);
    }

    private void OnClickListener() {
        predeterminado.setOnClickListener(v -> {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        });
        satelital.setOnClickListener(v -> {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        });
        hibrido.setOnClickListener(v -> {
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        });
    }

    private void loadData() {
        jsonFileString = loadJSONFromAsset("uteqinformation.json");
//        Log.i("Logs", jsonFileString);
        Gson gson = new Gson();

        Type type = new TypeToken<List<University>>() {
        }.getType();

        universities = gson.fromJson(jsonFileString, type);
    }

    private void supportMapFragment() {
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.frgMapGoogle);
        assert supportMapFragment != null;
        supportMapFragment.getMapAsync(this);
    }

    private void positiongoogleMaps() {
        for (int u = 0; u < universities.size(); u++) {
            Log.i("Logs", "Item " + u + "\n" + universities.get(u).toString());
            pointmarkers(new LatLng(universities.get(u).getLatitude(), universities.get(u).getLongitude()), universities.get(u).toString());
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap paramgoogleMap) {
        googleMap = paramgoogleMap;

        googleMap.setInfoWindowAdapter(new PopUpActivity(MainActivity.this));
        googleMap.setOnMarkerClickListener(this);

        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(-1.0436855, -79.4810402)).zoom(13).bearing(0).tilt(0).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.animateCamera(cameraUpdate);
        positiongoogleMaps();
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        marker.showInfoWindow();
        return false;
    }

    private void pointmarkers(LatLng points, String data) {
        MarkerOptions opMark = new MarkerOptions().position(points).draggable(true).title(data);
        Marker marker = googleMap.addMarker(opMark);
        marker.setTag(jsonFileString);
    }

    public String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = this.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}