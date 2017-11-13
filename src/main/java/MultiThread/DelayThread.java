package MultiThread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by robertpicyu on 2017/11/5.
 */
public class DelayThread {
    public static void main(String[] args) throws InterruptedException {

        /* 延迟执行,只执行一次: timer是单线程! */
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("timer job" + "thread: " + Thread.currentThread().getId());
            }
        },5);

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executor.schedule(()->System.out.println("current :"+ finalI + "thread: " + Thread.currentThread().getId()),3, TimeUnit.SECONDS);
        }
        Thread.sleep(1000);
        executor.schedule(()->System.out.println("current : final"+  "thread: " + Thread.currentThread().getId()),3, TimeUnit.SECONDS);


        /* 周期job */
        executor.scheduleAtFixedRate(()-> System.out.println("schedule job"),5,1,TimeUnit.SECONDS);
    }


}
