package com.bulefire.neuracraft.init;

import com.bulefire.neuracraft.NeuraCraft;
import com.bulefire.neuracraft.config.Config;
import com.bulefire.neuracraft.config.yy.BaseInformation;
import com.bulefire.neuracraft.register.RegisterBlock;
import com.bulefire.neuracraft.register.RegisterCreativeModeTab;
import com.bulefire.neuracraft.register.RegisterItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.bulefire.neuracraft.NeuraCraft.MODID;

public class Init {
    public static void init(NeuraCraft n) {
        // 获取模组事件总线
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // 注册commonSetup方法以进行模组加载
        modEventBus.addListener(Init::commonSetup);

        // 将延迟注册表注册到模组事件总线，以便方块被注册
        RegisterBlock.BLOCKS.register(modEventBus);
        // 将延迟注册表注册到模组事件总线，以便物品被注册
        RegisterItem.ITEMS.register(modEventBus);
        // 将延迟注册表注册到模组事件总线，以便标签被注册
        RegisterCreativeModeTab.CREATIVE_MODE_TABS.register(modEventBus);

        // 注册我们自己以接收服务器和其他游戏事件
        MinecraftForge.EVENT_BUS.register(n);

        // 将物品注册到创造模式标签
        // modEventBus.addListener(this::addCreative);
        registerConfig();
        }

    private static void registerConfig(){
        // 注册我们的模组的ForgeConfigSpec，以便Forge可以为我们创建和加载配置文件
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BaseInformation.SPEC);

    }

    /**
     * 在模组加载时执行某些操作 例如初始化
     * @param event event
     */
    private static void commonSetup(final FMLCommonSetupEvent event) {
//        // 一些通用设置代码
//        logger.info("通用设置中你好");
//        logger.info("泥土方块 >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
//
//        if (Config.logDirtBlock)
//            logger.info("泥土方块 >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
//
//        logger.info(Config.magicNumberIntroduction + Config.magicNumber);
//
//        Config.items.forEach((item) -> logger.info("物品 >> {}", item.toString()));
//        logger.info("初始化银影AI设置 {}", BaseInformation.api_url);
    }

    // 将示例方块物品添加到建筑方块标签
//    private void addCreative(BuildCreativeModeTabContentsEvent event) {
//        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
//            event.accept(EXAMPLE_BLOCK_ITEM);
//    }
    // 可以使用SubscribeEvent并让事件总线发现带有@SubscribeEvent注解的方法

    /**
     * 服务器启动时执行某些操作
     * @param event event
     */
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // 当服务器启动时执行某些操作
        // logger.info("服务器启动中你好");
    }

    // 可以使用EventBusSubscriber自动注册带有@SubscribeEvent注解的所有静态方法
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // 一些客户端设置代码
//            logger.info("客户端设置中你好");
//            logger.info("MINECRAFT名称 >> {}", Minecraft.getInstance().getUser().getName());
        }
    }

}
