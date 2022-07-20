package com.fakechat.practice.service;


import com.fakechat.practice.adapter.ContactListAdapter;
import com.fakechat.practice.model.Friend;
import com.fakechat.practice.service.config.Config;
import com.fakechat.practice.service.message.ChatRequestMessage;
import com.fakechat.practice.service.message.ChatResponseMessage;
import com.fakechat.practice.service.message.GroupChatRequestMessage;
import com.fakechat.practice.service.message.GroupChatResponseMessage;
import com.fakechat.practice.service.message.GroupCreateRequestMessage;
import com.fakechat.practice.service.message.GroupJoinRequestMessage;
import com.fakechat.practice.service.message.GroupMembersRequestMessage;
import com.fakechat.practice.service.message.GroupQuitRequestMessage;
import com.fakechat.practice.service.message.LoginRequestMessage;
import com.fakechat.practice.service.message.LoginResponseMessage;
import com.fakechat.practice.service.protocol.MessageCodecSharable;
import com.fakechat.practice.service.protocol.ProcotolFrameDecoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;


public class IMClient {


    private static final String log = "ChatClient";
    private static ChannelHandlerContext ctx2 = null;



    private IMClient() {
    }

    public static synchronized IMClient getInstance() {
        return SingletonHolder.instance;
    }


    private static class SingletonHolder {
        private static final IMClient instance = new IMClient();
    }

    public void sendMessage(String from,String to ,String text) {
        ctx2.writeAndFlush(new ChatRequestMessage(from, to, text));
    }

    public void receivedMessage() {

    }

    public static void start(String username) {

        NioEventLoopGroup group = new NioEventLoopGroup();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        CountDownLatch WAIT_FOR_LOGIN = new CountDownLatch(1);
        AtomicBoolean LOGIN = new AtomicBoolean(false);
        AtomicBoolean EXIT = new AtomicBoolean(false);
        Scanner scanner = new Scanner(System.in);
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(group);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ProcotolFrameDecoder());
                    ch.pipeline().addLast(MESSAGE_CODEC);
                    // 用来判断是不是 读空闲时间过长，或 写空闲时间过长
                    // 3s 内如果没有向服务器写数据，会触发一个 IdleState#WRITER_IDLE 事件
                    ch.pipeline().addLast(new IdleStateHandler(0, 3, 0));
                    // ChannelDuplexHandler 可以同时作为入站和出站处理器
                    ch.pipeline().addLast(new ChannelDuplexHandler() {
                        // 用来触发特殊事件
                        @Override
                        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                            IdleStateEvent event = (IdleStateEvent) evt;
                            // 触发了写空闲事件
                            if (event.state() == IdleState.WRITER_IDLE) {
                                //log.debug("3s 没有写数据了，发送一个心跳包");
//                                ctx.writeAndFlush(new PingMessage());
                            }
                        }
                    });
                    ch.pipeline().addLast("client handler", new ChannelInboundHandlerAdapter() {
                        // 接收响应消息
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            System.out.println(msg);
                            if ((msg instanceof LoginResponseMessage)) {
                                LoginResponseMessage response = (LoginResponseMessage) msg;
                                System.out.println(response.getReason());
                            } else if (msg instanceof ChatResponseMessage) {
                                ChatResponseMessage response = (ChatResponseMessage) msg;
                                System.out.println(response.getFrom() + ":" + response.getContent());
                                if (listener != null) {
                                    listener.onReceiveMessageListener(response);
                                }
                            } else if (msg instanceof GroupChatResponseMessage) {
                                GroupChatResponseMessage response = (GroupChatResponseMessage) msg;
                                System.out.println(response.getFrom() + ":" + response.getContent());

                            }


                        }

                        // 在连接建立后触发 active 事件
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            LoginRequestMessage message = new LoginRequestMessage(username, "123");
                            ctx.writeAndFlush(message);
                            ctx2 = ctx;
                        }

                        // 在连接断开时触发
                        @Override
                        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                            System.out.println("连接已经断开，按任意键退出..");
                        }

                        // 在出现异常时触发
                        @Override
                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                            System.out.println("连接已经断开，按任意键退出..{}" + cause.getMessage());
                        }
                    });
                }
            });
            Channel channel = bootstrap.connect(Config.host, Config.port).sync().channel();
            channel.closeFuture().sync();



        } catch (Exception e) {
            System.out.println("client error" + e);
        } finally {
            group.shutdownGracefully();
        }
    }

    private static ReceiveMessageListener listener;

    public void setReceiveMessageListener(ReceiveMessageListener listener) {
        this.listener = listener;
    }

    public interface ReceiveMessageListener {
        void onReceiveMessageListener(ChatResponseMessage msg);
    }

}
