package org.sosly.vlp.entities;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import org.sosly.vlp.VillagerLiteracyProgram;
import org.sosly.vlp.api.VLPRegistries;

import java.util.Set;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = VillagerLiteracyProgram.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VLPPoiTypes {
    public static RegistryObject<PoiType> METALWORKER;

    static {
        METALWORKER = VLPRegistries.POI_TYPES.register("metalworker_poi", getPoiTypeSupplier(() -> Blocks.FURNACE));
    }

    private static Set<BlockState> getBlockStates(Block block) {
        return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
    }

    private static Supplier<? extends PoiType> getPoiTypeSupplier(Supplier<? extends Block> block) {
        return () -> new PoiType(getBlockStates(block.get()), 1, 1);
    }
}
