package assignment.internet.com.assignment.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import assignment.internet.com.assignment.R;
import assignment.internet.com.assignment.config.ConfigVolley;
import assignment.internet.com.assignment.listener.OnItemSelected;
import assignment.internet.com.assignment.models.DataManager;

/**
 * Created by ChandrakanhS on 11/24/2016.
 */
public class DetailsItemsListAdapter extends RecyclerView.Adapter<DetailsItemsListAdapter.ItemHolder>{
    List<DataManager> dataList;
    Context mContext;
    OnItemSelected listener;

    public DetailsItemsListAdapter(Context mContext, List<DataManager> dataList, OnItemSelected listener) {
        this.mContext = mContext;
        this.dataList = dataList;
        this.listener = listener;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        final DataManager item = dataList.get(position);
        holder.bind(item, listener);
        holder.tv_title.setText(item.getANNOUNCEMENT_TITLE().getValue());
        holder.iv_category.setImageUrl(item.getANNOUNCEMENT_IMAGE_THUMBNAIL().getValue(), ConfigVolley.getInstance().getImageLoader());
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        NetworkImageView iv_category;
        TextView tv_title;

        public void bind(final DataManager item, final OnItemSelected listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.selected(item);
                }
            });
        }

        public ItemHolder(View itemView) {
            super(itemView);
            iv_category = (NetworkImageView) itemView.findViewById(R.id.iv_category);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public DataManager getItem(int position) {
        return dataList.get(position);
    }
}
