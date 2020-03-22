package ru.bank.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.bank.account.generate.GenerateDao;

@SpringBootApplication
//@EntityScan("ru.bank.account")
//@EnableJpaRepositories("ru.bank.account.entity")
public class Main {

//    @Autowired
//    GenerateDao generateDao;

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
//    public void run()
//    {
//        generateDao.generateDao();
//    }
}