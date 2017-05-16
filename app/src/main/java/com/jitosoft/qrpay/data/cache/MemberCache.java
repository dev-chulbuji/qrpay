package com.jitosoft.qrpay.data.cache;

import com.jitosoft.qrpay.data.entity.MemberEntity;

public interface MemberCache {

    void setMemberEntity(MemberEntity memberEntity);

    MemberEntity getMemberEntity();
}
