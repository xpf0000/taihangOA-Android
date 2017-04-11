package x.taihangOA.com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import x.taihangOA.com.R;
import x.taihangOA.com.Utils.GlobalVariables;
import x.taihangOA.com.Utils.MyEventBus;

/**
 * Created by admins on 2016/8/11.
 */
public class MineAdapter extends BaseAdapter {
    String[] strings;
    int[] drable;
    Context context;
    int type = 0;

    public MineAdapter(String[] strings, int[] drable, Context context) {
        this.strings = strings;
        this.drable = drable;
        this.context = context;
        EventBus.getDefault().register(this);
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        return strings[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.myset_item_layout, null);
        TextView textView = (TextView) convertView.findViewById(R.id.item);
        TextView bdage = (TextView) convertView.findViewById(R.id.bdage);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.drable);
        textView.setText(strings[position]);
        imageView.setImageResource(drable[position]);
//        BadgeView badge = new BadgeView(context, textView);
//        badge.setTextSize(10);
//        badge.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
//        badge.setBadgeMargin(textView.getWidth() / 5, 0);
//        badge.setText("new");
        if (GlobalVariables.type==0){
            if (GlobalVariables.types) {
                if (position==1){
                    bdage.setVisibility(View.VISIBLE);
                    GlobalVariables.type=1;
                    type=1;
                }
            } else {
                bdage.setVisibility(View.GONE);
                GlobalVariables.type=1;
                type=0;
            }

        }else {
            if (type == 1) {
                if (position==1){
                    bdage.setVisibility(View.VISIBLE);
                }
            } else {

                bdage.setVisibility(View.GONE);

            }
        }

        return convertView;
    }

    @Subscribe
    public void getEventmsg(MyEventBus myEventBus) {
        if (myEventBus.getMsg().equals("show")) {
            type = 1;
        } else {
            type = 0;
        }
        notifyDataSetChanged();
    }

}
