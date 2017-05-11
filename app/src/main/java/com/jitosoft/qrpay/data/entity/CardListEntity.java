package com.jitosoft.qrpay.data.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CardListEntity extends ResultEntity {

    List<CardEntity> cardEntities;
}
