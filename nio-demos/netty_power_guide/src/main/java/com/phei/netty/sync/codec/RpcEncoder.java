package com.phei.netty.sync.codec;

import com.phei.netty.sync.msg.Message;
import com.phei.netty.sync.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 */
public class RpcEncoder extends MessageToByteEncoder<Message> {

    private Class<?> genericClass;

    public RpcEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Message in, ByteBuf out) throws Exception {

        byte[] data = SerializationUtil.serialize(in);
        out.writeInt(data.length);
        out.writeBytes(data);

    }

}
