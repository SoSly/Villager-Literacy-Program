package org.sosly.vlp.entities;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import org.sosly.vlp.VillagerLiteracyProgram;
import org.sosly.vlp.api.VLPRegistries;

import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = VillagerLiteracyProgram.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VLPVillagerProfessions {
    public static RegistryObject<VillagerProfession> METALWORKER;

    static {
        METALWORKER = VLPRegistries.VILLAGER_PROFESSIONS.register("metalworker", () ->
                new VillagerProfession("metalworker", handlePoiType(VLPPoiTypes.METALWORKER),
                        handlePoiType(VLPPoiTypes.METALWORKER), ImmutableSet.of(), ImmutableSet.of(),
                        SoundEvents.VILLAGER_WORK_ARMORER));
    }

    private static Predicate<Holder<PoiType>> handlePoiType(RegistryObject<PoiType> poiType) {
        return x -> x.get() == poiType.get();
    }
}