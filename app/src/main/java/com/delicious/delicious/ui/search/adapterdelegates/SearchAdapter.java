package com.delicious.delicious.ui.search.adapterdelegates;

import android.util.Log;

import com.delicious.delicious.widget.recyclerview.OnRecyclerItemClickListener;
import com.delicious.delicious.network.data.ImageItem;
import com.delicious.delicious.ui.search.adapterdelegates.model.SearchDataModel;
import com.delicious.delicious.ui.search.adapterdelegates.view.SearchAdapterView;
import com.hannesdorfmann.adapterdelegates2.ListDelegationAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chonamdu on 2016. 6. 7..
 */

public class SearchAdapter extends ListDelegationAdapter<List<ImageItem>> implements SearchDataModel,SearchAdapterView{
    private OnRecyclerItemClickListener mOnRecyclerItemClickListener;
    private ArrayList<ImageItem> list = new ArrayList<>();
    public SearchAdapter(){
        this.delegatesManager.addDelegate(new SearchAdapterDelegate(mOnRecyclerItemClickListener));
        setItems(list);
    }

    @Override
    public void refresh(){
        Log.v("DEBUG230","SearchAdapter refresh");
        notifyDataSetChanged();
    }

    @Override
    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        mOnRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @Override
    public void addItem(ImageItem imageItem) {
        items.add(imageItem);
    }

    @Override
    public int getSize() {
        return getItemCount();
    }

    @Override
    public ImageItem getItem(int position) {
        return getItems() != null ? getItems().get(position) : null;
    }

    @Override
    public void clear() {
        if(getItems() != null){
            getItems().clear();
        }
    }
}
