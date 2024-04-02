package com.example.test_task_playtox.service;

import com.example.test_task_playtox.model.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
@Log4j2
public class RunAfterStartup {

    private final AccountService accountService;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        createAndSave();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for(int i = 0; i<30; i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random()*2000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Long fromId = (long) (Math.random()* accountService.allCount()+1);
                    Long toId = (long) (Math.random()* accountService.allCount()+1);
                    if(fromId == toId){
                        while (fromId == toId){
                            toId = (long) (Math.random()* accountService.allCount()+1);
                        }
                    }
                    Integer count = (int) (Math.random()*5000);
                    log.info("Thread {} transferred {} money from account id {} to account id {}", Thread.currentThread().getName(), count, fromId, toId);
                    accountService.moneyTransaction(fromId, toId, count);
                }
            });
        }
    }

    public void createAndSave(){
        Account account = new Account();
        Account account1 = new Account();
        Account account2 = new Account();
        Account account3 = new Account();

        accountService.save(account);
        accountService.save(account1);
        accountService.save(account2);
        accountService.save(account3);
    }
}
