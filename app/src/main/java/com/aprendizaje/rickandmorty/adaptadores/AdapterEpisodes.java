package com.aprendizaje.rickandmorty.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aprendizaje.rickandmorty.R;
import com.aprendizaje.rickandmorty.modelos.Episode;

import java.util.ArrayList;

public class AdapterEpisodes  extends RecyclerView.Adapter<AdapterEpisodes.EpisodeViewHolder>{
    ArrayList<Episode> episodeArrayList;
    Context context;

    public AdapterEpisodes(ArrayList<Episode> episodeArrayList, Context context) {
        this.episodeArrayList = episodeArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_episode, null,false);
        return new AdapterEpisodes.EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        holder.namelistEpisode.setText(episodeArrayList.get(position).getName());
        holder.airDateListEpisode.setText(episodeArrayList.get(position).getAir_date());
    }

    @Override
    public int getItemCount() {
        return episodeArrayList.size();
    }

    public class EpisodeViewHolder extends RecyclerView.ViewHolder {
        TextView namelistEpisode;
        TextView airDateListEpisode;
        public EpisodeViewHolder(@NonNull View itemView) {
            super(itemView);

            namelistEpisode = itemView.findViewById(R.id.namelistEpisode);
            airDateListEpisode = itemView.findViewById(R.id.airDateListEpisode);
        }
    }
}
