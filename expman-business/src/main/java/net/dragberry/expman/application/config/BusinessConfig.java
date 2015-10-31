package net.dragberry.expman.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.dragberry.expman.business.Services;

@Configuration
@Import(value = { DataConfig.class })
@ComponentScan(basePackageClasses = { Services.class })
public class BusinessConfig {

}
