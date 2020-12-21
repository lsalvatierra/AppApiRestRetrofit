package edu.pe.idat.appapirestretrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.pe.idat.appapirestretrofit.R;
import edu.pe.idat.appapirestretrofit.model.Album;

public class AlbumAdapter
     extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>
{
    private Context context;
    private ArrayList<Album> lstalbum;
    public AlbumAdapter(Context context){
        this.context = context;
        lstalbum = new ArrayList<>();
    }
    @NonNull
    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context)
                .inflate(R.layout.item_album, parent, false);
        return new ViewHolder(vista);
    }
    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.ViewHolder holder, int position) {
        Album item = lstalbum.get(position);
        holder.tvid.setText(item.getId().toString());
        holder.tvtitulo.setText(item.getTitle());
    }
    public void agregarElementos(List<Album> listaflor){
        lstalbum.clear();
        lstalbum.addAll(listaflor);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return lstalbum.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvid;
        TextView tvtitulo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tvid);
            tvtitulo = itemView.findViewById(R.id.tvtitulo);
        }
    }
}
