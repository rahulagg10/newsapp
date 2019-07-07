package com.news.sample.network;

import com.news.sample.model.Articles;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ArticleApi {

//http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=IEUtOocZjIzq9H8RqIAyBAbN9k0GoecT
    @GET("mostpopular/v2/mostviewed/all-sections/7.json?api-key=IEUtOocZjIzq9H8RqIAyBAbN9k0GoecT")
     Single<Articles> getUpcomongMatches();
}