package com.bulefire.neuracraft.ai.yy;

import java.util.HashMap;
import java.util.Map;

public class ClientManger {
    /**
     * 客户端表
     */
    private final Map<String,Client> clients;

    ClientManger(){
        // 初始化客户端表
        clients = new HashMap<>();
        // 初始化一个公共聊天室
        clients.put("public",new Client("public"));
    }

    /**
     * 获取一个客户端聊天室
     * @param chatName 聊天室名称
     * @return 聊天室实例
     */
    public Client getClient(String chatName){
        if (!clients.containsKey(chatName)){
            createClient(chatName);
        }
        // 返回客户端
        return clients.get(chatName);
    }

    public boolean createClient(String chatName){
        // 如果客户端表里已经有这个客户端了，就返回false
        if(clients.containsKey(chatName)){
            return false;
        }
        // 创建一个客户端
        clients.put(chatName,new Client(chatName));
        // 返回true
        return true;
    }
}