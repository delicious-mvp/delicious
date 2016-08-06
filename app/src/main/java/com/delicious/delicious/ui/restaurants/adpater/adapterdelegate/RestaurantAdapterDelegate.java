package com.delicious.delicious.ui.restaurants.adpater.adapterdelegate;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.delicious.delicious.R;
import com.delicious.delicious.ui.restaurants.adpater.displayitem.DisplayItem;
import com.delicious.delicious.ui.restaurants.adpater.displayitem.RestaurantDisplayItem;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.hannesdorfmann.adapterdelegates2.AbsListItemAdapterDelegate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantAdapterDelegate extends AbsListItemAdapterDelegate<RestaurantDisplayItem, DisplayItem, RestaurantAdapterDelegate.ShopViewHolder> {


    @Override
    protected boolean isForViewType(@NonNull DisplayItem item, List<DisplayItem> items, int position) {
        return item instanceof RestaurantDisplayItem;
    }

    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
        return new ShopViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull RestaurantDisplayItem item, @NonNull ShopViewHolder viewHolder) {
        viewHolder.titleTextView.setText(item.getTitle());
        viewHolder.distanceTextView.setText(String.format("%sm", item.getDistance()));
        viewHolder.addressTextView.setText(item.getAddress());

//        boolean hasThumbnail = !Strings.isNullOrEmpty(item.getThumbnailUrl());
//        if (hasThumbnail) {
//            viewHolder.thumbnailImageView.setVisibility(View.VISIBLE);
//
//
//        } else {
//            viewHolder.thumbnailImageView.setVisibility(View.GONE);
//        }

        ImageRequest imageRequest= ImageRequestBuilder.
                newBuilderWithSource(Uri.parse(item.getThumbnailUrl()))
                .setLocalThumbnailPreviewsEnabled(false)
                /*.setResizeOptions(new ResizeOptions(viewHolder.item_thumb.getWidth(),viewHolder.item_thumb.getHeight()))*/.build();

        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .build();
        viewHolder.thumbnailImageView.setController(draweeController);
    }


    static class ShopViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail_imageview)
        SimpleDraweeView thumbnailImageView;

        @BindView(R.id.title_textview)
        TextView titleTextView;

        @BindView(R.id.distance_textview)
        TextView distanceTextView;

        @BindView(R.id.address_textview)
        TextView addressTextView;

        public ShopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
