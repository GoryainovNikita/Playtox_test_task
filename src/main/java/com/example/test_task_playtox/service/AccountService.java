package com.example.test_task_playtox.service;

import com.example.test_task_playtox.model.Account;
import com.example.test_task_playtox.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class AccountService {

    private final AccountRepository accountRepository;


    public Account save(Account account){
        return accountRepository.save(account);
    }

    public Account getById(Long id){
        Optional<Account> byId = accountRepository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        throw new RuntimeException("Account not found for id : " + id);
    }

    @Transactional
    public void moneyTransaction(Long fromId, Long toId, Integer count){
        Account accountFrom = getById(fromId);
        Account accountTo = getById(toId);

        if(count > accountFrom.getMoney()){
            count = accountFrom.getMoney();
        }

        log.info("Account with id {} have money {}", fromId, accountFrom.getMoney());
        log.info("Account with id {} have money {}", toId, accountTo.getMoney());

        accountFrom.setMoney(accountFrom.getMoney() - count);
        accountTo.setMoney(accountTo.getMoney() + count);

        log.info("Account with id {} have money {}", fromId, accountFrom.getMoney());
        log.info("Account with id {} have money {}", toId, accountTo.getMoney());

        save(accountFrom);
        save(accountTo);

    }

    public Long allCount(){
        return accountRepository.count();
    }
}
