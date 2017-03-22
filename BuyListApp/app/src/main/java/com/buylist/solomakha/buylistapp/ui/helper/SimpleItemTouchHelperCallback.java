package com.buylist.solomakha.buylistapp.ui.helper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.buylist.solomakha.buylistapp.ui.adapter.ExpandableRecyclerListAdapter;

import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_SWIPE;

/**
 * Created by asolomakha on 3/16/2017.
 */

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback
{
    OnSwipeListener mSwipeListener;

    public SimpleItemTouchHelperCallback(OnSwipeListener swipeListener)
    {
        this.mSwipeListener = swipeListener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)
    {
        if (!(viewHolder instanceof ExpandableRecyclerListAdapter.ChildViewHolder))
        {
            return 0;
        }
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeFlag(ACTION_STATE_SWIPE, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target)
    {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
    {
        mSwipeListener.onSwipe(viewHolder, direction);
    }

}
