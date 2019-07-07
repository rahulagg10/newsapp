package com.news.sample.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.news.sample.R;
import com.news.sample.model.NewsArticle;


/**
 * News detail view
 */
public class DetailViewActivity extends Activity {
    
    public static final String ARG_NEWS = "ARG_NEWS";
    
    
    private NewsArticle newsArticle;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        newsArticle = (NewsArticle) extras.get(ARG_NEWS);
       
        TextView titleView = (TextView) findViewById(R.id.title);
        DraweeView imageView = (DraweeView) findViewById(R.id.news_image);
        TextView summaryView = (TextView) findViewById(R.id.summary_content);

        titleView.setText(newsArticle.getTitle());
        summaryView.setText(newsArticle.getAbstract());

        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(ImageRequest.fromUri(Uri.parse(newsArticle.getMedia().get(0).getMediaMetadata().get(0).getUrl())))
                .setOldController(imageView.getController()).build();
        imageView.setController(draweeController);
    }

    public void onFullStoryClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(newsArticle.getUrl()));
        startActivity(intent);
    }
}
