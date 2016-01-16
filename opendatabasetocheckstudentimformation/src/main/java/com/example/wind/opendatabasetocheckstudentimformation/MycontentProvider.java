package com.example.wind.opendatabasetocheckstudentimformation;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Wind on 2016/1/16.
 */
public class MycontentProvider extends ContentProvider {
    private static final UriMatcher um;
    static {
        um = new UriMatcher(UriMatcher.NO_MATCH);
        um.addURI("com.example.wind.opendatabasetocheckstudentimformation.provider.student","stu",1);
    }
    SQLiteDatabase sld;

    @Override
    public boolean onCreate() {
        sld = SQLiteDatabase.openDatabase(
                "/data/data/com.example.wind.opendatabasetocheckstudentimformation/mydb",
                null,
                SQLiteDatabase.OPEN_READWRITE | SQLiteDatabase.CREATE_IF_NECESSARY
        );
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
