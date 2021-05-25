package io.github.kimmking.gateway.outbound;

import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author chenxiaokang
 * @date 2021/5/25
 */
public interface IHttpOutboundHandler {
    public abstract void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter);
}
