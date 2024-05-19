package org.sosly.vlp.blocks.workstations;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class MetalworkerStationBlock extends Block {
    public MetalworkerStationBlock() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.BASS).noOcclusion().strength(3.0F));
    }
}
