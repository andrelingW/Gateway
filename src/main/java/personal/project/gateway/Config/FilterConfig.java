package personal.project.gateway.Config;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.RouteToRequestUrlFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import personal.project.gateway.Model.Route;
import personal.project.gateway.Service.GatewayService;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

public class FilterConfig implements GatewayFilter, Ordered {

    @Autowired
    GatewayService service;

    private static final Logger log = LoggerFactory.getLogger(FilterConfig.class);

    @Override
    public int getOrder() {
        return RouteToRequestUrlFilter.ROUTE_TO_URL_FILTER_ORDER + 1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            String newUrl = "";
            String mid = "";

            HttpHeaders headers = exchange.getRequest().getHeaders();
            String body = exchange.getAttribute("cachedRequestBodyObject");
            String sourcePath = exchange.getRequest().getPath().toString();

            if (headers.containsKey("X-PARTNER-ID")) {
                mid = headers.getFirst("X-PARTNER-ID");
            } else if (headers.containsKey("CREDENTIAL-ID")) {
                mid = headers.getFirst("CREDENTIAL-ID");
            }

            List<Route> listRoute = service.checkURL(mid, sourcePath);
            if (!listRoute.isEmpty()) {
                newUrl = listRoute.get(0).getTargetUrl();
            }

            log.info("INCOMING REQUEST - Header : " + headers.toString() + " Body : " + body +
                    " Target URL : " + newUrl);

            exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, new URI(newUrl));

        } catch (Exception e) {
            log.error("ERROR : " + e);
        }

        return chain.filter(exchange);
    }
}
