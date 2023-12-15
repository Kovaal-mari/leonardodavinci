package com.maryna.leonardodavinci;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
public class ArtworkDataSource {
    private SQLiteDatabase database;
    private ArtworkDatabaseHelper dbHelper;

    public ArtworkDataSource(Context context) {
        dbHelper = new ArtworkDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertArtwork(Artwork artwork) {
        ContentValues values = new ContentValues();
        values.put(ArtworkDatabaseHelper.COLUMN_TITLE, artwork.getTitle());
        values.put(ArtworkDatabaseHelper.COLUMN_YEAR, artwork.getYear());
        values.put(ArtworkDatabaseHelper.COLUMN_IMAGE_RESOURCE_ID, artwork.getImageResourceId());

        String whereClause = ArtworkDatabaseHelper.COLUMN_TITLE + " = ?";
        String[] whereArgs = {artwork.getTitle()};

        int rowsUpdated = database.update(ArtworkDatabaseHelper.TABLE_ARTWORKS, values, whereClause, whereArgs);

        if (rowsUpdated <= 0) {
            database.insert(ArtworkDatabaseHelper.TABLE_ARTWORKS, null, values);
        }

    }


    public List<Artwork> searchArtworks(String query) {
        List<Artwork> artworks = new ArrayList<>();

        Cursor cursor = database.query(
                ArtworkDatabaseHelper.TABLE_ARTWORKS,
                null,
                ArtworkDatabaseHelper.COLUMN_TITLE + " LIKE ? OR " +
                        ArtworkDatabaseHelper.COLUMN_YEAR + " = ?",
                new String[]{"%" + query + "%", query},
                null,
                null,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex(ArtworkDatabaseHelper.COLUMN_TITLE));
                int year = cursor.getInt(cursor.getColumnIndex(ArtworkDatabaseHelper.COLUMN_YEAR));
                int imageResourceId = cursor.getInt(cursor.getColumnIndex(ArtworkDatabaseHelper.COLUMN_IMAGE_RESOURCE_ID));

                artworks.add(new Artwork(title, year, imageResourceId));
            } while (cursor.moveToNext());

            cursor.close();
        }

        return artworks;
    }

    public List<Artwork> getAllArtworks() {
        List<Artwork> artworks = new ArrayList<>();

        Cursor cursor = database.query(
                ArtworkDatabaseHelper.TABLE_ARTWORKS,
                null,
                null,
                null,
                null,
                null,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex(ArtworkDatabaseHelper.COLUMN_TITLE));
                int year = cursor.getInt(cursor.getColumnIndex(ArtworkDatabaseHelper.COLUMN_YEAR));
                int imageResourceId = cursor.getInt(cursor.getColumnIndex(ArtworkDatabaseHelper.COLUMN_IMAGE_RESOURCE_ID));

                artworks.add(new Artwork(title, year, imageResourceId));
            } while (cursor.moveToNext());

            cursor.close();
        }

        return artworks;
    }
    public void clearAllArtworks() {
        database.delete(ArtworkDatabaseHelper.TABLE_ARTWORKS, null, null);
    }
}
