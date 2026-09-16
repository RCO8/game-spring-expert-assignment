package com.gameexpert;

import com.gameexpert.bootstrap.EngineComponentFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
/*@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.CUSTOM,
        classes = {EngineComponentFilter.class, TypeExcludeFilter.class,
                AutoConfigurationExcludeFilter.class}))*/ // 이건 스프링부트 앱 어노테이션 안에 있기 때문에 따로 있으면 안된다.
public class GameExpertApplication {
    public static void main(String[] args) {
        SpringApplication.run(GameExpertApplication.class, args);
    }
}
