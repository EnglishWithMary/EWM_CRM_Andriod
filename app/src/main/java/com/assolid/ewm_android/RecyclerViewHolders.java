package com.assolid.ewm_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.assolid.ewm_android.activities.ArchiveActivity;
import com.assolid.ewm_android.activities.CheckYourselfActivity;
import com.assolid.ewm_android.activities.MainActivity;
import com.assolid.ewm_android.activities.WorkWithCardActivity;
import com.assolid.ewm_android.database.DBHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.prefs.BackingStoreException;
import java.util.prefs.NodeChangeListener;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

import static android.content.Context.MODE_ENABLE_WRITE_AHEAD_LOGGING;
import static android.content.Context.MODE_PRIVATE;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener,View.OnCreateContextMenuListener {

    public TextView engText;
    public TextView ruText;

    public static final int IDM_Edit = 101;
    public static final int IDM_Delete = 102;

    public RecyclerViewHolders(final View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        itemView.setOnCreateContextMenuListener(this);
        engText = (TextView) itemView.findViewById(R.id.engText);
        ruText = (TextView) itemView.findViewById(R.id.ruText);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), ""+getItemId(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(View v) {
        itemView.showContextMenu();
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select The Action");
        menu.add(getPosition()+1, IDM_Edit, Menu.NONE, "Edit");//groupId, itemId, order, title
        menu.add(getPosition()+1, IDM_Delete, Menu.NONE, "Delete");
    }
}