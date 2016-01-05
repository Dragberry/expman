package net.dragberry.expman.menu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import net.dragberry.expman.menu.service.MenuService;

@Configuration
@ComponentScan(basePackageClasses = { MenuService.class })
public class MenuConfig {

}
