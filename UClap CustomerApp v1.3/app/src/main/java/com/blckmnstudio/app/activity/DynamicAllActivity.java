package com.blckmnstudio.app.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blckmnstudio.app.R;
import com.blckmnstudio.app.adepter.DynamicAdapter;
import com.blckmnstudio.app.model.ServiceDataItem;
import com.blckmnstudio.app.utils.SessionManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DynamicAllActivity extends BasicActivity implements DynamicAdapter.RecyclerTouchListener {

    @BindView(R.id.recycler_service)
    RecyclerView recyclerService;
    ArrayList<ServiceDataItem> subcatDataItems=new ArrayList<>();
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_all);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager=new SessionManager(this);
        getSupportActionBar().setTitle("View All");
        subcatDataItems = getIntent().getParcelableArrayListExtra("ListExtra");

        DynamicAdapter itemAdp = new DynamicAdapter( subcatDataItems ,this, "viewall");
        recyclerService.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerService.setAdapter(itemAdp);
    }


    @Override
    public void onClickDynamicItem(ServiceDataItem dataItem, int position) {
        sessionManager.setStringData("pid","0");
        startActivity(new Intent(this, SubCategoryActivity.class)
                .putExtra("vurl", dataItem.getVideo())
                .putExtra("name", dataItem.getCatTitle())
                .putExtra("named", dataItem.getCatSubtitle())
                .putExtra("cid", dataItem.getCatId())
                .putExtra("sid", "0"));
    }
}