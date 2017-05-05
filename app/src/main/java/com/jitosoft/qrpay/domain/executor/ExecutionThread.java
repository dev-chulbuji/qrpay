package com.jitosoft.qrpay.domain.executor;

import io.reactivex.Scheduler;

public interface ExecutionThread {
    Scheduler getScheduler();
}

