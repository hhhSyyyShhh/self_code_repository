package org.example.shopping_project1.config;

import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class TomcatConfig {

    @Bean
    public TomcatContextCustomizer tomcatContextCustomizer() {
        return context -> {
            context.addLifecycleListener(event -> {
                if (event.getType().equals("configure_start")) {
                    Arrays.stream(context.findChildren())
                            .filter(w -> w.getName().equalsIgnoreCase("jsp"))
                            .forEach(context::removeChild);
                }
            });
        };
    }
}

