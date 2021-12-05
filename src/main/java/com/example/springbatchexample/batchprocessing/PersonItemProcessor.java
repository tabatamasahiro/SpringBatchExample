package com.example.springbatchexample.batchprocessing;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.io.IOException;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public Person process(final Person person) throws Exception {
        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();

        final Person transformedPerson = new Person(firstName, lastName);

        System.out.println("Converting (" + person + ") into (" + transformedPerson + ")");
        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        okhttp();

        return transformedPerson;
    }

    private void okhttp() {
        Request request = new Request.Builder()
                .get()
                .header("Content-Type", ":application/json")
                .url("https://api.open-meteo.com/v1/forecast?latitude=35.6785&longitude=139.6823&hourly=temperature_2m&timezone=Asia%2FTokyo")
                .build();

        OkHttpClient client = new OkHttpClient();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("****************************");
            System.out.println(response.body().string());
            System.out.println("****************************");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}