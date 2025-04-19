package cn.liaozh.admin_api.config.quartz.exceution;

import cn.liaozh.admin_api.config.quartz.InvokeUtils;
import cn.liaozh.common.entity.DevCrontab;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * 禁止并发任务
 */
@DisallowConcurrentExecution
public class QuartzDisExecution extends AbstractQuartzJob {

    @Override
    protected void doExecute(JobExecutionContext context, DevCrontab crontab) throws Exception {
        InvokeUtils.invokeMethod(crontab);
    }

}