package com.news.sample.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.news.sample.R;
import com.news.sample.model.Medium;
import com.news.sample.model.NewsArticle;

import java.util.List;


public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {
	
	private List<NewsArticle> mNewList;
	private Context mContext;
	
	public NewsListAdapter(Context context, List<NewsArticle> objects) {
		mContext = context;
		mNewList = objects;
	}
	
	@NonNull
	@Override
	public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new NewsViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_news, null));
	}
	
	public void refresh(List<NewsArticle> objects){
		mNewList = objects;
		notifyDataSetChanged();
	}
	
	@Override
	public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
		final NewsArticle newsEntity = mNewList.get(position);
		holder.newsTitle.setText(newsEntity.getTitle());
		Medium multimedia = newsEntity.getMedia().get(0);
			holder.imageView.setVisibility(View.VISIBLE);
			DraweeController draweeController = Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequest.fromUri
							(Uri.parse(multimedia.getMediaMetadata().get(0).getUrl()))).setOldController(holder.imageView.getController()).build();
			holder.imageView.setController(draweeController);
		
		holder.mainView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String title = newsEntity.getTitle();
				Intent intent = new Intent(mContext, DetailViewActivity.class);
				intent.putExtra(DetailViewActivity.ARG_NEWS, newsEntity);
			
				mContext.startActivity(intent);
			}
		});
		
	}
	
	@Override
	public int getItemCount() {
		return mNewList == null ? 0 : mNewList.size();
	}


	class NewsViewHolder extends RecyclerView.ViewHolder {
		
		TextView newsTitle;
		DraweeView imageView;
		View mainView;
		
		public NewsViewHolder(View itemView) {
			super(itemView);
			mainView = itemView;
			newsTitle = itemView.findViewById(R.id.news_title);
			imageView = itemView.findViewById(R.id.news_item_image);
		}
	}
	
}
