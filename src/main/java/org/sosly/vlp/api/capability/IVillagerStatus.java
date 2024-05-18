package org.sosly.vlp.api.capability;

import net.minecraft.resources.ResourceLocation;
import org.sosly.vlp.VillagerLiteracyProgram;

/**
 * Capability for tracking extended villager data.
 */
public interface IVillagerStatus {
    ResourceLocation CAPABILITY = new ResourceLocation(VillagerLiteracyProgram.MOD_ID, "status");

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
