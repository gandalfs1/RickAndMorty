package com.aprendizaje.rickandmorty.adaptadores;

import static com.aprendizaje.rickandmorty.utilidades.Constantes.URL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aprendizaje.rickandmorty.R;
import com.aprendizaje.rickandmorty.actividades.DataCharacter;
import com.aprendizaje.rickandmorty.modelos.Character;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterCharacters extends RecyclerView.Adapter<AdapterCharacters.CharacterViewHolder> {

    ArrayList<Character> characterArrayList;
    Activity activity;

    public AdapterCharacters(ArrayList<Character> characterArrayList, Activity activity) {
        this.characterArrayList = characterArrayList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_chracter, null,false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.aliveCharacter.setVisibility(View.GONE);
        holder.deadCharacter.setVisibility(View.GONE);
        holder.unknownCharacter.setVisibility(View.GONE);
        Glide
                .with(activity)
                .load(characterArrayList.get(position).getImage())
                .centerCrop()
                .into(holder.imgCharacter);
        holder.nameCharacter.setText(characterArrayList.get(position).getName());

        holder.statusCharacter.setText(characterArrayList.get(position).getStatus());

        switch (characterArrayList.get(position).getStatus()){
            case "Alive":
                holder.aliveCharacter.setVisibility(View.VISIBLE);
                break;
            case "Dead":
                holder.deadCharacter.setVisibility(View.VISIBLE);
                break;
            default:
                holder.unknownCharacter.setVisibility(View.VISIBLE);
                break;
        }
        holder.speciesCharacter.setText(characterArrayList.get(position).getSpecies());
        holder.locatioNameCharacter.setText(characterArrayList.get(position).getLocation().getName());
        holder.firstSeenCharacter.setText(characterArrayList.get(position).getFirstEpisode());
    }

    @Override
    public int getItemCount() {
        return characterArrayList.size();
    }

    public class CharacterViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCharacter;
        TextView nameCharacter;
        TextView statusCharacter;
        TextView speciesCharacter;
        TextView locatioNameCharacter;
        TextView firstSeenCharacter;
        ImageView aliveCharacter;
        ImageView deadCharacter;
        ImageView unknownCharacter;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCharacter = itemView.findViewById(R.id.imgCharacter);
            nameCharacter = itemView.findViewById(R.id.nameCharacter);
            statusCharacter = itemView.findViewById(R.id.statusCharacter);
            speciesCharacter = itemView.findViewById(R.id.speciesCharacter);
            locatioNameCharacter = itemView.findViewById(R.id.locatioNameCharacter);
            firstSeenCharacter = itemView.findViewById(R.id.firstSeenCharacter);
            aliveCharacter = itemView.findViewById(R.id.aliveCharacter);
            deadCharacter = itemView.findViewById(R.id.deadCharacter);
            unknownCharacter = itemView.findViewById(R.id.unknownCharacter);

            nameCharacter.setOnClickListener(v ->{
                Context context = v.getContext();
                Intent intent = new Intent(context, DataCharacter.class);
                intent.putExtra("ID", characterArrayList.get(getAdapterPosition()).getId());
                context.startActivity(intent);
            });
        }
    }
}
