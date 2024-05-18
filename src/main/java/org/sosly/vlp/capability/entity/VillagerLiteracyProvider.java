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
import org.sosly.vlp.api.capability.IVillagerLiteracyCapability;

public class VillagerLiteracyProvider implements ICapabilitySerializable<Tag> {
    public static final Capability<IVillagerLiteracyCapability> VILLAGER_LITERACY = CapabilityManager.get(new CapabilityToken<>() {});
    private final LazyOptional<IVillagerLiteracyCapability> holder = LazyOptional.of(VillagerLiteracyCapability::new);

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
        return VILLAGER_LITERACY.orEmpty(capability, holder);
    }

    @Override
    public Tag serializeNBT() {
        IVillagerLiteracyCapability instance = holder.orElse(new VillagerLiteracyCapability());
        CompoundTag tag = new CompoundTag();
        tag.putLong("lastLevelUpTick", instance.getLastLevelUpTick());
        return tag;
    }

    @Override
    public void deserializeNBT(Tag nbt) {
        if (nbt instanceof CompoundTag tag) {
            IVillagerLiteracyCapability instance = holder.orElse(new VillagerLiteracyCapability());
            instance.setLastLevelUpTick(tag.getLong("lastLevelUpTick"));
        }
    }
}
