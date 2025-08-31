package br.dev.ferreiras.pipeline_api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.IOException;
import jakarta.annotation.PostConstruct;

public class SparkJobRunner {

    private static final Logger logger = LoggerFactory.getLogger(SparkJobRunner.class);

        @PostConstruct
        public void launchSparkJob() {
            try {
                ProcessBuilder pb = new ProcessBuilder(
                        "/opt/spark/bin/spark-submit",
                        "--class", "br.dev.ferreiras.ClickEventConsumer",
                        "--jars", "spark-sql-kafka-0-10_2.12-3.5.5.jar,kafka-clients-3.5.0.jar",
                        "--master", "local[*]",
                        "--conf", "spark.driver.extraJavaOptions=--add-exports=java.base/sun.nio.ch=ALL-UNNAMED",
                        "--conf", "spark.executor.extraJavaOptions=--add-exports=java.base/sun.nio.ch=ALL-UNNAMED",
                        "--conf", "spark.hadoop.fs.s3a.endpoint=http://172.31.40.1:9000",
                        "--conf", "spark.hadoop.fs.s3a.access.key=minio",
                        "--conf", "spark.hadoop.fs.s3a.secret.key=minio123",
                        "--conf", "spark.hadoop.fs.s3a.path.style.access=true",
                        "--conf", "spark.hadoop.fs.s3a.impl=org.apache.hadoop.fs.s3a.S3AFileSystem",
                        "/opt/spark/examples/jars/clicksparkconsumer-1.0-25.06-25-all.jar"
                );

                pb.inheritIO();
                Process process = pb.start();

                int exitCode = process.waitFor();
                logger.info("::: Spark job finished -> code: {} ::: ", exitCode);

            } catch (IOException | InterruptedException e) {
                logger.info("::: Something bad happened: {} :::", e.getMessage());
            }
        }
    }

