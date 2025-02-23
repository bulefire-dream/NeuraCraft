package com.bulefire.neuracraft.register;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.bulefire.neuracraft.NeuraCraft.MODID;

public class RegisterItem {
    // 创建一个延迟注册表来保存所有将在"neuracraft"命名空间下注册的物品
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

}
