package com.alekseysamoylov.integration.config

import com.alekseysamoylov.integration.controller.API_URI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import springfox.documentation.builders.PathSelectors.regex
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SwaggerConfig(private val protobufHttpMessageConverter: ProtobufHttpMessageConverter) : WebMvcConfigurationSupport() {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.alekseysamoylov.integration.controller"))
            .paths(regex("$API_URI.*"))
            .build()
            .apiInfo(
                ApiInfo(
                    "Integration Project",
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
            "$API_URI/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/ui"
        )
        registry.addRedirectViewController(
            "$API_URI/swagger-resources/configuration/security",
            "/swagger-resources/configuration/security"
        )
        registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources")
    }

    public override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("$API_URI/swagger-ui.html**")
            .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html")
        registry.addResourceHandler("$API_URI/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
    }

  override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
    converters.add(protobufHttpMessageConverter)
    super.configureMessageConverters(converters)
  }
}
