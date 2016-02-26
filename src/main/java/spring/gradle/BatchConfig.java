package spring.gradle;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.gradle.objects.Employee;
import spring.gradle.utils.BatchProcessor;
import spring.gradle.utils.BatchReader;
import spring.gradle.utils.BatchUpdater;

/**
 * Batch config
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public ItemReader<Employee> reader() {
        return new BatchReader();
    }

    @Bean
    public ItemProcessor<Employee, Employee> processor() {
        return new BatchProcessor();
    }

    @Bean
    public ItemWriter<Employee> writer() {
        return new BatchUpdater();
    }

    @Bean
    public Job updateEmployee(JobBuilderFactory jobs, Step s1) {
        return jobs.get("updateEmployee")
                .incrementer(new RunIdIncrementer())
                .flow(s1)
                .end()
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Employee> reader,
                      ItemWriter<Employee> writer, ItemProcessor<Employee, Employee> processor) {
        return stepBuilderFactory.get("step1")
                .<Employee, Employee> chunk(2)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
