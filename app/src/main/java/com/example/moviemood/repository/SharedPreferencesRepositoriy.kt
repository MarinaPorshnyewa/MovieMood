package com.example.moviemood.repository

import android.content.Context
import com.example.moviemood.model.Movie
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

private const val FILE_NAME = "shared_pref_file"
private const val FILMS = "film"

@Singleton
class SharedPreferencesRepositoriy @Inject constructor(
    private val context: Context
) {

    private val sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    fun saveListFilms(data: ArrayList<Movie>) {
        sharedPreferences.edit()
            .putString(FILMS, Gson().toJson(data))
            .apply()
    }

    fun getListFilms(): ArrayList<Movie> {
        val json = sharedPreferences.getString(FILMS, "")
        return if (json.isNullOrBlank()) {
            arrayListOf<Movie>()
        } else {
            GsonBuilder().create().fromJson<ArrayList<Movie>>(
                json,
                ArrayList::class.java
            )
        }
    }
    fun favoriteJsonToArray(): ArrayList<Movie>? {
        val recipeJson = sharedPreferences.getString(FILMS, "")
        val recipeListType: Type =
            object : TypeToken<List<Movie?>?>() {}.type
        return Gson().fromJson<List<Movie>>(recipeJson, recipeListType) as ArrayList<Movie>?
    }
}