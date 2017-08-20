package com.example.yangg.zhihu;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar t;
    private BottomSheetBehavior<View> bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(t);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("条目+" + i);
        }
        recyclerView.setAdapter(new myRvAdapter(list));

        /**
         * 找到BottomSheetBehavior
         * 当fab出发缩放的时候让BottomSheetBehaviour触犯显示或者隐藏
         *
         */
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.sheet_layout));
        bottomSheetBehavior.setPeekHeight(0);
        ScaleBehavior scaleBehavior = ScaleBehavior.from(findViewById(R.id.fab));

        scaleBehavior.setOnStateChangeListener(listener);
        FloatingActionButton fab= (FloatingActionButton) findViewById(R.id.fab);
        //fab.
    }
    private ScaleBehavior.OnStateChangeListener listener=new ScaleBehavior.OnStateChangeListener() {
        @Override
        public void onChanged(boolean isShow) {
            bottomSheetBehavior.setState(isShow? BottomSheetBehavior.STATE_EXPANDED:BottomSheetBehavior.STATE_COLLAPSED);
        }
    };

}
