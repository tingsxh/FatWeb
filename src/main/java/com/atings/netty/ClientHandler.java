package com.atings.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.ByteBuffer;

/***
 * @author xiaohui
 * @created 2020/4/1
 */
public class ClientHandler extends SimpleChannelInboundHandler<ByteBuffer> {

    public static void main(String[] args) {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuffer byteBuffer) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
