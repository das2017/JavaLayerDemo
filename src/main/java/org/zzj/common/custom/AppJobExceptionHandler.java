package org.zzj.common.custom;
import com.dangdang.ddframe.job.executor.handler.JobExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppJobExceptionHandler implements JobExceptionHandler {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handleException(String jobName, Throwable throwable) {
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        StringBuffer stackMsg = new StringBuffer();
        for (StackTraceElement ste : stackTrace) {
            stackMsg.append("\n");
            stackMsg.append(ste.toString());
        }
        logger.error("任务[{}]调度异常，错误原因:{}，错误信息:{}，错误堆栈信息:{}", jobName, throwable.getCause(), throwable.getMessage(), stackMsg);
    }
}