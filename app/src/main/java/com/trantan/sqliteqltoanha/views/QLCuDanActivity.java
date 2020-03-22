package com.trantan.sqliteqltoanha.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.trantan.sqliteqltoanha.R;
import com.trantan.sqliteqltoanha.adapter.ResidentAdapter;
import com.trantan.sqliteqltoanha.data.DBManager;
import com.trantan.sqliteqltoanha.model.Building;
import com.trantan.sqliteqltoanha.model.Resident;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class QLCuDanActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {
    private EditText edtNameOfHouseHold;
    private EditText edtRoomNumber;
    private EditText edtPhoneNumber;
    private Button btnAdd;
    private Button btnCancle;
    private Button btnUpdate;
    private ListView lvResident;
    private Spinner spnBuilding;
    private List<Building> listBuiding;
    private List<String> listBuildingName;
    private List<Resident> listResident;
    private ResidentAdapter residentAdapter;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlcu_dan);

        dbManager = new DBManager(this);
        initView();

        listResident = dbManager.getAllResident();
        listBuiding = dbManager.getAllBuilding();
        listBuildingName = new ArrayList<>();


        //Lay ra danh sach ten cac toa nha, loai bo cac phan tu trung nhau va sap xep theo thu tu alpha
        for (Building building : listBuiding) {
            listBuildingName.add(building.getmName());
        }

        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(listBuildingName);

        listBuildingName.clear();
        listBuildingName.addAll(hashSet);

        Collections.sort(listBuildingName);


        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listBuildingName);
        spnBuilding.setAdapter(adapter);
        spnBuilding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lvResident.setOnItemLongClickListener(this);
        lvResident.setOnItemClickListener(this);
        setAdapter();
    }

    private void initView() {
        spnBuilding = findViewById(R.id.spnBuilding);
        edtNameOfHouseHold = findViewById(R.id.edtNameOfHousehold);
        edtRoomNumber = findViewById(R.id.edtRoomNumber);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        lvResident = findViewById(R.id.lvResident);

        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancle = findViewById(R.id.btnCancle);
    }

    private Resident createResident() {
        String nameOfHousehold = edtNameOfHouseHold.getText().toString();
        String roomNumber = edtRoomNumber.getText().toString();
        int phoneNumber = Integer.parseInt(edtPhoneNumber.getText().toString());

        String nameOfBuilding = (String) spnBuilding.getSelectedItem();

        Resident resident = new Resident(nameOfHousehold, roomNumber, nameOfBuilding, phoneNumber);

        return resident;
    }

    private void setAdapter() {
        if (residentAdapter == null) {
            residentAdapter = new ResidentAdapter(this, R.layout.item_resident, listResident);
            lvResident.setAdapter(residentAdapter);
        } else {
            residentAdapter.notifyDataSetChanged();
            lvResident.setSelection(residentAdapter.getCount() - 1);
        }
    }

    public void onButtonAdd(View view) {
        if (edtNameOfHouseHold.getText().toString().isEmpty()
                || edtRoomNumber.getText().toString().isEmpty()
                || edtPhoneNumber.getText().toString().isEmpty()){

            Toast.makeText(this, "Vui long nhap day du thong tin", Toast.LENGTH_SHORT).show();
        }else {
            Resident resident = createResident();
            if (resident != null) {

                dbManager.addResident(resident);

                clearEditText();
            }
            refreshListResident();
            setAdapter();
        }

    }

    public void onButtonCancle(View view) {
        clearEditText();
    }

    private void refreshListResident() {
        listResident.clear();
        listResident.addAll(dbManager.getAllResident());
        if (residentAdapter != null) {
            residentAdapter.notifyDataSetChanged();
        }
    }

    private void clearEditText() {
        edtNameOfHouseHold.setText("");
        edtRoomNumber.setText("");
        edtPhoneNumber.setText("");
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
                        Resident resident = listResident.get(index);
                        int result = dbManager.deleteResident(resident);

                        if (result > 0) {
                            Toast.makeText(getApplicationContext(), "Delete Successfully", Toast.LENGTH_LONG).show();
                            refreshListResident();
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
        Resident resident = listResident.get(index);

        edtNameOfHouseHold.setText(resident.getmNameOfHouseHold());
        edtRoomNumber.setText(resident.getmRoomNumber());
        edtPhoneNumber.setText(String.valueOf(resident.getmPhoneNumber()));

        btnUpdate.setVisibility(View.VISIBLE);
        btnAdd.setVisibility(View.INVISIBLE);
        btnCancle.setVisibility(View.INVISIBLE);
    }

    public void onButtonUpdate(View view) {
        Resident resident = new Resident();

        resident.setmID(listResident.get(index).getmID());
        resident.setmNameOfHouseHold(edtNameOfHouseHold.getText().toString());
        resident.setmRoomNumber(edtRoomNumber.getText().toString());
        resident.setmNameOfBuilding(spnBuilding.getSelectedItem().toString());
        resident.setmPhoneNumber(Integer.parseInt(edtPhoneNumber.getText().toString()));

        int result = dbManager.updateResident(resident);

        clearEditText();
        if (result > 0){
            refreshListResident();
        }

        btnAdd.setVisibility(View.VISIBLE);
        btnCancle.setVisibility(View.VISIBLE);
        btnUpdate.setVisibility(View.INVISIBLE);
    }
}
