package com.trantan.sqliteqltoanha.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.trantan.sqliteqltoanha.R;
import com.trantan.sqliteqltoanha.adapter.BuildingAdapter;
import com.trantan.sqliteqltoanha.data.DBManager;
import com.trantan.sqliteqltoanha.model.Building;

import java.util.List;

public class QLToaNhaActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {
    private EditText edtCode;
    private EditText edtName;
    private Button btnAdd;
    private Button btnCancle;
    private Button btnUpdate;
    private ListView lvBuilding;
    private BuildingAdapter adapter;
    private List<Building> listBuilding;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qltoa_nha);

        dbManager = new DBManager(this);
        listBuilding = dbManager.getAllBuilding();
        initView();
        setAdapter();

        lvBuilding.setOnItemLongClickListener(this);
        lvBuilding.setOnItemClickListener(this);
    }

    private void initView() {
        edtCode = findViewById(R.id.edtCodeBuilding);
        edtName = findViewById(R.id.edtName);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancle = findViewById(R.id.btnCancle);
        btnUpdate = findViewById(R.id.btnUpdate);
        lvBuilding = findViewById(R.id.lvBuilding);
    }

    private Building createBuilding() {
        String code = edtCode.getText().toString();
        String name = edtName.getText().toString();

        Building building = new Building(code, name);

        return building;
    }

    private void setAdapter() {
        if (adapter == null) {
            adapter = new BuildingAdapter(this, R.layout.item_building, listBuilding);
            lvBuilding.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
            lvBuilding.setSelection(adapter.getCount() - 1);
        }
    }

    public void onButtonCancle(View view) {
        clearEditText();
    }

    private void clearEditText() {
        edtCode.setText("");
        edtName.setText("");
    }

    private void refreshListBuilding() {
        listBuilding.clear();
        listBuilding.addAll(dbManager.getAllBuilding());
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public void onButtonAddBuilding(View view) {
        if (edtCode.getText().toString().isEmpty() || edtName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Vui long nhap day du thong tin", Toast.LENGTH_LONG).show();
        }else {
            Building building = createBuilding();
            if (building != null) {
                dbManager.addBuilding(building);
                clearEditText();
            }
            refreshListBuilding();
            setAdapter();
        }
    }

    int index = -1;

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        index = position;

        AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
        builder.setTitle("Delete!");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage("Do you want delete this item")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Building building = listBuilding.get(index);
                        int result = dbManager.deleteBuilding(building);
                        if (result > 0) {
                            Toast.makeText(getApplicationContext(), "Delete Successfully", Toast.LENGTH_LONG).show();
                            refreshListBuilding();
                        } else {
                            Toast.makeText(getApplicationContext(), "Delete failed", Toast.LENGTH_LONG).show();
                        }

                        btnAdd.setVisibility(View.VISIBLE);
                        btnCancle.setVisibility(View.VISIBLE);
                        btnUpdate.setVisibility(View.INVISIBLE);
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        btnAdd.setVisibility(View.VISIBLE);
                        btnCancle.setVisibility(View.VISIBLE);
                        btnUpdate.setVisibility(View.INVISIBLE);
                    }
                });

        builder.show();

        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        index = position;
        Building building = listBuilding.get(index);


        edtCode.setText(building.getmCode());
        edtName.setText(building.getmName());

        btnUpdate.setVisibility(View.VISIBLE);
        btnAdd.setVisibility(View.INVISIBLE);
        btnCancle.setVisibility(View.INVISIBLE);
    }


    public void onButtonUpdate(View view) {
        Building building = new Building();

        building.setmID(listBuilding.get(index).getmID());

        building.setmCode(edtCode.getText().toString());
        building.setmName(edtName.getText().toString());

        int result = dbManager.updateBuilding(building);
        clearEditText();

        if (result > 0){
            refreshListBuilding();
        }
        btnAdd.setVisibility(View.VISIBLE);
        btnCancle.setVisibility(View.VISIBLE);
        btnUpdate.setVisibility(View.INVISIBLE);
    }
}
