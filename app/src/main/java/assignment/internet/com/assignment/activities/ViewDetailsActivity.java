package assignment.internet.com.assignment.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;

import assignment.internet.com.assignment.R;
import assignment.internet.com.assignment.models.DataManager;

/**
 * Created by ChandrakanhS on 11/24/2016.
 */
public class ViewDetailsActivity extends AppCompatActivity{
    WebView webview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webview = (WebView) findViewById(R.id.myWebView);
        DataManager item = (DataManager) getIntent().getExtras().getSerializable("ITEM");
        webview.loadDataWithBaseURL("", item.getANNOUNCEMENT_HTML().getValue(), "text/html", "UTF-8", "");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
