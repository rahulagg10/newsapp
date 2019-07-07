package com.news.sample.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.news.sample.model.Articles;
import com.news.sample.network.ApiClient;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MaiViewModel extends AndroidViewModel {
	
    MutableLiveData<Articles> articleLiveData;
    private Single<Articles> articleResponse;
    private DisposableSingleObserver<Articles> disposableSingleObserver;


    public MaiViewModel(@NonNull Application application) {
        super(application);
    }


    public void requestArticle() {
      
      
        articleLiveData = new MutableLiveData<Articles>();

        articleResponse = ApiClient.getApiClient().getUpcomongMatches();

        disposableSingleObserver = articleResponse.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<Articles>() {
                    @Override
                    public void onSuccess(Articles articles) {
                      Log.e("test", articles.toString());
                        articleLiveData.setValue(articles);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplication().getApplicationContext(),
                                "Error in req server", Toast.LENGTH_LONG).show();
                    }
                });

    }

    public LiveData<Articles> getArticleLiveData() {
        return articleLiveData;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposableSingleObserver != null)
            disposableSingleObserver.dispose();
    }
	
	
}
