package com.bulefire.neuracraft.ai.yy;

import com.bulefire.neuracraft.config.yy.BaseInformation;
import com.bulefire.neuracraft.util.SendMessageToChatBar;
import com.mojang.logging.LogUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

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
    public static void onChat(@NotNull String name, @NotNull String message){
        log.info("player send chat: {}", message);
        // 发送消息给AI
        String msg = getMessage(message);
        log.info("player send to ai is: {}", msg);
        String repose = YY.chat(name, msg);
        log.info("ai reply is: {}", repose);
        // 发送AI回复给玩家
        SendMessageToChatBar.sendChatMessage(BaseInformation.show_name, repose);
    }

    @Contract(pure = true)
    private static @NotNull String getMessage(@NotNull String messages){
        return messages.replace("AI","");
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
        Client c = clientManger.getClient(chatName);
        // 发送消息给AI,并获取回复
        return c.sendMessage(message);
    }
}
