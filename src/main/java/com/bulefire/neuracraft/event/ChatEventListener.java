package com.bulefire.neuracraft.event;

import com.bulefire.neuracraft.NeuraCraft;
import com.bulefire.neuracraft.ai.yy.YY;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = NeuraCraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChatEventListener {
    private static final Logger log = LogUtils.getLogger();

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onChat(@NotNull ClientChatEvent event){
        log.info("client catch player send chat");
        // 获取消息文本
        String message = event.getMessage();
        // 获取玩家名称
        String name;
        if (Minecraft.getInstance().player != null) {
            name = Minecraft.getInstance().player.getName().getString();
        }else{
            throw new RuntimeException("Minecraft.getInstance().player is null");
        }

        catchChat(name,message,null,event);
    }

    @SubscribeEvent
    @OnlyIn(Dist.DEDICATED_SERVER)
    public static void onServerChat(@NotNull ServerChatEvent event){
        log.info("server catch player send chat");
        // 获取消息文本
        String message = event.getMessage().getString();
        // 获取玩家名称
        String name;
        if (event.getPlayer() != null) {
            name = event.getPlayer().getName().getString();
        }else{
            throw new RuntimeException("Minecraft.getInstance().player is null");
        }

        catchChat(name,message,event,null);
    }

    private static void catchChat(String name, @NotNull String message, ServerChatEvent s, ClientChatEvent c){
        // to AI
        //List<String> key = List.of("银影","YY","yy","y","Y","AI","ai","Ai","aI","A","a","I","i");
        List<String> key = List.of("AI");
        // log.info(message);
        if (key.stream().anyMatch(message::contains)){
            log.info("catch player send chat to AI");
            CompletableFuture.runAsync(() -> {
                try {
                    YY.onChat(name, message,s,c);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
