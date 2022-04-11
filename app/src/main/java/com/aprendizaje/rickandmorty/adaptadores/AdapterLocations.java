package com.aprendizaje.rickandmorty.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aprendizaje.rickandmorty.R;
import com.aprendizaje.rickandmorty.modelos.Episode;
import com.aprendizaje.rickandmorty.modelos.Location;

import java.util.ArrayList;

public class AdapterLocations  extends RecyclerView.Adapter<AdapterLocations.LocationsViewHolder>{

    ArrayList<Location> locationArrayList;
    Context context;

    public AdapterLocations(ArrayList<Location> locationArrayList, Context context) {
        this.locationArrayList = locationArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public LocationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_location, null,false);
        return new AdapterLocations.LocationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationsViewHolder holder, int position) {
        holder.namelistLocations.setText(locationArrayList.get(position).getName());
        holder.typelistLocations.setText(locationArrayList.get(position).getType());
        holder.dimensionlistLocations.setText(locationArrayList.get(position).getDimension());
        holder.residentslistLocations.setText(String.valueOf(locationArrayList.get(position).getResidents().size()));
    }

    @Override
    public int getItemCount() {
        return locationArrayList.size();
    }

    public class LocationsViewHolder extends RecyclerView.ViewHolder {
        TextView namelistLocations;
        TextView typelistLocations;
        TextView dimensionlistLocations;
        TextView residentslistLocations;
        public LocationsViewHolder(@NonNull View itemView) {
            super(itemView);

            namelistLocations = itemView.findViewById(R.id.namelistLocations);
            typelistLocations = itemView.findViewById(R.id.typelistLocations);
            dimensionlistLocations = itemView.findViewById(R.id.dimensionlistLocations);
            residentslistLocations = itemView.findViewById(R.id.residentslistLocations);
        }
    }
}
