package com.example.a21752434.appwsretrofit2.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a21752434.appwsretrofit2.R;

import java.util.ArrayList;

public class PokemonAdaptador extends RecyclerView.Adapter<PokemonAdaptador.PokemonViewHolder> {

    private ArrayList<Pokemon> lista;

    public PokemonAdaptador(ArrayList<Pokemon> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.pokemon_item, viewGroup, false);
        PokemonViewHolder pvh = new PokemonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder pokemonViewHolder, int i) {
        pokemonViewHolder.bindPokemon(lista.get(i));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public static class PokemonViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvUrl;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNombre);
            tvUrl = itemView.findViewById(R.id.tvUrl);

        }

        public void bindPokemon(Pokemon poke) {
            tvName.setText(poke.getName());
            tvUrl.setText(poke.getUrl());
        }
    }

}
