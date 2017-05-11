package com.jitosoft.qrpay.presentation.ui.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jitosoft.qrpay.R;
import com.jitosoft.qrpay.presentation.model.CardDisplayModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>
        implements CardAdapterContract.Model, CardAdapterContract.View {

    List<CardDisplayModel> items;

    public CardAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindView(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void setItems(List<CardDisplayModel> items) {
        this.items = items;
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_number)
        TextView cardNumber;
        @BindView(R.id.card_company)
        TextView cardCompany;
        @BindView(R.id.card_cvc)
        TextView cvc;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }

        public void bindView(CardDisplayModel cardDisplayModel) {
            cardNumber.setText(cardDisplayModel.getCardNumber());
            cardCompany.setText(cardDisplayModel.getCardCompany());
            cvc.setText(cardDisplayModel.getCvc());
        }
    }
}
