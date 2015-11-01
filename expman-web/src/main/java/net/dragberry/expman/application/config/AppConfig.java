package net.dragberry.expman.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = { BusinessConfig.class, SecurityConfig.class })
public class AppConfig {

}
