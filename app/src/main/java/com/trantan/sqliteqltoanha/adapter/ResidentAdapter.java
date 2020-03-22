package com.trantan.sqliteqltoanha.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.trantan.sqliteqltoanha.R;
import com.trantan.sqliteqltoanha.model.Resident;

import java.util.List;

public class ResidentAdapter extends BaseAdapter {
    private Context context;
    private int resource;
    private List<Resident> listResident;

    public ResidentAdapter(Context context, int resource, List<Resident> listResident) {
        this.context = context;
        this.resource = resource;
        this.listResident = listResident;
    }

    @Override
    public int getCount() {
        if (listResident == null){
            return 0;
        }
        return listResident.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ResidentHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resident,parent,false);
            holder = new ResidentHolder();
//            holder.txtId = convertView.findViewById(R.id.txtIDResident);
            holder.txtNameOfHouseholde = convertView.findViewById(R.id.txtNameOfHousehold);
            holder.txtRoomNumber = convertView.findViewById(R.id.txtRoomNumber);
            holder.txtNameOfBuilding = convertView.findViewById(R.id.txtNameOfBuilding);
            holder.txtPhoneNumber = convertView.findViewById(R.id.txtPhoneNumber);

            convertView.setTag(holder);
        }else {
            holder = (ResidentHolder) convertView.getTag();
        }

        Resident resident = listResident.get(position);

        //holder.txtId.setText(String.valueOf(resident.getmID()));
        holder.txtNameOfHouseholde.setText(resident.getmNameOfHouseHold());
        holder.txtRoomNumber.setText(resident.getmRoomNumber());
        holder.txtNameOfBuilding.setText(resident.getmNameOfBuilding());
        holder.txtPhoneNumber.setText(String.valueOf(resident.getmPhoneNumber()));

        return convertView;
    }

    public class ResidentHolder{
        TextView txtId;
        TextView txtNameOfHouseholde;
        TextView txtRoomNumber;
        TextView txtNameOfBuilding;
        TextView txtPhoneNumber;
    }
}
