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
import com.buylist.solomakha.buylistapp.ui.helper.OnClickListener;

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

    public static final int MENU_CONTEXT_EDIT_ID = 0;
    public static final int MENU_CONTEXT_DELETE_ID = 1;

    public BasketAdapter(Activity parent, OnClickListener onClickListener)
    {
        mItems = new ArrayList<>();
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
            mTxtItem.setOnCreateContextMenuListener(this);
            mTxtItem.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mOnClickListener.OnListItemClickListener(v, getAdapterPosition());
                }
            });

            //mTxtItem.setOnContextClickListener();
            //mTxtItem.set(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
        {
            menu.setHeaderTitle(R.string.context_action);
            menu.add(Menu.NONE, MENU_CONTEXT_EDIT_ID, getAdapterPosition(), R.string.edit_context_item);
            menu.add(Menu.NONE, MENU_CONTEXT_DELETE_ID, getAdapterPosition(), R.string.delete_context_item);
        }

        /*@Override
        public boolean onContextItemSelected(MenuItem item)
        {
            switch (item.getItemId())
            {
                case MENU_CONTEXT_EDIT_ID:
                    showEditBasketDialog(mBasketList.get(item.getOrder()));
                    return true;
                case MENU_CONTEXT_DELETE_ID:
                    mPresenter.deleteBasket(mBasketList.get(item.getOrder()).getId());
                    return true;
                default:
                    return super.onContextItemSelected(item);
            }
        }

        private void showEditBasketDialog(Basket basket)
        {
            BasketDialogFragment basketDialog = new BasketDialogFragment();
            basketDialog.setPresenter(mPresenter);
            basketDialog.setBasket(basket);
            basketDialog.show(getFragmentManager(), "");
        }*/
    }
}
