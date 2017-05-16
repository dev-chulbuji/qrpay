package com.jitosoft.qrpay.data.datasource;

import android.support.annotation.NonNull;

import com.jitosoft.qrpay.data.cache.MemberCache;

public class MemberDataSourceFactory {

    static MemberDataSourceFactory factoryInstance;
    private MemberCache memberCache;

    private MemberDataSourceFactory(@NonNull MemberCache memberCache) {
        this.memberCache = memberCache;
    }

    public static MemberDataSourceFactory getInstance(@NonNull MemberCache memberCache) {

        if (factoryInstance == null) {
            factoryInstance = new MemberDataSourceFactory(memberCache);
        }
        return factoryInstance;
    }

    public MemberDataSource local() {
        return MemberLocalDataSource.getInstance(memberCache);
    }

    public MemberDataSource remote() {
        return MemberRemoteDataSource.getInstance(memberCache);
    }
}
