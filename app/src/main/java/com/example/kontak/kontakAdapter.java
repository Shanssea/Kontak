package com.example.kontak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

import java.util.List;

public class kontakAdapter extends ArrayAdapter {
    private static class ViewHolder{
        TextView nama; TextView nohp;
    }

    public kontakAdapter(@NonNull Context context, int resource, @NonNull List<kontak> objects) {
        super(context, resource, objects);
    }
    public View getView (int position, View ConvertView,
                         ViewGroup parent){
        kontak dtkontak= (kontak) getItem(position);
        ViewHolder viewKontak;
        if(ConvertView==null){
            viewKontak = new ViewHolder();
            ConvertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.item_user,parent,false);
            viewKontak.nama = (TextView) ConvertView.findViewById(R.id.tNama);
//            viewKontak.nohp = (TextView) ConvertView.findViewById(R.id.tnohp);

            ConvertView.setTag(viewKontak);

        }
        else{
            viewKontak = (ViewHolder) ConvertView.getTag();
        }
        viewKontak.nama.setText(dtkontak.getNama());
//        viewKontak.nohp.setText(dtkontak.getNohp());
        return ConvertView;
    }

    public void getInfo (int position){
        kontak dtkontak = (kontak) getItem(position);
        String kontakInfo = dtkontak.toString();
        String kontakId = kontakInfo.substring(0, kontakInfo .indexOf(" "));
    }
}
