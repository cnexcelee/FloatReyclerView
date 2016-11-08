package viewpager.example.admin.com.testrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    List<Integer> list = new ArrayList<Integer>();

    RecyclerView mRecyclerView;
    MyAdapter madapter;

    Random mRandom;

    TextView mTextView;

    int topBarHeight;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 20; i++)
            list.add(i);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mTextView = (TextView) findViewById(R.id.float_textview);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int bean = list.get(position);
                if (bean == 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        madapter = new MyAdapter(this);
        mRecyclerView.setAdapter(madapter);

        mTextView.setText(String.valueOf(list.get(0)));


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                topBarHeight = mTextView.getHeight();
                Log.i("TAG", "onCreate: " + topBarHeight);
                Log.i("TAG", "onScrollStateChanged: " + newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int id = linearLayoutManager.findFirstVisibleItemPosition();


                View view = linearLayoutManager.findViewByPosition(id+1);
                Log.i("TAG", "onScrolled:== " + view.getTop() + " ==" + view.getY());
                if (view.getTop() <= topBarHeight) {
                    mTextView.setY(-(topBarHeight - view.getTop()));
                }else {
                    mTextView.setY(0);
                }
                mTextView.setText("测试" + String.valueOf(list.get(id)));
            }
        });
    }


    class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

        Context mContext;

        public MyAdapter(Context context) {
            mContext = context;
            mRandom = new Random();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
            return new ViewHolder(rootView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mTextView.setText("测试" + position);
            int color = (int) (Math.random() * 10);
//            Log.i("TAG", "onBindViewHolder: " + color);
//            holder.mTextView.setBackgroundColor(getColor(color));
            holder.mTextView.setBackgroundColor(Color.WHITE);
            holder.mColorView.setBackgroundColor(getColor(color));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        private int getColor(int position) {
            int color = Color.RED;
            switch (position) {
                case 1:
                    color = Color.RED;
                    break;
                case 2:
                    color = Color.BLUE;
                    break;
                case 3:
//                    color = Color.BLACK;
//                    break;
                case 4:
                    color = Color.YELLOW;
                    break;
                case 5:
                    color = Color.GRAY;
                    break;
                case 6:
                    color = Color.GREEN;
                    break;
                default:
                    color = Color.MAGENTA;
            }
            return color;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        TextView mColorView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textview);
            mColorView = (TextView) itemView.findViewById(R.id.textcolor);
        }

    }

        private  static final int[] ATTRS = new int[]{
                android.R.attr.listDivider
        };

}
