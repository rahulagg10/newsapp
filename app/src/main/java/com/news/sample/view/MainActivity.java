package com.news.sample.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.news.sample.R;
import com.news.sample.model.Articles;
import com.news.sample.viewmodel.MaiViewModel;


public class MainActivity extends AppCompatActivity {
	
	private static final String TAG = MainActivity.class.getSimpleName();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final RecyclerView recyclerView = findViewById(R.id.news_recycler_view);
		
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		final NewsListAdapter newsAdapter = new NewsListAdapter(MainActivity.this, null);
		MaiViewModel playBioViewModel = ViewModelProviders.of(this).get(MaiViewModel.class);
		
		
		playBioViewModel.requestArticle();
		
		playBioViewModel.getArticleLiveData().observe(MainActivity.this, new Observer<Articles>() {
			@Override
			public void onChanged(@Nullable Articles articles) {
				
				newsAdapter.refresh(articles.getNewsEntities());
				recyclerView.setAdapter(newsAdapter);
//
			}
		});
	}


}
