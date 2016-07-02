package com.delicious.delicious.base;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.delicious.delicious.R;

import butterknife.ButterKnife;

/**
 * Created by tae-hwan on 6/5/16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        setUpToolbar();
    }
    public void setUpToolbar(){
        toolbar = ButterKnife.findById(this, R.id.toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }
    }
}
