package xyz.computer_algorithms.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvConfiguration {
    @Value("${POSTGRES_USER}")
    private String postgresUser;

    @Value("${POSTGRES_PW}")
    private String postgresPassword;

    @Value("${PGADMIN_MAIL}")
    private String pgAdminMail;

    @Value("${PGADMIN_PW}")
    private String pgAdminPassword;

    @Value("${SERVER_HOST}")
    private String serverHost;

    @Value("${SERVER_PORT}")
    private String serverPort;
}