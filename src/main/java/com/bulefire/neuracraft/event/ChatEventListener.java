package com.bulefire.neuracraft.event;

import com.bulefire.neuracraft.NeuraCraft;
import com.bulefire.neuracraft.ai.yy.YY;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = NeuraCraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ChatEventListener {
    private static final Logger log = LogUtils.getLogger();

    @SubscribeEvent
    public static void onChat(@NotNull ClientChatEvent event){
        // log.info("player send chat");
        // 获取消息文本
        String message = event.getMessage();
        // 获取玩家名称
        String name;
        if (Minecraft.getInstance().player != null) {
            name = Minecraft.getInstance().player.getName().getString();
        }else{
            throw new RuntimeException("Minecraft.getInstance().player is null");
        }

        // to AI
        //List<String> key = List.of("银影","YY","yy","y","Y","AI","ai","Ai","aI","A","a","I","i");
        List<String> key = List.of("AI");
        // log.info(message);
        if (key.stream().anyMatch(message::contains)){
            CompletableFuture.runAsync(() -> {
                try {
                    YY.onChat(name, message);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
