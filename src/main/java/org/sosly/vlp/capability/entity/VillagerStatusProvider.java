package org.sosly.vlp.capability.entity;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.sosly.vlp.api.capability.IVillagerStatus;

public class VillagerStatusProvider implements ICapabilitySerializable<Tag> {
    public static final Capability<IVillagerStatus> STATUS = CapabilityManager.get(new CapabilityToken<>() {});
    private final LazyOptional<IVillagerStatus> holder = LazyOptional.of(VillagerStatus::new);

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
        return STATUS.orEmpty(capability, holder);
    }

    @Override
    public Tag serializeNBT() {
        IVillagerStatus instance = holder.orElse(new VillagerStatus());
        CompoundTag tag = new CompoundTag();
        tag.putLong("lastLevelUpTick", instance.getLastLevelUpTick());
        return tag;
    }

    @Override
    public void deserializeNBT(Tag nbt) {
        if (nbt instanceof CompoundTag tag) {
            IVillagerStatus instance = holder.orElse(new VillagerStatus());
            instance.setLastLevelUpTick(tag.getLong("lastLevelUpTick"));
        }
    }
}
