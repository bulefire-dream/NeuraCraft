package com.bulefire.neuracraft.util;

import com.bulefire.neuracraft.NeuraCraft;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
    public static void sendChatMessage(@NotNull ServerPlayer player, @NotNull String name, @NotNull String message){
        // 构建消息
        MutableComponent cm = Component.translatable("<%s> %s", name, message);
        // 发送消息
        player.sendSystemMessage(cm);
    }

    public static void sendChatMessage(@NotNull LocalPlayer player, @NotNull String name, @NotNull String message){
        // 构建消息
        MutableComponent cm = Component.translatable("<%s> %s", name, message);
        // 发送消息
        player.sendSystemMessage(cm);
    }

    @SubscribeEvent
    @OnlyIn(Dist.DEDICATED_SERVER)
    public static void onServerChat(@NotNull ServerChatEvent event, @NotNull String name, @NotNull String message) {
        ServerPlayer player = event.getPlayer();
        sendChatMessage(player,name,message);
    }
}
