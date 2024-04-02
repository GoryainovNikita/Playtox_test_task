package com.example.test_task_playtox.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PID;

    private String ID;

    private Integer money;

    public Account() {
        char ch = (char)(Math.random()*100);
        this.ID = ch +""+ Math.random()*10;
        this.money = 10000;
    }
}
