package viewpager.example.admin.com.testrecyclerview;

import android.support.v7.widget.RecyclerView;

/**
 * Created by admin
 * date 16/9/23.
 */

public class MyLayoutManager extends RecyclerView.LayoutManager {

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return null;
    }
}
