package org.sosly.vlp.api;


import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.sosly.vlp.VillagerLiteracyProgram;

public class VLPRegistries {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister
            .create(ForgeRegistries.BLOCKS, VillagerLiteracyProgram.MOD_ID);
    public static DeferredRegister<PoiType> POI_TYPES = DeferredRegister
            .create(ForgeRegistries.POI_TYPES, VillagerLiteracyProgram.MOD_ID);
    public static DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister
            .create(ForgeRegistries.VILLAGER_PROFESSIONS, VillagerLiteracyProgram.MOD_ID);
}
