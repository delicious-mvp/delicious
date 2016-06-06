package com.delicious.delicious.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.delicious.delicious.base.presenter.BasePresenter;
import com.delicious.delicious.base.presenter.BaseView;
import com.delicious.delicious.util.ViewUnbindHelper;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chonamdu on 2016. 6. 6..
 */

public class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView<P> {
    private WeakReference<ProgressDialog> loadingDialog;
    private BaseActivity baseActivity;
    private Unbinder unbinder;
    private View mView;

    private P presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        if (activity instanceof BaseActivity) {
            baseActivity = (BaseActivity) activity;
        }
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        mView = view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null)
            unbinder.unbind();
        if(mView != null)
            ViewUnbindHelper.unbindReferences(mView);
    }

    @Override
    public void showProgress(){
        if (loadingDialog == null && baseActivity != null) {
            loadingDialog = new WeakReference<>(ProgressDialog.show(baseActivity, "","네트워크 요청중입니", true));
        }

    }

    @Override
    public void hideProgress(){
        if (loadingDialog != null) {
            loadingDialog.get().dismiss();
            loadingDialog = null;
        }
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    public P getPresenter() {
        return presenter;
    }
}
