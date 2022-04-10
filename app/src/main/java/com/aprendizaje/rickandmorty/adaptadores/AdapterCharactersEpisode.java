package com.aprendizaje.rickandmorty.adaptadores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aprendizaje.rickandmorty.R;

import java.util.ArrayList;

public class AdapterCharactersEpisode extends RecyclerView.Adapter<AdapterCharactersEpisode.EpisodeViewHolder>{

    ArrayList<String> listEpisodes;
    Activity activity;

    public AdapterCharactersEpisode(ArrayList<String> listEpisodes, Activity activity) {
        this.listEpisodes = listEpisodes;
        this.activity = activity;
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_character_episodes, null,false);
        return new AdapterCharactersEpisode.EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {

        String episode = listEpisodes.get(position);
        episode = episode.substring(40,episode.length());

        holder.episodeNumber.setText(episode);

    }

    @Override
    public int getItemCount() {
        return listEpisodes.size();
    }

    public class EpisodeViewHolder extends RecyclerView.ViewHolder {
        TextView episodeNumber;
        public EpisodeViewHolder(@NonNull View itemView) {
            super(itemView);
            episodeNumber = itemView.findViewById(R.id.episodeNumber);
        }
    }
}
