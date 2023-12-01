package com.example.apigateway.filter;

import com.example.apigateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GatewayFilter{

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private RestTemplate template;

    @Autowired
    private JwtUtil jwtUtil;

//    public AuthenticationFilter() {
//        super(Config.class);
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return ((exchange, chain) -> {
//            if (routeValidator.isSecured.test(exchange.getRequest())) {
//                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//                    throw new RuntimeException("missing authorization header");
//                }
//
//                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
//                if (authHeader != null && authHeader.startsWith("Bearer ")) {
//                    authHeader = authHeader.substring(7);
//                }
//                try {
////                    template.getForObject("http://IDENTITY-SERVICE/validate?token" + authHeader, String.class);
//                    jwtUtil.validateToken(authHeader);
//                } catch (Exception e) {
//                    System.out.println("invalid access...!");
//                    throw new RuntimeException("un authorized access to application");
//                }
//            }
//            return chain.filter(exchange);
//        });
//    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (routeValidator.isSecured.test(request)) {
            if (authMissing(request)) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            final String token = request.getHeaders().getOrEmpty("Authorization").get(0);

            if (jwtUtil.isExpired(token)) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            if (routeValidator.isYourOrders.test(request)){
                String jwtToken = token.split(" ")[1].trim();
                String sub = jwtUtil.getClaims(jwtToken).get("sub", String.class);
                String shopServiceUrl = "http://localhost:8090/api/handle";
                System.err.println(jwtUtil.getClaims(token.split(" ")[1].trim()));
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> requestEntity = new HttpEntity<>(sub, headers);
                ResponseEntity<String> responseEntity = template.postForEntity(shopServiceUrl, requestEntity, String.class);
            }
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private boolean authMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }
}
