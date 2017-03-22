package com.buylist.solomakha.buylistapp.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.buylist.solomakha.buylistapp.R;
import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;
import com.buylist.solomakha.buylistapp.ui.MainActivity;
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
    private Activity mParent;

    public BasketAdapter(Activity parent, List<Basket> list, OnClickListener onClickListener)
    {
        mItems = (ArrayList<Basket>) list;
        mInflater = (LayoutInflater) parent.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mOnClickListener = onClickListener;
        mParent = parent;
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
    }

    @Override
    public int getItemCount()
    {
        return mItems.size();
    }

    public void refresh(List<Basket> baskets)
    {
        mItems.clear();
        mItems.addAll(baskets);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener
    {
        TextView mTxtItem;

        public ViewHolder(View itemView)
        {
            super(itemView);
            mTxtItem = (TextView) itemView.findViewById(R.id.listItem);


            mTxtItem.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mOnClickListener.onClick(v, getAdapterPosition());
                }
            });
            mParent.registerForContextMenu(mTxtItem);
            mTxtItem.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
        {
            menu.setHeaderTitle("Select The Action");
            menu.add(Menu.NONE, MainActivity.MENU_CONTEXT_EDIT_ID, getAdapterPosition(), "Edit");
            menu.add(Menu.NONE, MainActivity.MENU_CONTEXT_DELETE_ID, getAdapterPosition(), "Delete");
        }
    }
}
