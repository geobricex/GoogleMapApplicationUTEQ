package com.example.googlemapapplicationuteq;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class PopUpActivity implements GoogleMap.InfoWindowAdapter, Callback {
    Context context;
    ImageView imgLogo;
    TextView lblName;
    TextView txtAutoridad;
    TextView txtDireccion;
    TextView txtContact;
    TextView txtLatitude;
    TextView txtLongitude;
    Marker globalMarker;

    public PopUpActivity(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        Log.i("Logs", "getInfoWindow " + marker.getTitle());
        View view = LayoutInflater.from(context).inflate(R.layout.activity_pop_up, null);

        initialize(view);
        loadData(marker);

        return view;
    }

    private void initialize(View view) {
        imgLogo = view.findViewById(R.id.imgLogo);
        lblName = view.findViewById(R.id.lblName);
        txtAutoridad = view.findViewById(R.id.txtAutoridad);
        txtDireccion = view.findViewById(R.id.txtDireccion);
        txtContact = view.findViewById(R.id.txtContacto);
        txtLatitude = view.findViewById(R.id.txtLatitude);
        txtLongitude = view.findViewById(R.id.txtLongitude);
    }

    private void loadData(Marker marker) {
        globalMarker = marker;
        try {
            JSONObject json = new JSONObject(marker.getTitle());
            Log.i("Logs", marker.getTitle());

            lblName.setText(json.get("name").toString());
            txtAutoridad.setText(json.get("authority").toString());
            txtDireccion.setText(json.get("direction").toString());
            txtContact.setText(json.get("contact").toString());
            txtLatitude.setText("Lat: "+json.get("latitude").toString());
            txtLongitude.setText("Long: "+json.get("longitude").toString());

            Picasso.get().load(json.get("logo").toString()).resize(200, 200).centerCrop().into(imgLogo, this);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View getInfoContents(@NonNull Marker marker) {
        return null;
    }

    @Override
    public void onSuccess() {
        if (globalMarker != null && globalMarker.isInfoWindowShown()) {
            globalMarker.hideInfoWindow();
            globalMarker.showInfoWindow();
        }
    }

    @Override
    public void onError(Exception e) {

    }
}