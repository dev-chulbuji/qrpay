package com.jitosoft.qrpay;

import android.support.annotation.NonNull;

/**
 * Created by jihoon on 2017. 4. 28..
 */

public interface MemberDataSource {

    interface SaveMemberCallback {

        void onSaveMemberCompleted();
    }

    void saveMember(@NonNull String email, @NonNull String nickname, @NonNull String password, @NonNull SaveMemberCallback callback);

    Member getMember();
}
