package com.jitosoft.qrpay.data.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.jitosoft.qrpay.AppApplication;
import com.jitosoft.qrpay.data.entity.MemberEntity;

public class MemberCacheImpl implements MemberCache {

    private static final String FILE_NAME = "members";

    private static MemberCacheImpl memberCacheImpl;

    private SharedPreferences prefs;

    private MemberCacheImpl() {
        prefs = AppApplication.getAppContext()
                .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public static MemberCacheImpl getInstance() {
        if (memberCacheImpl == null) {
            memberCacheImpl = new MemberCacheImpl();
        }
        return memberCacheImpl;
    }

    @Override
    public void setMemberEntity(MemberEntity memberEntity) {
        SharedPreferences.Editor editor = prefs.edit();
        String memberEntityString = new Gson().toJson(memberEntity);
        editor.putString("member_entity", memberEntityString);
        editor.apply();
    }

    @Override
    public MemberEntity getMemberEntity() {
        String memberEntityString = prefs.getString("member_entity", "");
        MemberEntity memberEntity = new Gson().fromJson(memberEntityString, MemberEntity.class);
        return memberEntity;
    }
}
