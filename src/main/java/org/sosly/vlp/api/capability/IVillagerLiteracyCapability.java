package org.sosly.vlp.api.capability;

import net.minecraft.resources.ResourceLocation;
import org.sosly.vlp.VillagerLiteracyProgram;

/**
 * Capability for tracking extended villager data.
 */
public interface IVillagerLiteracyCapability {
    ResourceLocation KEY = new ResourceLocation(VillagerLiteracyProgram.MOD_ID, "villager-literacy");

    /**
     * Get the last tick that the villager leveled up.
     * @return The last tick that the villager leveled up.
     */
    long getLastLevelUpTick();
    /**
     * Set the last tick that the villager leveled up.
     * @param time The last tick that the villager leveled up.
     */
    void setLastLevelUpTick(long time);
}
