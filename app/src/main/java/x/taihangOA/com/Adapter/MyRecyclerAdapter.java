package x.taihangOA.com.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import x.taihangOA.com.R;
import x.taihangOA.com.Utils.DateUtils;
import x.taihangOA.com.Utils.ImageUtils;
import x.taihangOA.com.Utils.Timechange;

/**
 * Created by zpp on 2015/11/24 0004.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    ArrayList<HashMap<String, String>> array = new ArrayList<HashMap<String, String>>();
    private Context context;
    private List<Double> heights;
    private OnItemClickListener mListener;
    com.nostra13.universalimageloader.core.ImageLoader ImageLoader;
    DisplayImageOptions options;
    x.taihangOA.com.Utils.ImageUtils ImageUtils;
    ImageLoadingListener animateFirstListener;
    DateUtils dateUtils;
    public MyRecyclerAdapter(Context context, ArrayList<HashMap<String, String>> array) {
        this.context = context;
        this.array = array;
        this.context=context;
        ImageUtils = new ImageUtils();
        ImageLoader = ImageLoader.getInstance();
        ImageLoader.init(ImageLoaderConfiguration.createDefault(context));
        animateFirstListener = new ImageUtils.AnimateFirstDisplayListener();
       dateUtils =new DateUtils();
    }

    private void getRandomHeight(ArrayList<HashMap<String, String>> array) {
        heights = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            double w=Double.parseDouble(array.get(i).get("width"));
            double h=Double.parseDouble(array.get(i).get("height"));
            double z=h/w;
//            heights.add((int) (200 + Math.random() * 400) + 200);
            heights.add(z);
        }
    }

    public interface OnItemClickListener {
        void ItemClickListener(View view, int postion);

        void ItemLongClickListener(View view, int postion);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hot_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        WindowManager wm = (WindowManager) context.
                getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
//        getRandomHeight(this.array);
        double w=Double.parseDouble(array.get(position).get("width"));
        double h=Double.parseDouble(array.get(position).get("height"));
        double z=h/w;
//        if (heights.size() == 0) {
//        } else {
            RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(params.width,(int)((width/2)*z));
            holder.photo.setLayoutParams(layoutParams);
//        }
        Timechange timechange=new Timechange();
       String time= timechange.Time(dateUtils.getDateToStringss(Long.parseLong(array.get(position).get("create_time"))));
        holder.mTv.setText(array.get(position).get("content"));
        holder.name.setText(array.get(position).get("nickname"));
        holder.time.setText(time);
        options=ImageUtils.setnoOptions();
        String url=array.get(position).get("url");
        ImageLoader.displayImage(url, holder.photo, options,
                animateFirstListener);

        if (mListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mListener.ItemClickListener(holder.itemView, pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mListener.ItemLongClickListener(holder.itemView, pos);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return array.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView mTv, name, time;
    ImageView photo;
    public MyViewHolder(View itemView) {
        super(itemView);
        mTv = (TextView) itemView.findViewById(R.id.content);
        name = (TextView) itemView.findViewById(R.id.name);
        time = (TextView) itemView.findViewById(R.id.time);
        photo=(ImageView)itemView.findViewById(R.id.photo);
    }
}