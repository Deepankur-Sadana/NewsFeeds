package com.example.newslistwithsearchsample.data.local

import android.provider.MediaStore.Audio.Artists
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newslistwithsearchsample.data.entity.Article

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Artists>)

    @Query("Select * From movies Order By page")
    fun getMovies(): PagingSource<Int, Article>

    @Query("Delete From movies")
    suspend fun clearAllMovies()
}