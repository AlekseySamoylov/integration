package com.alekseysamoylov.integration.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.PathSelectors.regex
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry




@Configuration
@EnableSwagger2
class SwaggerConfig : WebMvcConfigurationSupport() {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.alekseysamoylov.integration.controller"))
            .paths(regex("/api.*"))
            .build()
            .apiInfo(
                ApiInfo(
                    "Phone Book Project",
                    "Documentation automatically generated", "v1.0", null,
                    Contact(
                        "Aleksey Samoylov", "alekseysamoylov.com",
                        "alekseysamoylov89@gmail.com"
                    ), null, null, listOf()
                )
            )
    }

    public override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs")
        registry.addRedirectViewController(
            "/api/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/ui"
        )
        registry.addRedirectViewController(
            "/api/swagger-resources/configuration/security",
            "/swagger-resources/configuration/security"
        )
        registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources")
    }

    public override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/api/swagger-ui.html**")
            .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html")
        registry.addResourceHandler("/api/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
    }
}
