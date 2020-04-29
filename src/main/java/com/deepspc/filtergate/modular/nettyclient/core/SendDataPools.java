package com.deepspc.filtergate.modular.nettyclient.core;

import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description 向Netty发送数据的线程池
 * @Author didoguan
 * @Date 2020/4/29
 **/
@Component
public class SendDataPools {
    /**
     * 创建线程池
     */
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 10, 5, TimeUnit.MINUTES, new LinkedBlockingDeque<>());

    /**
     * 执行要处理的任务
     * @param runnable
     */
    public void executeTask(Runnable runnable) {
        executor.execute(runnable);
    }
}
