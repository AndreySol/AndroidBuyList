package com.buylist.solomakha.buylistapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.buylist.solomakha.buylistapp.R;
import com.buylist.solomakha.buylistapp.mvp.presentors.BasketListPresenter;
import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariy on 1/24/2016.
 */
public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder>
{
    private ArrayList<Basket> mItems;
    private LayoutInflater mInflater;
    private BasketListPresenter mPresenter;

    public static final int MENU_CONTEXT_EDIT_ID = 0;
    public static final int MENU_CONTEXT_DELETE_ID = 1;

    public BasketAdapter(Context context, BasketListPresenter presenter)
    {
        mItems = new ArrayList<>();
        mPresenter = presenter;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = mInflater.inflate(R.layout.list_item, null);
        return new ViewHolder(v);
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

        ViewHolder(View itemView)
        {
            super(itemView);
            mTxtItem = (TextView) itemView.findViewById(R.id.listItem);
            mTxtItem.setOnCreateContextMenuListener(this);
            mTxtItem.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mPresenter.openProductsInBasket(mItems.get(getAdapterPosition()).getId());
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
        {
            menu.setHeaderTitle(R.string.context_action);
            menu.add(Menu.NONE, MENU_CONTEXT_EDIT_ID, getAdapterPosition(), R.string.edit_context_item);
            menu.add(Menu.NONE, MENU_CONTEXT_DELETE_ID, getAdapterPosition(), R.string.delete_context_item);
        }
    }
}
