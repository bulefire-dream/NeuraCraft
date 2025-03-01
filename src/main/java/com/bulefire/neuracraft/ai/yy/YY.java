package com.bulefire.neuracraft.ai.yy;

import com.bulefire.neuracraft.config.yy.BaseInformation;
import com.bulefire.neuracraft.util.SendMessageToChatBar;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import static com.bulefire.neuracraft.NeuraCraft.MODID;

public class YY {
    private static final Logger log = LogUtils.getLogger();

    private static final ClientManger clientManger;
    static{
        clientManger = new ClientManger();
    }

    /**
     * 监听聊天事件
     * @param message 消息
     */
    public static void onChat(@NotNull String name, @NotNull String message, ServerChatEvent s, ClientChatEvent c){
        log.info("player send chat: {}", message);
        // 判断运行环境
        DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> {
            if (isSinglePlayer()) {
                // 单人游戏
                log.info("单人游戏");
                onClient(name,message);
            }else if (serverHasMod()){
                // 服务端有模组
                // 让服务端处理
                log.info("server has mod, give up deal it, give control to the server");
                // NetWork.sendToServer(name, message);
                return null;
            }else {
                // 服务端没有模组,本地处理
                log.warn("server has no mod,local deal with");
                onClient(name, message);
            }
            return null;
        });
        DistExecutor.unsafeCallWhenOn(Dist.DEDICATED_SERVER, () -> () -> {
            log.info("server catch the chat");
            onServer(s,name,message);
            return null;
        });
    }

    private static boolean isSinglePlayer() {
        return Minecraft.getInstance().isSingleplayer();
    }

    public static void onClient(@NotNull String name, @NotNull String message){
        log.warn("deal in client");
        String repose = dealWith(name, message);
        if (Minecraft.getInstance().player != null) {
            SendMessageToChatBar.sendChatMessage(Minecraft.getInstance().player,BaseInformation.show_name, repose);
        }else {
            throw new RuntimeException("Minecraft.getInstance().player is null");
        }
    }

    public static void onServer(@NotNull ServerChatEvent event, @NotNull String name, @NotNull String message){
        log.warn("deal in server");
        String repose = dealWith(name, message);
        SendMessageToChatBar.sendChatMessage(event.getPlayer(),BaseInformation.show_name, repose);
    }

    private static @NotNull String dealWith(@NotNull String name, @NotNull String message){
        // 发送消息给AI
        String msg = getMessage(message);
        log.info("player send to ai is: {}", msg);
        String repose = YY.chat(name, msg);
        log.info("ai reply is: {}", repose);
        // 发送AI回复给玩家
        return repose;
    }

    @Contract(pure = true)
    private static @NotNull String getMessage(@NotNull String messages){
        return messages.replace("AI","");
    }

    private static boolean serverHasMod(){
        return ModList.get().isLoaded(MODID);
    }

    /**
     * 聊天
     * @param message 用户的消息
     * @return AI的回复
     */
    public static @NotNull String chat(@NotNull String username, @NotNull String message) {
        // 获取聊天室名称
        String chatName = NameManger.getChatName(username);
        log.info("get player {} in chat name: {}",username ,chatName);
        // 获取聊天室
        ChatRoom c = clientManger.getClient(chatName);
        // 发送消息给AI,并获取回复
        return c.sendMessage(message);
    }
}
