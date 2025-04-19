package cn.liaozh.admin_api.config.quartz.exceution;

import cn.liaozh.admin_api.config.quartz.InvokeUtils;
import cn.liaozh.common.entity.DevCrontab;
import org.quartz.JobExecutionContext;

/**
 * 允许并发任务
 */
public class QuartzJobExecution extends AbstractQuartzJob {

    @Override
    protected void doExecute(JobExecutionContext context, DevCrontab crontab) throws Exception {
        InvokeUtils.invokeMethod(crontab);
    }

}
