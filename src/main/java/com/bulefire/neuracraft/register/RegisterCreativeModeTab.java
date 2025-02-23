package com.bulefire.neuracraft.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;

import static com.bulefire.neuracraft.NeuraCraft.MODID;

public class RegisterCreativeModeTab {
    // 创建一个延迟注册表来保存所有将在"neuracraft"命名空间下注册的创造模式标签
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
}
