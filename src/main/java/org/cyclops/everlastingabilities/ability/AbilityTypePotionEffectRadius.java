package org.cyclops.everlastingabilities.ability;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntitySelectors;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import org.cyclops.cyclopscore.helper.MinecraftHelpers;
import org.cyclops.everlastingabilities.EverlastingAbilities;

import java.util.List;

/**
 * Ability type for potion effects in an area.
 * @author rubensworks
 */
public class AbilityTypePotionEffectRadius extends AbilityTypeDefault {

    private static final int TICK_MODULUS = MinecraftHelpers.SECOND_IN_TICKS / 2;

    private final Potion potion;

    public AbilityTypePotionEffectRadius(String id, int rarity, int maxLevel, int baseXpPerLevel, Potion potion) {
        super(id, rarity, maxLevel, baseXpPerLevel);
        this.potion = potion;
        if (this.potion == null) {
            EverlastingAbilities.clog(Level.WARN, "Tried to register a null potion for ability " + id + ". This is possibly caused by a mod forcefully removing the potion effect for this ability.");
        }
    }

    protected int getDurationMultiplier() {
        return 6;
    }

    @Override
    public void onTick(EntityPlayer player, int level) {
        World world = player.worldObj;
        if (potion != null && !world.isRemote && player.worldObj.getWorldTime() % TICK_MODULUS == 0) {
            int radius = level * 2;
            List<EntityLivingBase> mobs = world.getEntitiesWithinAABB(EntityLivingBase.class,
                    player.getEntityBoundingBox().expandXyz(radius), EntitySelectors.NOT_SPECTATING);
            for (EntityLivingBase mob : mobs) {
                if (mob != player && (!(mob instanceof IEntityOwnable) || ((IEntityOwnable) mob).getOwner() != player) && !player.isOnSameTeam(mob)) {
                    mob.addPotionEffect(
                            new PotionEffect(potion, TICK_MODULUS * getDurationMultiplier(), level - 1, true, false));
                }
            }
        }
    }
}
