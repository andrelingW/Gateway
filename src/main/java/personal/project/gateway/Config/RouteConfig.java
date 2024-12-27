package personal.project.gateway.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

public class RouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r
                        .path("/**")
                        .and().readBody(String.class, requestBody -> true)
                        .filters(f -> f.filter(new FilterConfig()))
                        .uri("no://op"))
                .build();
    }

}
