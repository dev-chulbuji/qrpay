package com.jitosoft.qrpay.data.executor;

import com.jitosoft.qrpay.domain.executor.ExecutionThread;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class JobExecutionsThread implements ExecutionThread {

    @Override
    public Scheduler getScheduler() {
        return Schedulers.io();
    }
}
