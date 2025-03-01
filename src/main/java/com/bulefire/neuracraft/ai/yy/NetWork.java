package com.bulefire.neuracraft.ai.yy;

import com.mojang.logging.LogUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.function.Supplier;

import static com.bulefire.neuracraft.NeuraCraft.MODID;

public class NetWork {
    private static final Logger log = LogUtils.getLogger();

    private static final String VERSION = "1.0";
    private static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(MODID, "yy-c"))
            .clientAcceptedVersions(VERSION::equals)
            .serverAcceptedVersions(VERSION::equals)
            .networkProtocolVersion(() -> VERSION)
            .simpleChannel();

    public static void registerMessage(){
        int id = 0;
        CHANNEL.registerMessage(id++,
                Message.class,
                NetWork::encode,
                NetWork::decode,
                NetWork::handle);
    }

    public static void encode(@NotNull Message message, @NotNull FriendlyByteBuf buffer) {
        buffer.writeUtf(message.message[0]);
        buffer.writeUtf(message.message[1]);
    }

    @Contract(pure = true)
    public static @NotNull Message decode(@NotNull FriendlyByteBuf buffer) {
        return new Message(buffer.readUtf(), buffer.readUtf());
    }

    public static void handle(Message message, @NotNull Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(() -> {
            // 处理任务
            String name = message.message[0];
            String toMessage = message.message[1];
            log.info("收到消息 | {} | {}",name,toMessage);
            //YY.onServer(name,toMessage);
        });
        ctx.get().setPacketHandled(true);
    }

    /**
     * 发送消息
     * @param name 名字
     * @param message 消息
     */
    public static void sendToServer(@NotNull String name, @NotNull String message){
        log.info("发送消息 | {} | {}",name,message);
        NetWork.CHANNEL.sendToServer(new Message(name, message));
    }



    public static class Message {
        private final String[] message;

        /**
         * 构造消息
         * @param name 名字
         * @param message 消息
         */
        public Message(String name, String message) {
            this.message = new String[]{name, message};
        }

        /**
         * 名字在前,信息在后
         * @param message 数组
         */
        public Message(String[] message) {
            this.message = message;
        }
    }
}

