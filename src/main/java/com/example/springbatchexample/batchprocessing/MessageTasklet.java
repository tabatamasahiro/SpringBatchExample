package com.example.springbatchexample.batchprocessing;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;


public class MessageTasklet implements Tasklet {

    private final String message;

    public MessageTasklet(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

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
        System.out.println("Message:" + message);
        System.out.println("**************************");
        return RepeatStatus.FINISHED;
    }
}
