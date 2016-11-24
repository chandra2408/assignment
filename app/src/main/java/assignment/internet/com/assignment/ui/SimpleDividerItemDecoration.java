package assignment.internet.com.assignment.ui;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import assignment.internet.com.assignment.R;

/**
 * Created by ChandrakanhS on 11/24/2016.
 */
public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration{
    private Drawable mDivider;

    public SimpleDividerItemDecoration(Resources resources) {
        mDivider = ResourcesCompat.getDrawable(resources, R.drawable.line_divider, null);
    }

    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}

