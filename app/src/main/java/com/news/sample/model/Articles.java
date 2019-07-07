
package com.news.sample.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Articles {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName("results")
    @Expose
    private List<NewsArticle> newsEntities = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public List<NewsArticle> getNewsEntities() {
        return newsEntities;
    }

    public void setNewsEntities(List<NewsArticle> newsEntities) {
        this.newsEntities = newsEntities;
    }

}
