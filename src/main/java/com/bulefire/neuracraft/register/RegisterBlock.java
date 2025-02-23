package com.bulefire.neuracraft.register;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.bulefire.neuracraft.NeuraCraft.MODID;

public class RegisterBlock {
    // 创建一个延迟注册表来保存所有将在"neuracraft"命名空间下注册的方块
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
}
