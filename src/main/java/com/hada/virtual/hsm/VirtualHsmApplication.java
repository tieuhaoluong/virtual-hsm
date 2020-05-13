package com.hada.virtual.hsm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@Controller
public class VirtualHsmApplication {
    private static final Logger log = LoggerFactory.getLogger(VirtualHsmApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(VirtualHsmApplication.class);
        ConfigurableEnvironment env = app.run(args).getEnvironment();

        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String serverPort = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");
        if (contextPath == null || contextPath.isBlank()) {
            contextPath = "/";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        log.info("\n--------------------------------------------------------------------\n\t" +
                        "Application '{}' is running!\n\t" +
                        "Version\t\t: {}\n\t" +
                        "Access URLs\t: {}://{}:{}{}\n\t" +
                        "Local\t\t: {}://localhost:{}{}\n\t" +
                        "Profile(s)\t: {}\n\t" +
                        "Directory\t: {}" +
                        "\n--------------------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("spring.application.version"),
                protocol, hostAddress, serverPort, contextPath,
                protocol, serverPort, contextPath,
                env.getActiveProfiles(),
                env.getProperty("user.dir"));
    }

    @GetMapping
    public String index() {
        return "redirect:swagger-ui.html";
    }
}
