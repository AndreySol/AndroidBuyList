package com.buylist.solomakha.buylistapp.ui.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.buylist.solomakha.buylistapp.R;
import com.buylist.solomakha.buylistapp.storage.database.entities.Product;

/**
 * Created by asolomakha on 1/4/2016.
 */

public class ExpandableProductsAdapter extends BaseExpandableListAdapter
{

    private Context context;
    private List<String> listDataHeader;
    private HashMap<String, List<Product>> listDataChild;

    public ExpandableProductsAdapter(Context context)
    {
        this.context = context;
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
    }

    public void setValues(List<String> listDataHeader, HashMap<String, List<Product>> listChildData)
    {
        this.listDataChild = listChildData;
        this.listDataHeader = listDataHeader;
        notifyDataSetChanged();
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon)
    {
        return listDataChild.get(listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        return listDataChild.get(listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount()
    {
        return listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.exp_list_group, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        final Product product = (Product) getChild(groupPosition, childPosition);

        if (convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.exp_list_item, null);
        }

        ImageView expListItemImage = (ImageView) convertView.findViewById(R.id.exp_list_item_image);
        TextView expListItemName = (TextView) convertView.findViewById(R.id.exp_list_item_name);
        TextView expListItemQuantity = (TextView) convertView.findViewById(R.id.exp_list_item_quantity);

        if (product.isBought()) {
            expListItemImage.setImageResource(R.drawable.plus);
        } else {
            expListItemImage.setImageResource(R.drawable.minus);
        }

        expListItemName.setText(product.getName());
        expListItemQuantity.setText(String.valueOf(product.getQuantity()) + " " + product.getUnit().getName());
        return convertView;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }
}