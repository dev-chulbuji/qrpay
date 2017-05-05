package com.jitosoft.qrpay;

import com.jitosoft.qrpay.data.repository.MemberDataRepository;
import com.jitosoft.qrpay.domain.repository.MemberRepository;

/**
 * Created by jihoon on 2017. 5. 1..
 */

public class Injection {

    public static MemberRepository provideMemberRepository() {
        return MemberDataRepository.newInstance();
    }
}
