package com.dinesh.StudentManagementSystem.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * By default one thread is assigned for all schedules specified in classpath. Thread name scheduling-1
 * Add -> spring.task.scheduling.pool.size = 10 (It will assign 10 thread from scheduling-1 to 10)
 *
 * CRON FORMAT:
 *  Docs: http://www.quartz-scheduler.org/documentation/quartz-2.3.0/tutorials/crontrigger.html
 *
 *  FORMAT: SEC MIN HOUR DAY MONTH YEAR
 *
 */
@Component
public class MailScheduler {
    private Logger log = LoggerFactory.getLogger(MailScheduler.class);

//    @Scheduled(fixedDelay = 1000L) // Runs every one second. after method executed then 1 sec delay
//    @Scheduled(fixedDelayString = "PT2H") // java duration unit
    @Scheduled(cron = "0 15 10 ? * *") // cron Fire at 10:15am every day
    private void notifyUsersByEmail() {
        log.info("SENDING MAIL TO USER For Every 1 sec");
    }

//    @Scheduled(fixedDelay = 1000L, initialDelay = 5000L) // initialDelay Initially wait for given second after server started
//    private void notifyUsersByEmail() {
//        log.info("SENDING MAIL TO USER For Every 1 sec");
//    }
}
