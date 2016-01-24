package com.buylist.solomakha.buylistapp.ui.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.buylist.solomakha.buylistapp.R;

/**
 * Created by mariy on 1/27/2016.
 */
public class Basket_Dialog extends DialogFragment {

    DialogInterface.OnClickListener listner;

    public void set_listener(DialogInterface.OnClickListener l) {
        listner = l;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Create list");
        builder.setView(R.layout.create_list_dialog);

        builder.setPositiveButton(android.R.string.ok, listner);

        builder.setNegativeButton(android.R.string.cancel, null);

        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);

    }
}
