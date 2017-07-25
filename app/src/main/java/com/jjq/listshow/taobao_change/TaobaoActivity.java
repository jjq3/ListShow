package com.jjq.listshow.taobao_change;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjq.listshow.R;

import java.util.ArrayList;

/**
 * Created by jiangjieqiang on 2017/7/24.
 */

public class TaobaoActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private Button button;

    private ArrayList<ShopVO> shopVOList = new ArrayList<>();
    private boolean isGrid = false;
    private StaggeredGridLayoutManager layoutManager;

    private MyRecyclerAdapter myRecyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taobao);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_taobao);
        button = (Button) findViewById(R.id.button_taobao);

        initDatas();

        initRecyclerView();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyTypeChanged(!isGrid);
            }
        });

    }

    /**
     * 初始化数据
     */
    private void initDatas(){
        for (int i = 0; i < 20; i++) {
            ShopVO shopVO = new ShopVO();
            shopVO.setLocalImageId(R.mipmap.ic_launcher);
            shopVO.setTitle("title" + i);
            shopVO.setContent("content" + i);
            shopVOList.add(shopVO);
        }
    }

    private void initRecyclerView(){
        layoutManager = new StaggeredGridLayoutManager(
                isGrid ? 2 : 1,
                StaggeredGridLayoutManager.VERTICAL) {
            @Override
            public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
                super.smoothScrollToPosition(recyclerView, state, position);
                //Create your RecyclerView.SmoothScroller instance? Check.
                LinearSmoothScroller smoothScroller =
                        new LinearSmoothScroller(TaobaoActivity.this) {

                            @Override
                            protected void onStop() {
                                super.onStop();
                            }

                            @Override
                            public PointF computeScrollVectorForPosition(int targetPosition) {
                                return null;
                            }
                        };

                //Docs do not tell us anything about this,
                //but we need to set the position we want to scroll to.
                smoothScroller.setTargetPosition(position);
                //Call startSmoothScroll(SmoothScroller)? Check.
                startSmoothScroll(smoothScroller);
            }
        };
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST,
//                R.drawable.devide_line_gray, 2 * getActivity().getResources().getDimension(R.dimen.eight_dip)));

        if (myRecyclerAdapter == null) {
            myRecyclerAdapter = new MyRecyclerAdapter();
        }
        recyclerView.setAdapter(myRecyclerAdapter);
        myRecyclerAdapter.initData(false);
        myRecyclerAdapter.appendData(shopVOList);
        myRecyclerAdapter.notifyDataSetChanged();
        recyclerView.stopScroll();
        recyclerView.smoothScrollToPosition(0);
    }

    private class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final int TYPE_HEADER = 0x1000;
        private final int TYPE_NORMAL = 0x2000;
        private final int TYPE_FOOTER = 0x3000;
        private final int TYPE_EMPTY = 0x4000;
        private final int TYPE_NORMAL_LIST = 0x5000;
        private final int TYPE_NORMAL_GRID = 0x6000;
        private ArrayList<MyItemInfo> itemInfos;
        private boolean needFooter = false;
        private boolean hasFooter = false;

        public class MyItemInfo {
            int type;
            ShopVO shopVO;

            public MyItemInfo(int type, ShopVO shopVO) {
                this.type = type;
                this.shopVO = shopVO;
            }
        }

        public MyRecyclerAdapter() {
            itemInfos = new ArrayList<>();
        }

        public void initData(boolean needFooter) {
            this.needFooter = needFooter;
            this.hasFooter = false;
            int oldCount = itemInfos.size();
            itemInfos.clear();
            this.notifyItemRangeRemoved(0, oldCount);
        }

        public void appendData(ArrayList<ShopVO> shopVOs) {
            int oldCount = itemInfos.size();
            if (hasFooter) {
                itemInfos.remove(oldCount - 1);
                this.notifyItemRemoved(oldCount - 1);
                oldCount--;
            }
            int size = shopVOs.size();
            for (int i = 0; i < size; i++)
                itemInfos.add(new MyItemInfo(TYPE_NORMAL, shopVOs.get(i)));
            this.notifyItemRangeInserted(oldCount + 1, size);
            if (needFooter) {
                itemInfos.add(new MyItemInfo(TYPE_FOOTER, null));
                this.notifyItemInserted(itemInfos.size() - 1);
                hasFooter = true;
            }
        }

        public void removeFooter() {
            int oldCount = itemInfos.size();
            itemInfos.remove(oldCount - 1);
            notifyItemRemoved(oldCount - 1);
        }

        public void appendEmptyView() {
            int oldCount = itemInfos.size();
            if (hasFooter) {
                itemInfos.remove(oldCount - 1);
                this.notifyItemRemoved(oldCount - 1);
                oldCount--;
            }
            itemInfos.add(new MyItemInfo(TYPE_EMPTY, null));
            notifyItemRangeInserted(oldCount, 1);
        }

        @Override
        public int getItemViewType(int position) {
            switch (itemInfos.get(position).type){
                case TYPE_NORMAL:
                    return isGrid ? TYPE_NORMAL_GRID : TYPE_NORMAL_LIST;
                default:
                    return itemInfos.get(position).type;
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = null;
            switch (viewType) {
                case TYPE_HEADER:
                    return null;
                case TYPE_NORMAL_LIST:
                    view = inflater.inflate(R.layout.layout_taobao_item_list, parent, false);
                    return new NormalListViewHolder(view);
                case TYPE_NORMAL_GRID:
                    view = inflater.inflate(R.layout.layout_taobao_item_grid, parent, false);
                    return new NormalGridViewHolder(view);
                case TYPE_EMPTY:
                    return null;
                case TYPE_FOOTER:
                    return null;
                default:
                    return null;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            switch (viewHolder.getItemViewType()) {
                case TYPE_NORMAL_LIST:
                    NormalListViewHolder normalListViewHolder = (NormalListViewHolder) viewHolder;
                    normalListViewHolder.setContent(itemInfos.get(i).shopVO);
                    break;
                case TYPE_NORMAL_GRID:
                    NormalGridViewHolder normalGridViewHolder = (NormalGridViewHolder) viewHolder;
                    normalGridViewHolder.setContent(itemInfos.get(i).shopVO);
                case TYPE_HEADER:
                    break;
                case TYPE_FOOTER:
                    break;
                case TYPE_EMPTY:
                    break;
                default:
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return itemInfos.size();
        }

        private class EmptyItemHolder extends RecyclerView.ViewHolder {
            public EmptyItemHolder(View itemView) {
                super(itemView);
            }
        }

        private class NormalListViewHolder extends RecyclerView.ViewHolder {
            private ShopVO shopVO;
            private ImageView imageView;
            private TextView titleView;
            private TextView contentView;

            public NormalListViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.image_taobao_item_list);
                titleView = (TextView) itemView.findViewById(R.id.text_taobao_item_list_title);
                contentView = (TextView) itemView.findViewById(R.id.text_taobao_item_list_content);
                itemView.setOnClickListener(new OnItemClickListener());
            }

            public void setContent(ShopVO shopVO) {
                this.shopVO = shopVO;
                imageView.setImageResource(shopVO.getLocalImageId());
                titleView.setText(shopVO.getTitle());
                contentView.setText(shopVO.getContent());
            }

            private class OnItemClickListener implements View.OnClickListener {
                @Override
                public void onClick(View v) {

                }
            }
        }

        private class NormalGridViewHolder extends RecyclerView.ViewHolder {
            private ShopVO shopVO;
            private ImageView imageView;
            private TextView titleView;
            private TextView contentView;

            public NormalGridViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(new OnItemClickListener());
                imageView = (ImageView) itemView.findViewById(R.id.image_taobao_item_grid);
                titleView = (TextView) itemView.findViewById(R.id.text_taobao_item_grid_title);
                contentView = (TextView) itemView.findViewById(R.id.text_taobao_item_grid_content);
            }

            public void setContent(ShopVO shopVO) {
                this.shopVO = shopVO;
                imageView.setImageResource(shopVO.getLocalImageId());
                titleView.setText(shopVO.getTitle());
                contentView.setText(shopVO.getContent());
            }

            private class OnItemClickListener implements View.OnClickListener {
                @Override
                public void onClick(View v) {

                }
            }
        }
    }

    /**
     * 修改网格布局和列表布局
     *
     * @param isGrid
     */
    public void notifyTypeChanged(boolean isGrid) {
        this.isGrid = isGrid;
        layoutManager.setSpanCount(isGrid ? 2 : 1);
        myRecyclerAdapter.notifyDataSetChanged();
    }

}
