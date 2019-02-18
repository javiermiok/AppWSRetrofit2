package com.example.a21752434.appwsretrofit2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.a21752434.appwsretrofit2.model.Pokemon;
import com.example.a21752434.appwsretrofit2.model.PokemonAdaptador;
import com.example.a21752434.appwsretrofit2.model.PokemonRes;
import com.example.a21752434.appwsretrofit2.retrofitUtils.APIRestService;
import com.example.a21752434.appwsretrofit2.retrofitUtils.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private ArrayList<Pokemon> datos;
    private LinearLayoutManager llm;
    private PokemonAdaptador adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rvPokemons);
        datos = new ArrayList<Pokemon>();

        llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,true);


        adaptador = new PokemonAdaptador(datos);

        rv.setAdapter(adaptador);

        rv.setLayoutManager(llm);
    }

    public void consumirWS(View v) {
        Retrofit r = RetrofitClient.getClient(APIRestService.BASE_URL);
        APIRestService ars = r.create(APIRestService.class);
        Call<PokemonRes> call = ars.obtenerPokemon();


        call.enqueue(new Callback<PokemonRes>() {

            @Override
            public void onResponse(Call<PokemonRes> call, Response<PokemonRes> response) {
                if(response.isSuccessful()) {
                    PokemonRes pokeRes = response.body();
                    for (Pokemon poke: pokeRes.getResults()) {
                        datos.add(poke);
                    }
                    adaptador.notifyDataSetChanged();

                } else {
                    Toast.makeText(MainActivity.this,
                            "Error: " + response.code(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PokemonRes> call, Throwable t) {
                Log.e("error", t.toString());
                Toast.makeText(MainActivity.this,
                        "Error: No se ha podido conectar con el Webservice" ,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
