package com.delicious.delicious.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.delicious.delicious.R;
import com.delicious.delicious.base.BaseFragment;
import com.delicious.delicious.listener.OnRecyclerItemClickListener;
import com.delicious.delicious.search.adapterdelegates.SearchAdapter;
import com.delicious.delicious.search.presenter.ViewSearchContract;

import butterknife.BindView;

/**
 * Created by chonamdu on 2016. 6. 6..
 */

public class SearchFragment extends BaseFragment<ViewSearchContract.Presenter> implements ViewSearchContract.View {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    SearchAdapter searchAdapter;
    public SearchFragment(){

    }
    public static SearchFragment newInstance(){
        return new SearchFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchAdapter = new SearchAdapter();
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getPresenter().setDataModel(searchAdapter);
        getPresenter().setDataView(searchAdapter);

        getPresenter().getDataView().setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                getPresenter().onItemClick(position);
            }
        });


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("검색어를 입력해주세요.");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String s) {
                getPresenter().inputSearchText(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                getPresenter().inputSearchText(s);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void refresh() {
        Log.v("DEBUG230","refresh");
        getPresenter().getDataView().refresh();
    }

    @Override
    public void goToDetailView(int postion) {
//        Intent intent =;
    }

    @Override
    public void onDestroy() {
        getPresenter().unSubscribeSearch();
        super.onDestroy();
    }
}
