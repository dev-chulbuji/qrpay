package com.jitosoft.qrpay;

import com.jitosoft.qrpay.data.cache.MemberCache;
import com.jitosoft.qrpay.data.cache.MemberCacheImpl;
import com.jitosoft.qrpay.data.datasource.MemberDataSourceFactory;
import com.jitosoft.qrpay.data.repository.CardDataRepository;
import com.jitosoft.qrpay.data.repository.MemberDataRepository;
import com.jitosoft.qrpay.domain.repository.CardRepository;
import com.jitosoft.qrpay.domain.repository.MemberRepository;

/**
 * Created by jihoon on 2017. 5. 1..
 */

public class Injection {

    public static MemberRepository provideMemberRepository() {
        MemberCache memberCache = MemberCacheImpl.getInstance();
        MemberDataSourceFactory memberDataSourceFactory = MemberDataSourceFactory.getInstance(memberCache);
        return MemberDataRepository.newInstance(memberDataSourceFactory);
    }

    public static CardRepository provideCardRepository() {
        return CardDataRepository.newInstance();
    }
}
