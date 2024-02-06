// package com.example.EpamSpringBoot;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;
// import springfox.documentation.builders.ApiInfoBuilder;
// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.service.ApiInfo;
// import springfox.documentation.service.Contact;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.web.plugins.Docket;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
// @Configuration
// @EnableWebMvc
// public class SwaggerConfig {
// @Bean
// public Docket api() {
// return new Docket(DocumentationType.SWAGGER_2)
// .select()
// .apis(RequestHandlerSelectors.basePackage("com.example.EpamSpringBoot.*"))
// .paths(PathSelectors.any())
// .build().apiInfo(metaData());
// }
//
// private ApiInfo metaData() {
// return new ApiInfoBuilder()
// .title("Tech Interface - Spring Boot Swagger Configuration")
// .description("\"Swagger configuration for application \"")
// .version("1.1.0")
// .license("Apache 2.0")
// .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
// .contact(new Contact("Tech Interface",
// "https://www.youtube.com/channel/UCMpJ8m1w9t7EFcF9x8rs02A", "info@techinterface.com"))
// .build();
// }
// //for Swagger api doc generation
// //http://localhost:8091/v2/api-docs
// }
//// import org.springframework.context.annotation.Bean;
//// import org.springframework.context.annotation.Configuration;
//// import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//// import springfox.documentation.builders.ApiInfoBuilder;
//// import springfox.documentation.builders.PathSelectors;
//// import springfox.documentation.builders.RequestHandlerSelectors;
//// import springfox.documentation.service.ApiInfo;
//// import springfox.documentation.service.Contact;
//// import springfox.documentation.spi.DocumentationType;
//// import springfox.documentation.spring.web.plugins.Docket;
////
//// @Configuration
//// @EnableWebMvc
//// public class SwaggerConfig implements WebMvcConfigurer {
////
//// @Bean
//// public Docket api() {
//// return new Docket(DocumentationType.SWAGGER_2).select()
//// .apis(RequestHandlerSelectors.basePackage("com.example.EpamSpringBoot"))
//// .paths(PathSelectors.regex("/.*"))
//// .build().apiInfo(apiInfoMetaData());
//// }
////
//// private ApiInfo apiInfoMetaData() {
////
//// return new ApiInfoBuilder().title("NAME OF SERVICE")
//// .description("API Endpoint Decoration")
//// .contact(new Contact("Dev-Team", "https://www.dev-team.com/", "dev-team@gmail.com"))
//// .license("Apache 2.0")
//// .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
//// .version("1.0.0")
//// .build();
//// }
////
//// }