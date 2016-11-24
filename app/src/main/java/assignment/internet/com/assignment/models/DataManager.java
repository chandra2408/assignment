package assignment.internet.com.assignment.models;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import assignment.internet.com.assignment.listener.NetworkReturns;

/**
 * Created by ChandrakanhS on 11/24/2016.
 */
public class DataManager implements Serializable{
    BaseModel ID;
    BaseModel NATIVE_DATE;
    BaseModel ANNOUNCSEMENT_DATE;
    BaseModel EXPIRY;
    BaseModel ANNOUNCEMENT_DESCRIPTION;
    BaseModel ANNOUNCEMENT_TITLE;
    BaseModel ANNOUNCEMENT_IMAGE;
    BaseModel ANNOUNCEMENT_IMAGE_THUMBNAIL;
    BaseModel ANNOUNCEMENT_HTML;
    ArrayList<DataManager> dataList;

    public BaseModel getID() {
        return ID;
    }

    public void setID(BaseModel ID) {
        this.ID = ID;
    }

    public BaseModel getNATIVE_DATE() {
        return NATIVE_DATE;
    }

    public void setNATIVE_DATE(BaseModel NATIVE_DATE) {
        this.NATIVE_DATE = NATIVE_DATE;
    }

    public BaseModel getANNOUNCSEMENT_DATE() {
        return ANNOUNCSEMENT_DATE;
    }

    public void setANNOUNCSEMENT_DATE(BaseModel ANNOUNCSEMENT_DATE) {
        this.ANNOUNCSEMENT_DATE = ANNOUNCSEMENT_DATE;
    }

    public BaseModel getEXPIRY() {
        return EXPIRY;
    }

    public void setEXPIRY(BaseModel EXPIRY) {
        this.EXPIRY = EXPIRY;
    }

    public BaseModel getANNOUNCEMENT_DESCRIPTION() {
        return ANNOUNCEMENT_DESCRIPTION;
    }

    public void setANNOUNCEMENT_DESCRIPTION(BaseModel ANNOUNCEMENT_DESCRIPTION) {
        this.ANNOUNCEMENT_DESCRIPTION = ANNOUNCEMENT_DESCRIPTION;
    }

    public BaseModel getANNOUNCEMENT_TITLE() {
        return ANNOUNCEMENT_TITLE;
    }

    public void setANNOUNCEMENT_TITLE(BaseModel ANNOUNCEMENT_TITLE) {
        this.ANNOUNCEMENT_TITLE = ANNOUNCEMENT_TITLE;
    }

    public BaseModel getANNOUNCEMENT_IMAGE() {
        return ANNOUNCEMENT_IMAGE;
    }

    public void setANNOUNCEMENT_IMAGE(BaseModel ANNOUNCEMENT_IMAGE) {
        this.ANNOUNCEMENT_IMAGE = ANNOUNCEMENT_IMAGE;
    }

    public BaseModel getANNOUNCEMENT_IMAGE_THUMBNAIL() {
        return ANNOUNCEMENT_IMAGE_THUMBNAIL;
    }

    public void setANNOUNCEMENT_IMAGE_THUMBNAIL(BaseModel ANNOUNCEMENT_IMAGE_THUMBNAIL) {
        this.ANNOUNCEMENT_IMAGE_THUMBNAIL = ANNOUNCEMENT_IMAGE_THUMBNAIL;
    }

    public BaseModel getANNOUNCEMENT_HTML() {
        return ANNOUNCEMENT_HTML;
    }

    public void setANNOUNCEMENT_HTML(BaseModel ANNOUNCEMENT_HTML) {
        this.ANNOUNCEMENT_HTML = ANNOUNCEMENT_HTML;
    }

    public ArrayList<DataManager> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<DataManager> dataList) {
        this.dataList = dataList;
    }

    public void loadData(final String url, final NetworkReturns listener){
        new AsyncTask<String,Void,String>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... strings) {
                String response="";
                try {
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet(url);
                    HttpResponse execute = httpClient.execute(httpGet);
                    InputStream content = execute.getEntity().getContent();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return response;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                List<DataManager> parsedDataList=null;
                try{
                    if(!TextUtils.isEmpty(s)){
                        Gson gson = new Gson();
                        DataManager[] dataList = gson.fromJson(s, DataManager[].class);
                        parsedDataList = Arrays.asList(dataList);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(listener!=null){
                    listener.returns(parsedDataList);
                }
            }
        }.execute();
    }

}
