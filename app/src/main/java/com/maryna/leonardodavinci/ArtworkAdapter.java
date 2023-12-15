package com.maryna.leonardodavinci;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArtworkAdapter extends RecyclerView.Adapter<ArtworkAdapter.ViewHolder> {
    private List<Artwork> artworks;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView titleTextView;
        public TextView yearTextView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
            titleTextView = view.findViewById(R.id.titleTextView);
            yearTextView = view.findViewById(R.id.yearTextView);
        }
    }

    public void setArtworks(List<Artwork> artworks) {
        this.artworks = artworks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_artwork, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (artworks != null && position < artworks.size()) {
            Artwork artwork = artworks.get(position);
            holder.imageView.setImageResource(artwork.getImageResourceId());
            holder.titleTextView.setText(artwork.getTitle());
            holder.yearTextView.setText(String.valueOf(artwork.getYear()));
        }
    }

    @Override
    public int getItemCount() {
        return artworks != null ? artworks.size() : 0;
    }
}