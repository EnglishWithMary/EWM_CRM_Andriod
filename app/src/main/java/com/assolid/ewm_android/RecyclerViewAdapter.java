package com.assolid.ewm_android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assolid.ewm_android.models.Card;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<Card> cardList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<Card> cardList) {
        this.cardList = cardList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_layout, null);
        RecyclerViewHolders recVH = new RecyclerViewHolders(layoutView);
        return recVH;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.engText.setText(cardList.get(position).getEngText());
        holder.ruText.setText(cardList.get(position).getRuText());
    }

    @Override
    public int getItemCount() {
        return this.cardList.size();
    }

    public void clear(){
        cardList.clear();
    }
}