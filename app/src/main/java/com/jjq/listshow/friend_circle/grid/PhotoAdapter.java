package com.jjq.listshow.friend_circle.grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.jjq.listshow.R;
import com.jjq.listshow.friend_circle.model.SaidPictureVO;

import java.util.List;

/**
 * Created by jiangjieqiang on 2017/7/24.
 */

public class PhotoAdapter extends BaseAdapter{

    private Context context;
    private List<SaidPictureVO> saidPictureVOList;
    private int columnWidth;

    public PhotoAdapter(Context context, List<SaidPictureVO> saidPictureVOList, int columnWidth) {
        this.context = context;
        this.saidPictureVOList = saidPictureVOList;
        this.columnWidth = columnWidth;
    }

    @Override
    public int getCount() {
        return saidPictureVOList.size();
    }

    @Override
    public Object getItem(int position) {
        return saidPictureVOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_friend_gridview_image_item, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.image_fiend_gridview_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
        params.width = columnWidth;
        params.height = columnWidth;
        holder.imageView.setLayoutParams(params);
        holder.imageView.setImageResource(saidPictureVOList.get(position).getLocalImg());
        return convertView;
    }

    class ViewHolder {
        private ImageView imageView;
    }
}
