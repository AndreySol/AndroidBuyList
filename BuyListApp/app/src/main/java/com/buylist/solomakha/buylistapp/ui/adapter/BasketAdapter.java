package com.buylist.solomakha.buylistapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.buylist.solomakha.buylistapp.R;
import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;
import com.buylist.solomakha.buylistapp.ui.OnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariy on 1/24/2016.
 */
public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder>
{
    private ArrayList<Basket> mItems;
    private LayoutInflater mInflater;//переводит XML в Object

    private OnClickListener mOnClickListener;

    public BasketAdapter(Context context, List<Basket> list, OnClickListener onClickListener) {
        mItems = (ArrayList<Basket>) list;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mOnClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = mInflater.inflate(R.layout.list_item, null);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        holder.mTxtItem.setText(mItems.get(position).getName());
        holder.mTxtItem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mOnClickListener.onClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTxtItem;

        public ViewHolder(View itemView)
        {
            super(itemView);
            mTxtItem = (TextView) itemView.findViewById(R.id.listItem);
        }
    }
}
