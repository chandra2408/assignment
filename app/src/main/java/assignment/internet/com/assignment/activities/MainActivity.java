package assignment.internet.com.assignment.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import assignment.internet.com.assignment.R;
import assignment.internet.com.assignment.adapters.DetailsItemsListAdapter;
import assignment.internet.com.assignment.config.ConfigVolley;
import assignment.internet.com.assignment.listener.NetworkReturns;
import assignment.internet.com.assignment.listener.OnItemSelected;
import assignment.internet.com.assignment.models.DataManager;
import assignment.internet.com.assignment.ui.SimpleDividerItemDecoration;

/**
 * Created by ChandrakanhS on 11/24/2016.
 */
public class MainActivity extends AppCompatActivity implements OnItemSelected{
    RecyclerView rcv_list;
    ProgressDialog dialog;
    List<DataManager> dataLists;
    DetailsItemsListAdapter adapter;
	DetailsItemsListAdapter abcadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConfigVolley.init(getApplicationContext());
        rcv_list = (RecyclerView) findViewById(R.id.rcv_list);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(rcv_list.getContext());
        rcv_list.setLayoutManager(mLayoutManager);
        rcv_list.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
    }

    @Override
    public void selected(DataManager item) {
        Intent i = new Intent(MainActivity.this,ViewDetailsActivity.class);
        Bundle data = new Bundle();
        data.putSerializable("ITEM",item);
        i.putExtras(data);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(dataLists!=null && dataLists.size()>0){
            refreshAdapter();
        }else{
            showProgress();
            String url = "http://94.56.199.34/EMC/IPDP/ipdpb.ashx?TemplateName=Promotions_ipad.htm&p=Common.Announcements&Handler=News&AppName=EMC&Type=News&F=J";
            new DataManager().loadData(url, new NetworkReturns() {
                @Override
                public void returns(List<DataManager> dataList) {
                    closeProgress();
                    dataLists = dataList;
                    if(dataLists!=null && dataLists.size()>0){
                        refreshAdapter();
                    }
                }
            });
        }
    }

    void refreshAdapter(){
        adapter = new DetailsItemsListAdapter(MainActivity.this,dataLists,MainActivity.this);
        rcv_list.setAdapter(adapter);
    }

    void showProgress(){
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    void closeProgress(){
        if(dialog!=null){
            dialog.cancel();
        }
    }
}
