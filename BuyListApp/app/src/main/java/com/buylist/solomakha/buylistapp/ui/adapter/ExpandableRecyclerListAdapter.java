package com.buylist.solomakha.buylistapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.buylist.solomakha.buylistapp.R;
import com.buylist.solomakha.buylistapp.storage.database.entities.Product;
import com.buylist.solomakha.buylistapp.ui.helper.OnSwipeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asolomakha on 3/11/2017.
 */

public class ExpandableRecyclerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnSwipeListener
{
    public static final int HEADER = 0;
    public static final int CHILD = 1;

    private List<Item> mItemList = new ArrayList<>();
    private LayoutInflater mInflater;

    public ExpandableRecyclerListAdapter()
    {
    }

    public void refreshList(List<Item> itemList)
    {
        mItemList.clear();
        mItemList.addAll(itemList);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        mInflater = (LayoutInflater) recyclerView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position)
    {
        return mItemList.get(position).type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType)
        {
            case HEADER:
                viewHolder = new HeaderViewHolder(mInflater.inflate(R.layout.exp_list_group, null));
                break;
            case CHILD:
                viewHolder = new ChildViewHolder(mInflater.inflate(R.layout.exp_list_item, null));
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
    {
        Item item = mItemList.get(position);
        switch (item.type)
        {
            case HEADER:
                final HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                headerViewHolder.mNameTextView.setText(item.categoryTitle);
                setHeaderAsExpandable(headerViewHolder.mNameTextView, headerViewHolder.mImageIcon, headerViewHolder.childList, position);
                break;
            case CHILD:
                ChildViewHolder childViewHolder = (ChildViewHolder) holder;
                childViewHolder.mNameTextView.setText(item.product.getName());
                childViewHolder.mQuantityView.setText(String.valueOf(item.product.getQuantity()));
                break;
        }
    }

    @Override
    public int getItemCount()
    {
        return mItemList.size();
    }

    @Override
    public void onSwipe(RecyclerView.ViewHolder viewHolder, int direction)
    {
        mItemList.remove(viewHolder.getAdapterPosition());
        notifyItemRemoved(viewHolder.getAdapterPosition());
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder
    {
        TextView mNameTextView;
        ImageView mImageIcon;
        List<Item> childList = new ArrayList<>();

        HeaderViewHolder(View itemView)
        {
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.lblListHeader);
            mImageIcon = (ImageView) itemView.findViewById(R.id.image_header_icon);
        }
    }

    private void setHeaderAsExpandable(final TextView view, final ImageView icon, final List<Item> childList, final int position)
    {
        final int itemPosition = position + 1;
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (childList.size() == 0)
                {
                    int count = 0;
                    while (itemPosition <= mItemList.size() - 1 && mItemList.get(itemPosition).type == CHILD)
                    {
                        childList.add(mItemList.remove(itemPosition));
                        count++;
                    }
                    notifyItemRangeRemoved(itemPosition, count);
                    notifyDataSetChanged();
                    icon.setImageResource(R.drawable.circle_plus_exp);
                }
                else
                {
                    int positionToAdd = itemPosition;
                    int count = childList.size();

                    for (Item item : childList)
                    {
                        mItemList.add(positionToAdd, item);
                        positionToAdd++;
                    }
                    childList.clear();
                    notifyItemRangeInserted(itemPosition, count);
                    notifyDataSetChanged();
                    icon.setImageResource(R.drawable.circle_minus_exp);

                }
            }
        });
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder
    {
        TextView mNameTextView;
        TextView mQuantityView;

        ChildViewHolder(View itemView)
        {
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.exp_list_item_name);
            mQuantityView = (TextView) itemView.findViewById(R.id.exp_list_item_quantity);
        }
    }

    public static class Item
    {
        public int type;
        public String categoryTitle;
        public Product product;
    }
}