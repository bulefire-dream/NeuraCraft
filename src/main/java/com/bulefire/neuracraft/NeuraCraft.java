package com.bulefire.neuracraft;

import com.bulefire.neuracraft.config.Config;
import com.bulefire.neuracraft.config.yy.BaseInformation;
import com.bulefire.neuracraft.init.Init;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// 模组ID应与META-INF/mods.toml文件中的条目匹配
@Mod(NeuraCraft.MODID)
public class NeuraCraft {
    // 在一个公共地方定义模组ID，供所有地方引用
    /**
     * 模组ID
     */
    public static final String MODID = "neuracraft";
    // 直接引用一个slf4j日志记录器
    private static final Logger logger = LogUtils.getLogger();
//    // 创建一个新的方块，其ID为"neuracraft:example_block"，结合命名空间和路径
//    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
//    // 创建一个新的方块物品，其ID为"neuracraft:example_block"，结合命名空间和路径
//    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));
//
//    // 创建一个新的食物物品，其ID为"neuracraft:example_id"，营养值为1，饱和度为2
//    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(1).saturationMod(2f).build())));
//
//    // 创建一个创造模式标签，其ID为"neuracraft:example_tab"，用于示例物品，位于战斗标签之后
//    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder().withTabsBefore(CreativeModeTabs.COMBAT).icon(() -> EXAMPLE_ITEM.get().getDefaultInstance()).displayItems((parameters, output) -> {
//        output.accept(EXAMPLE_ITEM.get()); // 将示例物品添加到标签中。对于自己的标签，建议使用此方法而不是事件
//    }).build());

    /**
     * 模组构造函数(初始化)
     */
    public NeuraCraft() {
        Init.init(this);
    }
}
