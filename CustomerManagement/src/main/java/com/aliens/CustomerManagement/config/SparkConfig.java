package com.aliens.CustomerManagement.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {
    @Value("${spark.app.name}")
    private String appName;

    @Value("${spark.master}")
    private String master;

    @Bean
    public SparkSession sparkSession() {
        SparkConf conf = new SparkConf()
                .setAppName(appName)
                .setMaster(master)
                .set("spark.mongodb.input.uri", "mongodb://localhost:27017")
                .set("spark.mongodb.input.database", "CustomerDomain")
                .set("spark.mongodb.input.collection", "Customer");

        JavaSparkContext sparkContext = new JavaSparkContext(conf);

        return SparkSession.builder()
                .sparkContext(sparkContext.sc())
                .appName(appName)
                .getOrCreate();
    }
    @Bean
    public JavaSparkContext javaSparkContext() {
        return new JavaSparkContext(sparkSession().sparkContext());
    }
}