package com.example.simpsoncatalog.data.datasource

import android.net.http.HttpException
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.simpsoncatalog.data.datasource.api.ApiServices
import com.example.simpsoncatalog.data.response.SimpsonChResponse
import com.example.simpsoncatalog.data.response.toDomain
import com.example.simpsoncatalog.domain.entity.SimpsonCharacter
import java.io.IOException
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(
    private val apiService: ApiServices
) : PagingSource<Int, SimpsonCharacter>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SimpsonCharacter> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getCharacters(page = page)
            val characters = response.results?.map { it.toDomain() } ?: emptyList()
            LoadResult.Page(
                data = characters,
                prevKey = if (response.prev == 1) null else page - 1,
                nextKey = if (response.next == null) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, SimpsonCharacter>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}