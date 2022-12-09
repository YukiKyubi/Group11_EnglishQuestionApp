package com.example.englishquestion.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.englishquestion.model.Catalog;
import com.example.englishquestion.R;

import java.util.ArrayList;
import java.util.List;

public class CataAdapter extends ArrayAdapter {
    Activity context;
    int layoutID;
    ArrayList<Catalog> list = null;

    int drawable[] = {R.drawable.animal, R.drawable.fiction, R.drawable.ocean, R.drawable.place,
            R.drawable.home, R.drawable.plant, R.drawable.job,
            R.drawable.transport, R.drawable.school, R.drawable.weapon};

    public CataAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = (Activity) context;
        this.layoutID = resource;
        this.list = (ArrayList<Catalog>) objects;
    }

    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parents) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID, null);
        if((list.size() > 0) && (position >= 0)) {
            TextView txtCatName = convertView.findViewById(R.id.txtCatName);
            LinearLayout boxlayout = convertView.findViewById(R.id.boxlayout);
            txtCatName.setText(list.get(position).getCatName());
            boxlayout.setBackgroundResource(drawable[list.get(position).getBackground() - 1]);
        }
        return convertView;
    }
}
