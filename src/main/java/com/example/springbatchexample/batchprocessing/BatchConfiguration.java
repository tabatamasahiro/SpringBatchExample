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
//                .<Person, Person>chunk(10)//ステップ定義では、一度に書き込むデータ量を定義します。この場合、一度に最大 10 個のレコードが書き込まれます
//                .reader(reader())
//                .processor(processor())
//                .writer(writer)
//                .build();
//    }

    @Bean
    public Job fooJob() {
        System.out.println("fooJob実行！");
        return jobBuilderFactory.get("aaa")
                .flow(helloStep())//実行するステップ
                .end()
                .build();
    }

    @Bean
    public Step helloStep() {
        System.out.println("helloStepメソッド実行！");
        return stepBuilderFactory.get("myHelloStep")
//                .tasklet(new MessageTasklet("hello"))//実行するTaskLetを指定
//                .tasklet(messageTasklet)//実行するTaskLetを指定
                .tasklet(methodInvokingTaskletAdapter())//実行するTaskLetを指定
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
    MessageTasklet messageTasklet;
    @Autowired
    ExampleService exampleService;
    //--------------------------------------------------------

    @Bean
    public Job barJob() {
        System.out.println("barJobメソッドを実行");
        return jobBuilderFactory.get("bbb")
                .flow(helloStep())//実行するStep
                .next(worldStep())//実行するStep
                .end()
                .build();
    }

    public Step worldStep() {
        System.out.println("worldStepを実行");
        return stepBuilderFactory.get("myWorldStep")
//                .tasklet(new MessageTasklet("world!!"))
                .tasklet(messageTasklet)
                .build();
    }

//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .<Person, Person>chunk(10)//ステップ定義では、一度に書き込むデータ量を定義します。この場合、一度に最大 10 個のレコードが書き込まれます
//                .reader(reader())
//                .processor(processor())
//                .writer(writer)
//                .build();
//    }


}