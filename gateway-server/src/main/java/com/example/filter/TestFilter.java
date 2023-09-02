package com.example.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.config.InterceptorPathBean;
import com.example.entity.AuthTuser;
import com.example.utils.JWTUtils;
import com.example.utils.Power;
import com.example.utils.Res;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@Component
public class TestFilter implements GlobalFilter, Ordered {


    @Resource
    InterceptorPathBean pathBean;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request=exchange.getRequest();
        ServerHttpResponse response=exchange.getResponse();
//        放行
        String absPath=request.getPath().toString();
        String[] pathArr=absPath.split("/");
        String FIRST_PATH = pathArr[1];
        String SECOND_PATH = pathArr[2];
//        String ThirdPath = pathArr[3];

        System.out.println("1-访问 "+absPath);

        //公共资源
        if(  (pathBean.getOpenPaths().contains(absPath)
                || pathBean.getOpenPaths().contains(FIRST_PATH)) && !pathBean.getAdminPaths().contains(SECOND_PATH)
        ){
            System.out.println("2-进入公共资源   "+ absPath);
            return chain.filter(exchange);
        }

        HttpHeaders headers=request.getHeaders();
        String token =null;
        AuthTuser tuser=null;
        try{
            token=headers.get("token").get(0);
            DecodedJWT decodedJWT= JWTUtils.verify(token);
            tuser=JWTUtils.getUser(decodedJWT);
        }catch (Exception e){
            return errorInfo(exchange, "认证不通过", 403);
        }

        //        普通用户权限
        if( pathBean.getUserPaths().contains(FIRST_PATH)  && !pathBean.getAdminPaths().contains(SECOND_PATH)){
            System.out.println("2-进入普通用户资源   "+ absPath + "    "+ tuser);
            return chain.filter(exchange);
        }
//        管理员权限
        else if( tuser.getRole().equals(Power.ADMIN)
                && pathBean.getAdminPaths().contains(SECOND_PATH)
        ){
            System.out.println("2-进入管理用户资源   "+ absPath + "    "+ tuser);
            return chain.filter(exchange);
        }
        return errorInfo(exchange, "访问受到限制！", 403);
    }

    @Override
    public int getOrder() {
        return 1;
    }

    /**
     * 返回response
     *
     * @param exchange
     * @param message  异常信息
     * @param status   data中的status
     * @return
     */
    public static Mono<Void> errorInfo(ServerWebExchange exchange, String message, Integer status) {
        // 自定义返回格式
        Map map =Res.getMap();
        map.put("code",status);
        map.put("message",message);
        return Mono.defer(() -> {
            byte[] bytes;
            try {
                bytes = new ObjectMapper().writeValueAsBytes(map);
            } catch (JsonProcessingException e) {
//                log.error("网关响应异常：", e);
                throw new RuntimeException("信息序列化异常");
            } catch (Exception e) {
//                log.error("网关响应异常：", e);
                throw new RuntimeException("写入响应异常");
            }
            ServerHttpResponse response = exchange.getResponse();
            response.getHeaders().add("Content-Type", MediaType.APPLICATION_JSON_UTF8.toString());
            DataBuffer buffer = response.bufferFactory().wrap(bytes);
            return response.writeWith(Flux.just(buffer));
        });
    }



//        System.out.println(request.getQueryParams());
//
//        List<String> values=request.getQueryParams().get("test");
//        if(values != null && values.contains("1")){
//            return chain.filter(exchange);
//        }
//        else{
//            return exchange.getResponse().setComplete();
//        }



}
