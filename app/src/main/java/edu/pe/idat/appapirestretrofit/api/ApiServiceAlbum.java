package edu.pe.idat.appapirestretrofit.api;

import java.util.List;

import edu.pe.idat.appapirestretrofit.model.Album;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceAlbum {

    @GET("albums")
    Call<List<Album>> obtenerListaAlbum();

}
