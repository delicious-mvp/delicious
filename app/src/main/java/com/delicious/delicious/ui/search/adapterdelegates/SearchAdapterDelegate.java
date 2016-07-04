package com.delicious.delicious.ui.search.adapterdelegates;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.delicious.delicious.R;
import com.delicious.delicious.app.DeliciousApp;
import com.delicious.delicious.widget.recyclerview.OnRecyclerItemClickListener;
import com.delicious.delicious.network.data.ImageItem;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.hannesdorfmann.adapterdelegates2.AdapterDelegate;
import java.util.List;

/**
 * Created by chonamdu on 2016. 6. 7..
 */

public class SearchAdapterDelegate implements AdapterDelegate<List<ImageItem>>{
    private OnRecyclerItemClickListener mOnRecyclerItemClickListener;
    public SearchAdapterDelegate(OnRecyclerItemClickListener onRecyclerItemClickListener){
        mOnRecyclerItemClickListener = onRecyclerItemClickListener;
    }
    @Override
    public boolean isForViewType(@NonNull List<ImageItem> items, int position) {
        return items.get(position) != null;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(DeliciousApp.getAppContext()).inflate(R.layout.item_search_result, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull List<ImageItem> items, int position, @NonNull RecyclerView.ViewHolder holder) {
        SearchViewHolder viewHolder = (SearchViewHolder)holder;
        ImageItem imageItem = items.get(position);
        ImageRequest imageRequest= ImageRequestBuilder.
                newBuilderWithSource(Uri.parse(imageItem.getThumbnail()))
                .setLocalThumbnailPreviewsEnabled(false)
                /*.setResizeOptions(new ResizeOptions(viewHolder.item_thumb.getWidth(),viewHolder.item_thumb.getHeight()))*/.build();

        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .build();
        viewHolder.item_thumb.setController(draweeController);
        viewHolder.item_title.setText(imageItem.getTitle());
    }
}
