package com.example.springbatchexample.batchprocessing;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

//    @Bean
//    public FlatFileItemReader<Person> reader() {
//        return new FlatFileItemReaderBuilder<Person>()
//                .name("personItemReader")
//                .resource(new ClassPathResource("sample-data.csv"))
//                .delimited()
//                .names(new String[]{"firstName", "lastName"})
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
//                    setTargetType(Person.class);
//                }})
//                .build();
//    }
//
//    @Bean
//    public PersonItemProcessor processor() {
//        return new PersonItemProcessor();
//    }
//
//    @Bean
//    public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<Person>()
//                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//                .sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
//                .dataSource(dataSource)
//                .build();
//    }


//    @Bean
//    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
//        return jobBuilderFactory.get("importUserJob")
//                .incrementer(new RunIdIncrementer())
//                .listener(listener)
//                .flow(step1)
//                .end()
//                .build();
//    }


//    @Bean
//    public Step step1(JdbcBatchItemWriter<Person> writer) {
//        return stepBuilderFactory.get("step1")
//                .<Person, Person>chunk(10)//??????????????????????????????????????????????????????????????????????????????????????????????????????????????? 10 ??????????????????????????????????????????
//                .reader(reader())
//                .processor(processor())
//                .writer(writer)
//                .build();
//    }

    @Bean
    public Job fooJob() {
        System.out.println("fooJob?????????");
        return jobBuilderFactory.get("aaa")
                .flow(helloStep())//????????????????????????
                .end()
                .build();
    }

    @Bean
    public Step helloStep() {
        System.out.println("helloStep?????????????????????");
        return stepBuilderFactory.get("myHelloStep")
//                .tasklet(new MessageTasklet("hello"))//????????????TaskLet?????????
//                .tasklet(messageTasklet)//????????????TaskLet?????????
                .tasklet(methodInvokingTaskletAdapter())//????????????TaskLet?????????
//                .listener(new ItemFailureLoggerListener())
                .build();
    }

    public MethodInvokingTaskletAdapter methodInvokingTaskletAdapter() {
        MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
        adapter.setTargetObject(exampleService);
        adapter.setTargetMethod("doing");
        return adapter;
    }

    @Autowired
    HogeTasklet hogeTasklet;
    @Autowired
    MessageTasklet messageTasklet;
    @Autowired
    ExampleService exampleService;
    //--------------------------------------------------------

    @Bean
    public Job barJob() {
        System.out.println("barJob?????????????????????");
        return jobBuilderFactory.get("bbb")
                .flow(helloStep())//????????????Step
                .next(hoge())//????????????Step
                .next(worldStep())//????????????Step
                .end()
                .build();
    }

    public Step worldStep() {
        System.out.println("worldStep?????????");
        return stepBuilderFactory.get("myWorldStep")
//                .tasklet(new MessageTasklet("world!!"))
                .tasklet(messageTasklet)
                .build();
    }


    public Step hoge() {
        System.out.println("hoge?????????");
        return stepBuilderFactory.get("myHoge")
                .tasklet(hogeTasklet)
                .build();
    }


//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .<Person, Person>chunk(10)//??????????????????????????????????????????????????????????????????????????????????????????????????????????????? 10 ??????????????????????????????????????????
//                .reader(reader())
//                .processor(processor())
//                .writer(writer)
//                .build();
//    }


}