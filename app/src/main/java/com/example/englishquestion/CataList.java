package com.example.englishquestion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.englishquestion.adapter.CataAdapter;
import com.example.englishquestion.model.Catalog;
import com.example.englishquestion.sqlite.CatalogDAO;

import java.util.List;

public class CataList extends AppCompatActivity {

    TextView txtLogo;
    ListView cata_list;

    List<Catalog> list = null;
    CataAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cata_list);
        getWidgetsControl();
        CatalogDAO catalogDB = new CatalogDAO(this);
        catalogDB.getCatData();
        list = catalogDB.getAll();
        adapter = new CataAdapter(CataList.this, R.layout.cata_list_item_custom, list);
        cata_list.setAdapter(adapter);
        cata_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CataList.this, MainScreen.class);
                intent.putExtra("catID", list.get(i).getCatID());
                intent.putExtra("catName", list.get(i).getCatName());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Dialog dialog = new Dialog(CataList.this);
        dialog.setContentView(R.layout.dialog_custom_exit);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.white_solid_radius_20dp));
        Button ok = dialog.findViewById(R.id.btnOK);
        Button cancel = dialog.findViewById(R.id.btnCancel);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    private void getWidgetsControl() {
        txtLogo = findViewById(R.id.txtLogo);
        cata_list = findViewById(R.id.cata_list);
    }
}