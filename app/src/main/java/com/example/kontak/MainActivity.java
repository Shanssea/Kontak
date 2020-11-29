package com.example.kontak;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lv;
    private ImageView add;
    private kontakAdapter kAdapter;
    private SQLiteDatabase dbku;
    private SQLiteOpenHelper dbopen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.listView);
        add = findViewById(R.id.add);
        add.setOnClickListener(operasi);
        lv.setOnItemClickListener(this);

//        ArrayList<kontak> listKontak = new ArrayList<kontak>();
        ArrayList<kontak> listKontak = new ArrayList<>();
        kAdapter = new kontakAdapter(this,0,listKontak);
        lv.setAdapter(kAdapter);

        dbopen = new SQLiteOpenHelper(this,"kontak.db",null,1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
            }
            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            }
        };

        dbku = dbopen.getWritableDatabase();
        dbku.execSQL("Create table if not exists kontak(nama TEXT, nohp TEXT);");
        ambildata();
    }

    View.OnClickListener operasi= new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.add:tambah_data();
                break;
            }
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"ok", Toast.LENGTH_SHORT).show();
    }

    private void add_item(String nm, String hp){
        ContentValues datanya = new ContentValues();
        datanya.put("nama",nm);
        datanya.put("nohp",hp);
        dbku.insert("kontak",null,datanya);
        kontak newKontak = new kontak(nm,hp);
        kAdapter.add(newKontak);
    }

    private void tambah_data(){
        AlertDialog.Builder buat = new AlertDialog.Builder(this);
        buat.setTitle("Tambah Kontak");

        View vAdd = LayoutInflater.from(this).inflate(R.layout.add_kontak,null);
        final EditText nm = vAdd.findViewById(R.id.nm);
        final EditText hp = vAdd.findViewById(R.id.hp);

        buat.setView(vAdd);

        buat.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                add_item(nm.getText().toString(), hp.getText().toString());
                Toast.makeText(getBaseContext(),"Data Tersimpan", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        buat.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        buat.show();
    }

    private void insertKontak(String nm, String hp){
        kontak newKontak = new kontak(nm, hp);
        kAdapter.add(newKontak);
    }

    private void ambildata(){
        Cursor cur = dbku.rawQuery("select * from kontak",null);
        Toast.makeText(this,"Terdapat sejumlah " + cur.getCount(),
                Toast.LENGTH_LONG).show();
        int i=0;
        if (cur.getCount() > 0){
            cur.moveToFirst();
        }
        while (i<cur.getCount()){
            insertKontak(cur.getString(cur.getColumnIndex("nama")),
                    cur.getString(cur.getColumnIndex("nohp")));
            cur.moveToNext();
            i++;
        }
    }

//    private void delete_kontak(long id){
//        String string =String.valueOf(id);
//        dbku.execSQL("DELETE FROM kontak WHERE _id = '" + string + "'");
//    }
//
//    private void hapus_data(){
//        AlertDialog.Builder buat = new AlertDialog.Builder(this);
//        buat.setTitle("Tambah Kontak");
//
//        View vDel = LayoutInflater.from(this).inflate(R.layout.delete_kontak,null);
//
//        buat.setView(vDel);
//
//        buat.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String id = kAdapter.getInfo(lv.getItemAtPosition());
//                delete_kontak(id);
//                Toast.makeText(getBaseContext(),"Data Tersimpan", Toast.LENGTH_LONG).show();
//                dialog.dismiss();
//            }
//        });
//        buat.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//
//        buat.show();
//    }
}