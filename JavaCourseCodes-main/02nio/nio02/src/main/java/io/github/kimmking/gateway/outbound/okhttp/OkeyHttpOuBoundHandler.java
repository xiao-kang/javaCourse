package io.github.kimmking.gateway.outbound.okhttp;


import io.github.kimmking.gateway.filter.HeaderHttpResponseFilter;
import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.github.kimmking.gateway.filter.HttpResponseFilter;
import io.github.kimmking.gateway.outbound.IHttpOutboundHandler;
import io.github.kimmking.gateway.outbound.httpclient4.NamedThreadFactory;
import io.github.kimmking.gateway.router.HttpEndpointRouter;
import io.github.kimmking.gateway.router.RandomHttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author chenxiaokang
 * @date 2021/5/23
 */
public class OkeyHttpOuBoundHandler implements IHttpOutboundHandler {
    private  OkHttpClient httpclient;
    private ExecutorService proxyService;
    private List<String> backendUrls;

    HttpResponseFilter filter = new HeaderHttpResponseFilter();
    HttpEndpointRouter router = new RandomHttpEndpointRouter();

    public OkeyHttpOuBoundHandler(List<String> backends) {
        System.out.println("OkeyHttpOuBoundHandler");

        this.backendUrls = backends.stream().map(this::formatUrl).collect(Collectors.toList());

        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);

//        IOReactorConfig ioConfig = IOReactorConfig.custom()
//                .setConnectTimeout(1000)
//                .setSoTimeout(1000)
//                .setIoThreadCount(cores)
//                .setRcvBufSize(32 * 1024)
//                .build();

//        httpclient = HttpAsyncClients.custom().setMaxConnTotal(40)
//                .setMaxConnPerRoute(8)
//                .setDefaultIOReactorConfig(ioConfig)
//                .setKeepAliveStrategy((response,context) -> 6000)
//                .build();
//        httpclient.start();

        httpclient =new OkHttpClient.Builder()

                .connectTimeout(30, TimeUnit.SECONDS)
                .callTimeout(120, TimeUnit.SECONDS)
                .pingInterval(5, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
//        try {
//            System.out.println(getClass().getClassLoader());
//           Class<okhttp3.OkHttpClient> clazz= (Class<OkHttpClient>) getClass().getClassLoader().loadClass("okhttp3.OkHttpClient");
////            httpClient =new OkHttpClient();
//            httpclient=clazz.newInstance();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }


    }

    private String formatUrl(String backend) {
        return backend.endsWith("/")?backend.substring(0,backend.length()-1):backend;
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter) {
        System.out.println("handle");
        String backendUrl = router.route(this.backendUrls);
        final String url = backendUrl + fullRequest.uri();
        filter.filter(fullRequest, ctx);
//        fetchGet(fullRequest,ctx,url);
        proxyService.submit(()->fetchGet(fullRequest, ctx, url));
    }

    private void fetchGet(final FullHttpRequest inbound, final ChannelHandlerContext ctx, final String url) {
//        final HttpGet httpGet = new HttpGet(url);
//        //httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
//        httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
//        httpGet.setHeader("mao", inbound.headers().get("mao"));

        httpclient.newCall(new Request.Builder()
                        .url(url)
                        .header("User-Agent", "OkHttp Example")
                        .build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response)  {
                try {
//                    System.out.println(response.body().byteString());
                    handleResponse(inbound, ctx, response);

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });



    }

    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final Response endpointResponse) throws Exception {
        FullHttpResponse response = null;
        try {
//            String value = "hello,kimmking";
//            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
//            response.headers().set("Content-Type", "application/json");
//            response.headers().setInt("Content-Length", response.content().readableBytes());


//            byte[] body = EntityUtils.toByteArray(endpointResponse.getEntity());


            byte[] body=endpointResponse.body().bytes();
            System.out.println(new String(body));
//            System.out.println(body.length);

            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));

//            response.headers().set("Content-Type", "application/json");
            response.headers().set("Content-Type", "text/html");
//            response.headers().setInt("Content-Length", Integer.parseInt(endpointResponse.getFirstHeader("Content-Length").getValue()));
            response.headers().setInt("Content-Length",body.length);


//            response.headers().setInt("Content-Length", Integer.parseInt(endpointResponse.header("Content-Length")));

            filter.filter(response);

//            for (Header e : endpointResponse.getAllHeaders()) {
//                //response.headers().set(e.getName(),e.getValue());
//                System.out.println(e.getName() + " => " + e.getValue());
//            }

        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
            //ctx.close();
        }

    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
