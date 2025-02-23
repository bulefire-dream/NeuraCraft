package com.bulefire.neuracraft.util;

import com.bulefire.neuracraft.NeuraCraft;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = NeuraCraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class SendMessageToChatBar {
    private static final Logger log = LogUtils.getLogger();

    /**
     * 向聊天框发送消息
     * @param name 发送者名字,可为空,不可为null
     * @param message 消息
     */
    public static void sendChatMessage(@NotNull String name, @NotNull String message){
        if (Minecraft.getInstance().player != null){
            // 构建消息
            MutableComponent cm = Component.translatable("<%s> %s", name, message);
            // 发送消息
            Minecraft.getInstance().player.sendSystemMessage(cm);
        }else{
            // 无人在线或者未启动完全
            log.error("Minecraft.getInstance().player is null");
        }
    }
}
