package com.jitosoft.qrpay.presentation.ui.main.adapter;

import com.jitosoft.qrpay.presentation.model.CardDisplayModel;

import java.util.List;

public interface CardAdapterContract {

    interface View {
        void refresh();
    }

    interface Model {
        void setItems(List<CardDisplayModel> items);

        void addAll(List<CardDisplayModel> items);
    }

}
