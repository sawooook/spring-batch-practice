package com.example.springbatchpractice

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class HelloJobConfig(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory
) {


    @Bean
    fun helloJob(): Job {
        return jobBuilderFactory.get("helloJob")
            .start(helloStepA())
            .next(helloStepB())
            .build()
    }

    @Bean
    fun helloStepA(): Step {
        // tasklet을 무한 반복 시킴, tasklet을 그래서 한번만 반복시키고 종료시킬지
        return stepBuilderFactory.get("helloStepA")
            .tasklet(Tasklet { contribution, chunkContext ->
                println("=============================")
                println(">>>> HELLO SPRING BATCH")
                println("=============================")
                RepeatStatus.FINISHED
            }).build()
    }

    @Bean
    fun helloStepB(): Step {
        return stepBuilderFactory.get("helloStepB")
            .tasklet(Tasklet { contribution, chunkContext ->
                println("=============================")
                println(">>>> HELLO SPRING BATCH")
                println("=============================")
                RepeatStatus.FINISHED
            }).build()
    }
}
