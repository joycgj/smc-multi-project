package cn.javass.dp.strategy.example8;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 实现日志策略的抽象模板，实现给消息添加时间
 */
public abstract class LogStrategyTemplate implements LogStrategy {

    @Override
    public final void log(String msg) {
        //第一步：给消息添加记录日志的时间
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        msg = df.format(new java.util.Date()) + " 内容是：" + msg;
        //第二步：真正执行日志记录
        doLog(msg);
    }

    public abstract void doLog(String msg);
}
