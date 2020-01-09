package com.wisnu.movieapp.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.wisnu.movieapp.db.DatabaseContract.MovieColumns
import com.wisnu.movieapp.db.DatabaseContract.MovieColumns.Companion.TABLE_MOVIE
import com.wisnu.movieapp.db.DatabaseContract.TvColumns
import com.wisnu.movieapp.db.DatabaseContract.TvColumns.Companion.TABLE_TV

internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME, null,
    DATABASE_VERSION
) {
    companion object {
        private const val DATABASE_NAME = "momvieapp"
        private const val DATABASE_VERSION = 1

        private const val SQL_CREATE_TABLE_MOVIE = "CREATE TABLE $TABLE_MOVIE" +
                " (${MovieColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${MovieColumns.MOVIE_ID} TEXT NOT NULL, " +
                "${MovieColumns.TITLE} TEXT NOT NULL, " +
                "${MovieColumns.DATE} TEXT, " +
                "${MovieColumns.OVERVIEW} TEXT, " +
                "${MovieColumns.SCORE} TEXT," +
                "${MovieColumns.POSTER} TEXT)"

        private const val SQL_CREATE_TABLE_TV = "CREATE TABLE $TABLE_TV" +
                " (${TvColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${TvColumns.TV_ID} TEXT NOT NULL, " +
                "${TvColumns.TITLE} TEXT NOT NULL, " +
                "${TvColumns.DATE} TEXT, " +
                "${TvColumns.OVERVIEW} TEXT, " +
                "${TvColumns.SCORE} TEXT," +
                "${TvColumns.POSTER} TEXT)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_MOVIE)
        db?.execSQL(SQL_CREATE_TABLE_TV)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldV: Int, newV: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_MOVIE")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_TV")
        onCreate(db)
    }
}