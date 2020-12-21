package edu.pe.idat.appapirestretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import edu.pe.idat.appapirestretrofit.adapter.AlbumAdapter;
import edu.pe.idat.appapirestretrofit.api.ApiServiceAlbum;
import edu.pe.idat.appapirestretrofit.model.Album;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private ApiServiceAlbum apiServiceAlbum;
    private RecyclerView rvdatos;
    private AlbumAdapter albumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvdatos =  findViewById(R.id.rvdatos);
        albumAdapter = new AlbumAdapter(this);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //rvdatos.setLayoutManager(new LinearLayoutManager(this));
        //rvdatos.setLayoutManager(new GridLayoutManager(this, 2));
        rvdatos.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        rvdatos.setAdapter(albumAdapter);
        apiServiceAlbum = retrofit.create(ApiServiceAlbum.class);
        consumirApiRest();
    }

    private void consumirApiRest(){
        Call<List<Album>> albumRespuesta = apiServiceAlbum.obtenerListaAlbum();
        albumRespuesta.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if(response.isSuccessful()){
                    List<Album> lstalbum = response.body();
                    albumAdapter.agregarElementos(lstalbum);
                }else{
                    Log.e("TagRetrofit", response.errorBody().toString());
                }
            }
            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Log.e("TagRetrofit", t.getMessage());
            }
        });
    }
}