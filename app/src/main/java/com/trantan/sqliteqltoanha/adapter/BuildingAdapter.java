package com.trantan.sqliteqltoanha.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.trantan.sqliteqltoanha.R;
import com.trantan.sqliteqltoanha.model.Building;

import java.util.List;

public class BuildingAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<Building> listBuilding;

    public BuildingAdapter(@NonNull Context context, int resource, List<Building> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listBuilding = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BuildingHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_building,parent,false);
            holder = new BuildingHolder();
            holder.txtID = convertView.findViewById(R.id.txtID);
            holder.txtCode = convertView.findViewById(R.id.txtCode);
            holder.txtName = convertView.findViewById(R.id.txtName);

            convertView.setTag(holder);
        }else {
            holder = (BuildingHolder) convertView.getTag();
        }

        Building building = listBuilding.get(position);
        holder.txtID.setText(String.valueOf(building.getmID()));
        holder.txtCode.setText(building.getmCode());
        holder.txtName.setText(building.getmName());

        return convertView;
    }

    public class BuildingHolder{
        TextView txtID;
        TextView txtCode;
        TextView txtName;
    }
}
