package org.xrui.netty.echo;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter{
    private int counter = 0;
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg){
        String body = (String)msg;
        System.out.println("This is " + ++counter + " times receive client :[" + body +"]");
        body += "$_";
        ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
        ctx.writeAndFlush(echo);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx){
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) {
        //Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}

