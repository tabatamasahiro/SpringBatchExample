package com.example.springbatchexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBatchExampleApplication {

    public static void main(String[] args) {

        int exit = SpringApplication.exit(
                SpringApplication.run(SpringBatchExampleApplication.class, args)
                , () -> 99  //処理成功時のステータスコード
        );
        System.out.println(">>>> ExitStatus:" + exit);
        System.exit(exit);

    }

}
