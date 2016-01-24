package com.buylist.solomakha.buylistapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.buylist.solomakha.buylistapp.R;
import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariy on 1/24/2016.
 */
public class EntityAdapter extends BaseAdapter {
    ArrayList<Basket> items;
    LayoutInflater lInflater;//переводит XML  в Object

    public EntityAdapter(ArrayList<Basket> list, Context context) {
        items = new ArrayList<Basket>(list);
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        if (position < items.size() && position >= 0) {
            return items.get(position);
        } else throw new RuntimeException(); //return null;

    }

    @Override
    public long getItemId(int position) {
        if (position < items.size() && position >= 0) {
            return items.get(position).getId();
        } else throw new RuntimeException(); //return null;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtItem = (TextView)view.findViewById(R.id.listItem);
            view.setTag(viewHolder);
        }
        else   viewHolder =(ViewHolder)view.getTag();

        Basket item = (Basket) getItem(position);
        viewHolder.txtItem.setText(item.getName());
        return view;
    }

    public void refresh(List<Basket> productsListList) {
        items.clear();
        items.addAll(productsListList);
        notifyDataSetChanged();
    }


    static class ViewHolder {
        TextView txtItem;
    }
}
