package com.example.kontak;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SingleActivity extends AppCompatActivity {

    private TextView txtNama, txtNomor, txtAlamat;
    private String nama, hp, alamat;

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
    }
}
