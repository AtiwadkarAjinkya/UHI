
package in.gov.abdm.uhi.registry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("in.gov.abdm.uhi.registry")).paths(PathSelectors.any())
				.build().apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {
		ApiInfo apiinfo = new ApiInfo("UHI Network-Registry Project",
				"UHI Network-Registry under UHI(Unified Health Interface)", "1.0", "termsOfServiceUrl",
				"National Health Authority India", "License 1.0", "https://licenseUrl.com");
		return apiinfo;
	}
}
