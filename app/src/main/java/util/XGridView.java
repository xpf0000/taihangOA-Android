package util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by X on 16/9/1.
 */
public class XGridView extends GridView {

    private boolean scrollEnable = true;

    public boolean isScrollEnable() {
        return scrollEnable;
    }

    public void setScrollEnable(boolean scrollEnable) {
        this.scrollEnable = scrollEnable;
    }

    public XGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XGridView(Context context) {
        super(context);
    }

    public XGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if(ev.getAction() == MotionEvent.ACTION_MOVE && !scrollEnable){

            return true;
        }

        return super.dispatchTouchEvent(ev);

    }


}
