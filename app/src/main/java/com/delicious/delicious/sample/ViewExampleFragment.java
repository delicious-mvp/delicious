package com.delicious.delicious.sample;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.delicious.delicious.R;
import com.delicious.delicious.base.BaseFragment;
import com.delicious.delicious.listener.OnRecyclerItemClickListener;
import com.delicious.delicious.sample.adapter.ViewExampleRecyclerAdapter;
import com.delicious.delicious.sample.presenter.ViewExampleContract;

import butterknife.BindView;

/**
 * Created by tae-hwan on 6/6/16.
 */
public class ViewExampleFragment extends BaseFragment<ViewExampleContract.Presenter> implements ViewExampleContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ImageView imgBottomSheet;

    private ViewExampleRecyclerAdapter recyclerAdapter;

    private BottomSheetBehavior bottomSheetBehavior;
    private FloatingActionButton floatingActionButton;

    public static ViewExampleFragment newInstance() {
        return new ViewExampleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_view_example, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up floating action button
        floatingActionButton =
                (FloatingActionButton) getActivity().findViewById(R.id.fab);

        imgBottomSheet = (ImageView) getActivity().findViewById(R.id.img_bottom_sheet);

        bottomSheetBehavior = BottomSheetBehavior.from(getActivity().findViewById(R.id.rl_bottom_sheet));
        bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 260.f, getResources().getDisplayMetrics()));
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                getPresenter().onSlide(slideOffset);
            }
        });


        recyclerAdapter = new ViewExampleRecyclerAdapter(getContext());
        recyclerAdapter.setOnRecyclerItemClickListener(position -> getPresenter().onItemClick(position));

        getPresenter().setDataModel(recyclerAdapter);
        recyclerView.setAdapter(recyclerAdapter);

        getPresenter().updateData();
    }

    @Override
    public void refresh() {
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showBottomSheet(@DrawableRes int drawableRes) {
        imgBottomSheet.setImageResource(drawableRes);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @Override
    public void showFloatingActionButton() {
        floatingActionButton.show();
    }

    @Override
    public void hideFloatingActionButton() {
        floatingActionButton.hide();
    }
}
