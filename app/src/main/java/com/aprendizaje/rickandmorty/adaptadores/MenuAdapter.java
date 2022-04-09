package com.aprendizaje.rickandmorty.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aprendizaje.rickandmorty.CallbackMenu;
import com.aprendizaje.rickandmorty.MainActivity;
import com.aprendizaje.rickandmorty.R;
import com.aprendizaje.rickandmorty.modelos.Menu;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    ArrayList<Menu> listMenu;
    CallbackMenu callbackMenu;
    Context context;

    public MenuAdapter(Context context, ArrayList<Menu> listMenu, CallbackMenu callbackMenu) {
        this.listMenu = listMenu;
        this.callbackMenu = callbackMenu;
        this.context = context;
    }


    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_menu, null, false);
        return new MenuAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Glide
                .with(context)
                .load(listMenu.get(position).getImage())
                .centerCrop()
                .into(holder.imageMenu);

        holder.nameMenu.setText(listMenu.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        ImageView imageMenu;
        TextView nameMenu;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMenu = itemView.findViewById(R.id.imageMenu);
            nameMenu = itemView.findViewById(R.id.nameMenu);
        }
    }
}
