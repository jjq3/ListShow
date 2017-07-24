package com.jjq.listshow.friend_circle.grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jjq.listshow.R;
import com.jjq.listshow.friend_circle.model.SaidVO;

import java.util.List;

/**
 * Created by jiangjieqiang on 2016/10/26.
 */

public class FriendAdapter extends BaseAdapter {

    private Context context;
    private List<SaidVO> saidVOList;

    public FriendAdapter(Context context, List<SaidVO> saidVOList) {
        this.context = context;
        this.saidVOList = saidVOList;
    }

    @Override
    public int getCount() {
        return saidVOList.size();
    }

    @Override
    public Object getItem(int position) {
        return saidVOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_friend_gridview_item, null);
            holder = new ViewHolder();
            holder.sayTextView = (TextView) convertView.findViewById(R.id.text_friend_item);
            holder.gridView = (FriendGridView) convertView.findViewById(R.id.gridview_friend_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.sayTextView.setText(saidVOList.get(position).getContent());
        holder.gridView.setPhotoAdapter(saidVOList.get(position).getSaidPictureVOs());

        return convertView;
    }

    class ViewHolder {
        private TextView sayTextView;
        private FriendGridView gridView;
    }
}
