package com.example.springbatchexample.batchprocessing;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class HogeTasklet implements Tasklet {

    @Value("#{jobParameters[ccc] ?: 'ccc-init'}")
    String ccc;

    @Value("#{jobParameters[ddd] ?: 'ddd-init'}")
    String ddd;

//    private final String message;
//
//    public MessageTasklet(String message) {
//        this.message = message;
//    }
//
//    public String getMessage() {
//        return message;
//    }

    /**
     * 具体的処理を記述する処理
     *
     * @param contribution
     * @param chunkContext
     * @return
     * @throws Exception
     */
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("**************************");
        System.out.println("HogeStep=Message(ccc):" + ccc);
        System.out.println("HogeStep=Message(ddd):" + ddd);
        System.out.println("**************************");
        if(ccc.equals("unko")){
            throw new RuntimeException();   //ExistStausは正常終了のものがでる
        }
        return RepeatStatus.FINISHED;   //実行する作業がこれ以上ないことを呼び出し元に通知するコールバック
    }
}
