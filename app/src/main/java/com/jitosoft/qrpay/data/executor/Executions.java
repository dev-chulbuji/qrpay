package com.jitosoft.qrpay.data.executor;

import com.jitosoft.qrpay.domain.executor.ExecutionThread;
import com.jitosoft.qrpay.domain.executor.PostExecutionThread;

public class Executions {

    private static final JobExecutionsThread JOB_EXECUTIONS_THREAD;
    private static final UiExecutionsThread UI_EXECUTIONS_THREAD;

    static {
        JOB_EXECUTIONS_THREAD = new JobExecutionsThread();
        UI_EXECUTIONS_THREAD = new UiExecutionsThread();
    }

    public static ExecutionThread job() {
        return JOB_EXECUTIONS_THREAD;
    }

    public static PostExecutionThread ui() {
        return UI_EXECUTIONS_THREAD;
    }
}
