package com.maryna.leonardodavinci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import java.util.List;
public class MainActivity extends AppCompatActivity {

    private EditText searchEditText;
    private ImageButton searchButton;
    private Button showAllButton;
    private RecyclerView recyclerView;
    private ArtworkAdapter adapter;
    private ArtworkDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        showAllButton = findViewById(R.id.showAllButton);
        recyclerView = findViewById(R.id.recyclerView);

        dataSource = new ArtworkDataSource(this);
        dataSource.open();


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchEditText.getText().toString();
                List<Artwork> searchResults = dataSource.searchArtworks(query);
                updateRecyclerView(searchResults);
                searchEditText.setText("");
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
            }
        });

        showAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAllArtworks();
            }
        });

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ArtworkAdapter();
        recyclerView.setAdapter(adapter);

        // Add sample data
        addSampleData();
    }
    private void updateRecyclerView(List<Artwork> artworks) {
        adapter.setArtworks(artworks);
        adapter.notifyDataSetChanged();
    }

    private void showAllArtworks() {
        List<Artwork> allArtworks = dataSource.getAllArtworks();
        updateRecyclerView(allArtworks);
    }
    private void addSampleData() {
        // Add sample artworks to the database
        dataSource.insertArtwork(new Artwork("The Annunciation", 1476, R.drawable.annunciation));
        dataSource.insertArtwork(new Artwork("Madonna of the Carnation", 1478, R.drawable.baptism));
        dataSource.insertArtwork(new Artwork("The Baptism of Christ", 1478, R.drawable.madonna));
        dataSource.insertArtwork(new Artwork("Ginevra de' Benci", 1480, R.drawable.ginevra));
        dataSource.insertArtwork(new Artwork("Benois Madonna", 1481, R.drawable.benois));
        dataSource.insertArtwork(new Artwork("Madonna Litta", 1495, R.drawable.litta));
        dataSource.insertArtwork(new Artwork("Portrait of a Musician", 1487, R.drawable.portrain));
        dataSource.insertArtwork(new Artwork("Lady with an Ermine", 1491, R.drawable.lady));
        dataSource.insertArtwork(new Artwork("La Belle Ferronnière", 1493, R.drawable.belle));
        dataSource.insertArtwork(new Artwork("The Last Supper", 1498, R.drawable.supper));
        dataSource.insertArtwork(new Artwork("Portrait of Isabella d'Este", 1498, R.drawable.isabella));
        dataSource.insertArtwork(new Artwork("Madonna of the Yarnwinder", 1508, R.drawable.yarnwinder));
        dataSource.insertArtwork(new Artwork("Salvator Mundi", 1510, R.drawable.salvator));
        dataSource.insertArtwork(new Artwork("The Virgin and Child with Saint Anne", 1519, R.drawable.anne));
        dataSource.insertArtwork(new Artwork("Mona Lisa", 1516, R.drawable.mona));
        dataSource.insertArtwork(new Artwork("La Scapigliata", 1508, R.drawable.scapigliata));
        dataSource.insertArtwork(new Artwork("Saint John the Baptist", 1516, R.drawable.spain));


        // Display all artworks initially
        List<Artwork> allArtworks = dataSource.searchArtworks("");
        updateRecyclerView(allArtworks);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataSource.close();
    }


}

