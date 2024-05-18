package org.sosly.vlp.blocks;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import org.sosly.vlp.VillagerLiteracyProgram;
import org.sosly.vlp.api.VLPRegistries;
import org.sosly.vlp.blocks.workstations.MetalworkerStationBlock;

@Mod.EventBusSubscriber(modid = VillagerLiteracyProgram.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VLPBlocks {
    public static RegistryObject<MetalworkerStationBlock> METALWORKER_STATION;

    static {
        METALWORKER_STATION = VLPRegistries.BLOCKS.register("metalworker_station", MetalworkerStationBlock::new);
    }
}
