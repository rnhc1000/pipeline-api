package br.dev.ferreiras.pipeline_api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZonedDateTime;

@SpringBootApplication
public class PipelineApiApplication implements CommandLineRunner {


	private static final Logger logger = LoggerFactory.getLogger(PipelineApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PipelineApiApplication.class, args);
	}


	/**
	 * @param args no args needed
	 * @throws Exception in case of error
	 */
	@Override
	public void run(String... args) throws Exception {
		final ZonedDateTime zonedDateTime = ZonedDateTime.now(ZonedDateTime.now().getZone());
		final String javaVersion = System.getProperty("java.version");
		final int cores = Runtime.getRuntime().availableProcessors();

		if (PipelineApiApplication.logger.isInfoEnabled()) {
			logger.info("Pipeline API started running at zone {}, " +
							"powered by java version {}, " +
							"on top of {} cores",
					zonedDateTime,
					javaVersion,
					cores
			);
		}
	}
}
