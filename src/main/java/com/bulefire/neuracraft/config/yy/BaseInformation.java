package com.bulefire.neuracraft.config.yy;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.ForgeConfigSpec;
import org.slf4j.Logger;

public class BaseInformation {
    private static final Logger log = LogUtils.getLogger();

    // 构建器 Builder
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // API地址
    public static final ForgeConfigSpec.ConfigValue<String> API_URL;
    // API 接口
    public static final ForgeConfigSpec.ConfigValue<String> API_INTERFACE;
    // Token
    public static final ForgeConfigSpec.ConfigValue<String> TOKEN;
    // appid
    public static final ForgeConfigSpec.ConfigValue<String> APPID;
    // model
    public static final ForgeConfigSpec.ConfigValue<String> MODEL;
    // systemPrompt
    public static final ForgeConfigSpec.ConfigValue<String> SYSTEM_PROMPT;
    // show name
    public static final ForgeConfigSpec.ConfigValue<String> SHOW_NAME;

    static{
        BUILDER.push("银影AI 设置");

        API_URL = BUILDER.comment("API URL").define("apiUrl", "https://api-yinying-ng.wingmark.cn/v1");
        API_INTERFACE = BUILDER.comment("API 接口").define("apiInterface", "/chatWithCyberFurry");
        TOKEN = BUILDER.comment("Token").define("token", "yinying-XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        APPID = BUILDER.comment("AppID").define("appid", "xxxxxxxxx");
        MODEL = BUILDER.comment("Model").define("model", "yinyingllm-latest");
        SYSTEM_PROMPT = BUILDER.comment("System Prompt").define("systemPrompt", "你的名字叫银影，是翎迹网络开发的仿生灰狼");
        SHOW_NAME = BUILDER.comment("显示名称").define("showName", "银影");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    public BaseInformation(){

    }

    public static String api_url;
    public static String api_interface;
    public static String token;
    public static String appid;
    public static String model;
    public static String system_prompt;
    public static String show_name;

    public void init(){
        // log.info("初始化银影AI设置");
        api_url = API_URL.get();
        api_interface = API_INTERFACE.get();
        token = TOKEN.get();
        appid = APPID.get();
        model = MODEL.get();
        system_prompt = SYSTEM_PROMPT.get();
        show_name = SHOW_NAME.get();
    }
}
