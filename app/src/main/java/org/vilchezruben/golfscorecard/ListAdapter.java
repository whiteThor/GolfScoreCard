package org.vilchezruben.golfscorecard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Vilchez Ruben on 4/04/2018.
 */

public class ListAdapter extends BaseAdapter {
    private final Context mContext;
    private final Hole[] mHoles;

    public ListAdapter(Context context, Hole[] holes) {
        mContext = context;
        mHoles = holes;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return mHoles.length;
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return mHoles[position];
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder ;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.holeLable = convertView.findViewById(R.id.holeLabel);
            viewHolder.strokeCount = convertView.findViewById(R.id.strokeCount);
            viewHolder.addStrokeButton = convertView.findViewById(R.id.addStrokeButton);
            viewHolder.removeStrokeButton = convertView.findViewById(R.id.removeStrokeButton);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.holeLable.setText(mHoles[position].getLabel());
        viewHolder.strokeCount.setText(mHoles[position].getStrokeCount() +"");

        viewHolder.addStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int updatedStrokeCount = mHoles[position].getStrokeCount() + 1;
                mHoles[position].setStrokeCount(updatedStrokeCount);
                viewHolder.strokeCount.setText(updatedStrokeCount +"");
            }
        });
        viewHolder.removeStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int updatedStrokeCount = mHoles[position].getStrokeCount() - 1;
                if (updatedStrokeCount <0) updatedStrokeCount=0;
                mHoles[position].setStrokeCount(updatedStrokeCount);
                viewHolder.strokeCount.setText(updatedStrokeCount +"");
            }
        });
        return convertView;
    }

    public static class ViewHolder{
        private TextView holeLable;
        private TextView strokeCount;
        private Button removeStrokeButton;
        private Button addStrokeButton;

    }
}
