package com.delicious.delicious.sample.presenter;

import com.delicious.delicious.Global;
import com.delicious.delicious.R;
import com.delicious.delicious.base.presenter.AbstractPresenter;
import com.delicious.delicious.sample.adapter.model.ViewExampleDataModel;
import com.delicious.delicious.sample.data.SampleItem;

/**
 * Created by tae-hwan on 6/6/16.
 */
public class ViewExamplePresenter extends AbstractPresenter<ViewExampleContract.View> implements ViewExampleContract.Presenter {

    private ViewExampleDataModel dataModel;

    private float tmpBottomSlideOffset;

    public ViewExamplePresenter(ViewExampleContract.View view) {
        super(view);
    }

    @Override
    public void setDataModel(ViewExampleDataModel dataModel) {
        this.dataModel = dataModel;
    }

    @Override
    public void updateData() {
        getView().showProgress();

        Global.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                dataModel.add(new SampleItem(R.drawable.sample_01));
                dataModel.add(new SampleItem(R.drawable.sample_02));
                dataModel.add(new SampleItem(R.drawable.sample_03));
                dataModel.add(new SampleItem(R.drawable.sample_04));
                dataModel.add(new SampleItem(R.drawable.sample_05));

                getView().refresh();
                getView().hideProgress();
            }
        }, 500);
    }

    @Override
    public void onItemClick(int position) {
        SampleItem sampleItem = dataModel.getPhotoItem(position);

        if (sampleItem != null) {
            getView().showBottomSheet(sampleItem.drawableRes);
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void onSlide(float slideOffset) {
        if (slideOffset > 0.8) {
            if (tmpBottomSlideOffset > slideOffset) {
                getView().showFloatingActionButton();
            } else {
                getView().hideFloatingActionButton();
            }

        } else {
            getView().showFloatingActionButton();
        }

        tmpBottomSlideOffset = slideOffset;
    }
}
