package grimsi.accservermanager.backend;

import grimsi.accservermanager.backend.configuration.ApplicationConfiguration;
import grimsi.accservermanager.backend.service.StartupService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfiguration.class)
public class ACCServerManager implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ACCServerManager.class);

    @Autowired
    StartupService startupService;

    public static void main(String[] args) throws Exception {
        new SpringApplication(ACCServerManager.class).run(args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }

        log.info("Initializing application...");

        startupService.initialize();

        log.info("Initialized application, startup completed.");
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }

}
