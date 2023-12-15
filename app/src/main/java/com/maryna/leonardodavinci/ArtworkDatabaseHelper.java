package com.maryna.leonardodavinci;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ArtworkDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "artworks.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_ARTWORKS = "artworks";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_IMAGE_RESOURCE_ID = "image_resource_id";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_ARTWORKS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_YEAR + " INTEGER, " +
                    COLUMN_IMAGE_RESOURCE_ID + " INTEGER);";

    public ArtworkDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTWORKS);
        onCreate(db);
    }
}
