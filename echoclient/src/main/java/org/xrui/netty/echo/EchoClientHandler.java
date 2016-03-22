package org.xrui.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class EchoClientHandler extends ChannelInboundHandlerAdapter{
    private int counter = 0;

    static final String ECHO_REQ = "Hi, I am xiongjian!$_";

    public EchoClientHandler(){
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        for (int i =0; i< 10; i++)
        {
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg){
        System.out.println("This is " + ++counter + " times receive server : ["+ msg + "]");
    }

    @Override 
    public void channelReadComplete(ChannelHandlerContext ctx)
    {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
