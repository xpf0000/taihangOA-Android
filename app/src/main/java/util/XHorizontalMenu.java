package util;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

import x.taihangOA.com.MyAppService.LocationApplication;
import x.taihangOA.com.R;

/**
 * Created by X on 16/9/2.
 */
public class XHorizontalMenu extends RecyclerView {

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }

    private XHorizontalAdapter adapter;
    private Context context;

    private int selected = 0;
    private int normalColor = 0;
    private int selectedColor = 0;
    private int lineHeight = 3;
    private int normalTxtSize = 16;
    private int selectedTxtSize = 16;
    private int cellInterval = 12;
    private int onePageNum = 0;
    private int txtHPadding = -1;
    private int lineHMaigin = -1;

    public int getLineHMaigin() {
        return lineHMaigin;
    }

    public XHorizontalMenu setLineHMaigin(int lineHMaigin) {
        this.lineHMaigin = lineHMaigin;
        adapter.notifyDataSetChanged();
        return this;
    }

    public int getTxtHPadding() {
        return txtHPadding;
    }

    public XHorizontalMenu setTxtHPadding(int txtHPadding) {
        this.txtHPadding = txtHPadding;
        adapter.notifyDataSetChanged();
        return this;
    }

    public int getOnePageNum() {
        return onePageNum;
    }

    public XHorizontalMenu setOnePageNum(int onePageNum) {
        this.onePageNum = onePageNum;
        adapter.notifyDataSetChanged();
        return this;
    }

    private XHorizontalMain main;

    public XHorizontalMain getMain() {
        return main;
    }

    public void setMain(XHorizontalMain main) {

        this.main = main;
        if(this.main.getMenu() != this)
        {
            this.main.setMenu(this);
        }

        this.main.refresh();

    }

    public int getCellInterval() {
        return cellInterval;
    }

    public XHorizontalMenu setCellInterval(int cellInterval) {
        this.cellInterval = cellInterval;
        adapter.notifyDataSetChanged();
        return this;
    }

    public int getNormalColor() {
        return normalColor;
    }

    public XHorizontalMenu setNormalColor(int normalColor) {
        this.normalColor = ContextCompat.getColor(context, normalColor);
        adapter.notifyDataSetChanged();
        return this;
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public XHorizontalMenu setSelectedColor(int selectedColor) {
        this.selectedColor = ContextCompat.getColor(context, selectedColor);
        adapter.notifyDataSetChanged();
        return this;
    }

    public int getLineHeight() {
        return lineHeight;
    }

    public XHorizontalMenu setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
        adapter.notifyDataSetChanged();
        return this;
    }

    public int getNormalTxtSize() {
        return normalTxtSize;
    }

    public XHorizontalMenu setNormalTxtSize(int normalTxtSize) {
        this.normalTxtSize = normalTxtSize;
        adapter.notifyDataSetChanged();
        return this;
    }

    public int getSelectedTxtSize() {
        return selectedTxtSize;
    }

    public XHorizontalMenu setSelectedTxtSize(int selectedTxtSize) {
        this.selectedTxtSize = selectedTxtSize;
        adapter.notifyDataSetChanged();
        return this;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
        adapter.notifyDataSetChanged();
        if(main != null)
        {
            main.setCurrentItem(selected);
        }

        View child = null;
        for(int i=0;i<getChildCount();i++)
        {
            int tag = (int) this.getChildAt(i).getTag();

            if(tag == selected)
            {
                child = this.getChildAt(i);
                break;
            }

        }

        int[] l = new int[2];
        getLocationOnScreen(l);

        int middle = (getWidth()+l[0])/2;

        if(child != null)
        {
            int[] location = new int[2];

            child.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];


            int xx = x+child.getWidth()/2;
            int offx = middle - xx;

            scrollBy(-offx,0);

        }




    ;



    }

    public  void init(final Context context)
    {
        this.context = context;

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        setLayoutManager(linearLayoutManager);

        normalColor = ContextCompat.getColor(context, R.color.APPTXTBlack);
        selectedColor = ContextCompat.getColor(context, R.color.APPBlue);

        adapter = new XHorizontalAdapter(context);

        adapter.setOnItemClickLitener(new OnItemClickLitener()
        {
            @Override
            public void onItemClick(View view, int position)
            {

                System.out.println("child00: "+view);
                setSelected(position);

            }
        });

        setAdapter(adapter);

        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

               // System.out.println("offx: "+getScrollX());

            }
        });


    }

    public XHorizontalMenu(Context context) {
        super(context);
        init(context);
    }

    public XHorizontalMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public XHorizontalMenu(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        adapter.mDatas.clear();
        adapter.mDatas = null;
        adapter = null;

    }

    public void setData(List<XHorizontalModel> arr)
    {
        adapter.setmDatas(arr);
    }

    public List<XHorizontalModel> getData(){
        return adapter.getmDatas();
    }


    static public class XHorizontalModel
    {
        private String id;
        private String title;
        private Fragment view;

        public  XHorizontalModel(){}

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Fragment getView() {
            return view;
        }

        public void setView(Fragment view) {
            this.view = view;
        }
    }

    /**
     * 定义ListView适配器MainListViewAdapter
     */
    private class XHorizontalAdapter extends RecyclerView.Adapter {

        private OnItemClickLitener mOnItemClickLitener;

        public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
        {
            this.mOnItemClickLitener = mOnItemClickLitener;
        }


        private  class ViewHolder extends RecyclerView.ViewHolder
        {
            public ViewHolder(View arg0)
            {
                super(arg0);
            }

            ImageView mLine;
            TextView mTxt;
            LinearLayout layout;
        }


        private LayoutInflater mInflater;
        private List<XHorizontalModel> mDatas;

        public List<XHorizontalModel> getmDatas() {
            return mDatas;
        }

        public void setmDatas(List<XHorizontalModel> mDatas) {
            this.mDatas = mDatas;
            notifyDataSetChanged();
            main.setOffscreenPageLimit(mDatas.size());
            main.refresh();
        }

        public XHorizontalAdapter(Context context)
        {
            mInflater = LayoutInflater.from(context);
        }

        public XHorizontalAdapter(Context context, List<XHorizontalModel> datats)
        {
            mInflater = LayoutInflater.from(context);
            mDatas = datats;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = mInflater.inflate(R.layout.xhorizontalcell,
                    parent, false);
            ViewHolder viewHolder = new ViewHolder(view);

            viewHolder.mTxt = (TextView) view
                    .findViewById(R.id.XHorizontalCellTitle);

            viewHolder.mLine = (ImageView) view
                    .findViewById(R.id.XHorizontalCellLine);

            viewHolder.layout = (LinearLayout) view
                    .findViewById(R.id.XHorizontalCellLayout);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int i) {

            ViewHolder viewHolder = ((ViewHolder)holder);

            viewHolder.mTxt.setText(mDatas.get(i).title);

            ViewGroup.LayoutParams layoutParams1 = viewHolder.mLine.getLayoutParams();
            layoutParams1.height = DensityUtil.dip2px(context,lineHeight);
            viewHolder.mLine.setLayoutParams(layoutParams1);

            if(onePageNum > 0)
            {
                int w = LocationApplication.SW / onePageNum;
                ViewGroup.LayoutParams layoutParams = viewHolder.layout.getLayoutParams();
                layoutParams.width = w;
                viewHolder.layout.setLayoutParams(layoutParams);
            }
            else
            {
                int pd = DensityUtil.dip2px(context,cellInterval);
                viewHolder.layout.setPadding(pd,0,pd,0);
            }

            if(txtHPadding >= 0)
            {
                int pd = DensityUtil.dip2px(context,txtHPadding);
                viewHolder.mTxt.setPadding(pd,0,pd,0);
            }

            if(lineHMaigin >= 0)
            {
                int pd = DensityUtil.dip2px(context,lineHMaigin);
                ViewGroup.MarginLayoutParams layout = (ViewGroup.MarginLayoutParams)viewHolder.mLine.getLayoutParams();
                layout.setMargins(pd,0,pd,0);
                viewHolder.mLine.requestLayout();
            }




            viewHolder.layout.setTag(i);

            if(i == selected)
            {
                viewHolder.mTxt.setTextColor(selectedColor);
                viewHolder.mTxt.setTextSize(selectedTxtSize);
                viewHolder.mLine.setBackgroundColor(selectedColor);
                viewHolder.mLine.setVisibility(View.VISIBLE);
            }
            else
            {
                viewHolder.mTxt.setTextColor(normalColor);
                viewHolder.mTxt.setTextSize(normalTxtSize);
                viewHolder.mLine.setVisibility(View.INVISIBLE);
            }


            //System.out.println("item width: "+holder.itemView.getWidth());

            //如果设置了回调，则设置点击事件
            if (mOnItemClickLitener != null)
            {
                holder.itemView.setOnClickListener(new OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        mOnItemClickLitener.onItemClick(holder.itemView, i);
                    }
                });

            }


        }

        /**
         * 返回item的id
         */
        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

    }

}
