package com.example.kontak;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class SingleActivity extends AppCompatActivity implements OnMapReadyCallback {

    private TextView txtNama, txtNomor, txtAlamat;
    private String nama, hp, alamat;

    private LocationManager lm;
    private LocationListener ll;
    private GoogleMap mMap;

    private double latHere, lngHere, lat, lng;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_activity);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            nama = extras.getString("Nama");
            hp = extras.getString("Hp");
            alamat = extras.getString("Alamat");
        }

        txtNama = findViewById(R.id.txtNama);
        txtNomor = findViewById(R.id.txtNomor);
        txtAlamat = findViewById(R.id.txtAlamat);

        txtNama.setText(nama);
        txtNomor.setText(hp);
        txtAlamat.setText(alamat);

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        ll = new lokasiListener();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 200, ll);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Geocoder g = new Geocoder(getBaseContext());

        try {
            List<Address> daftar = g.getFromLocationName(alamat,1);
            Address address = daftar.get(0);

//            String nemuAlamat = address.getAddressLine(0);
            lat = address.getLatitude();
            lng = address.getLongitude();

        } catch (IOException e) {
            e.printStackTrace();
        }

        LatLng kontakLoc = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(kontakLoc).title("Marker in contact's location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kontakLoc, 10));
    }

    private void cari(){

    }

    private class lokasiListener implements LocationListener{
        @Override
        public void onLocationChanged(@NonNull Location location) {
            latHere = location.getLatitude();
            lngHere = location.getLongitude();
            Toast.makeText(getBaseContext(),"GPS capture", Toast.LENGTH_LONG).show();
        }


        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }
    }
}
