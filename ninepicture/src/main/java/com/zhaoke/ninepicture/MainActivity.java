package com.zhaoke.ninepicture;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.ZoomMediaLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    Myadapter mMyadapter;
    List<UserViewInfo> mList;
    private GridLayoutManager mGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ZoomMediaLoader.getInstance().init(new GlideLoader());
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add(new UserViewInfo("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=1676807226,2726748277&fm=173&app=25&f=JPEG?w=640&h=412&s=5EDDA944C6F17D9E2B37D19A0300909B"));
        }
        mRecyclerView = findViewById(R.id.recyclerView);
        mMyadapter = new Myadapter(R.layout.item, mList, this);
        mGridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mMyadapter);
        mMyadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                computeBoundsBackward(mGridLayoutManager.findFirstVisibleItemPosition());
                GPreviewBuilder.from(MainActivity.this)
                        .setData(mList)
                        .setCurrentIndex(position)
                        .setSingleFling(true)
                        .setType(GPreviewBuilder.IndicatorType.Number)
                        .start();
            }
        });

    }

    /**
     * 查找信息
     * 从第一个完整可见item逆序遍历，如果初始位置为0，则不执行方法内循环
     */
    private void computeBoundsBackward(int firstCompletelyVisiblePos) {
        for (int i = firstCompletelyVisiblePos; i < mList.size(); i++) {
            View itemView = mGridLayoutManager.findViewByPosition(i);
            Rect bounds = new Rect();
            if (itemView != null) {
                ImageView thumbView = (ImageView) itemView.findViewById(R.id.iv);
                thumbView.getGlobalVisibleRect(bounds);
            }
            mList.get(i).setBounds(bounds);
        }
    }
}
