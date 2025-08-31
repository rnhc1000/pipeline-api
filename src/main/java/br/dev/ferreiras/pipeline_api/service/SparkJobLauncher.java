package br.dev.ferreiras.pipeline_api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SparkJobLauncher {

    private static final Logger logger = LoggerFactory.getLogger(SparkJobLauncher.class);

    @KafkaListener(topics = "click-events", groupId = "launcher")
    public void onMessage(String event) {
        try {
            logger.info("::: Received event, launching Spark job... :::");
            ProcessBuilder processBuilder = new ProcessBuilder("/opt/spark/bin/spark-submit",
                    "--class", "br.dev.ferreiras.ClickEventConsumer",
                    "--master", "local[1]",
                    "--jars", "spark-sql-kafka-0-10_2.12-3.5.5.jar,kafka-clients-3.5.0.jar",
//                    .config("spark.driver.bindAddress", "0.0.0.0")
//                    .config("spark.driver.host", "127.0.0.1")
                    "/opt/spark/examples/jars/clicksparkconsumer-1.0-25.06-25-all.jar");
            processBuilder.inheritIO().start();
        } catch (Exception e) {
            logger.info("::: Something bad happened: {} :::", e.getMessage());

        }
    }
}

