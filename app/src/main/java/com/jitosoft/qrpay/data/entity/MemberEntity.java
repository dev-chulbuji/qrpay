package com.jitosoft.qrpay.data.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberEntity extends ResultEntity {
    String email;
    String nickname;
}
