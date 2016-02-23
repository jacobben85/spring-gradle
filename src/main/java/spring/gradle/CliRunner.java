package spring.gradle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Spring boot command line runner.
 */
@Component
public class CliRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CliRunner.class);

    @Override
    public void run(String... args) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (String option : args) {
            sb.append(" ").append(option);
        }
        sb = sb.length() == 0 ? sb.append("No Options Specified") : sb;
        LOGGER.warn(String.format("Application launched with options : %s", sb.toString()));
    }
}
