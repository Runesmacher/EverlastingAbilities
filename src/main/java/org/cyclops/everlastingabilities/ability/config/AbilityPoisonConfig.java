package org.cyclops.everlastingabilities.ability.config;

import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import org.cyclops.cyclopscore.config.ConfigurableProperty;
import org.cyclops.cyclopscore.config.ConfigurableTypeCategory;
import org.cyclops.cyclopscore.config.configurable.IConfigurable;
import org.cyclops.everlastingabilities.ability.AbilityTypePotionEffectRadius;
import org.cyclops.everlastingabilities.core.config.extendedconfig.AbilityConfig;

/**
 * Config for an ability.
 * @author rubensworks
 *
 */
public class AbilityPoisonConfig extends AbilityConfig {

    /**
     * Rarity of this ability.
     */
    @ConfigurableProperty(category = ConfigurableTypeCategory.GENERAL, categoryRaw = "ability", comment = "Rarity of this ability.", requiresMcRestart = true)
    public static int rarity = EnumRarity.EPIC.ordinal();
    /**
     * The maximum ability level.
     */
    @ConfigurableProperty(category = ConfigurableTypeCategory.GENERAL, categoryRaw = "ability", comment = "The maximum ability level.", requiresMcRestart = true)
    public static int maxLevel = 3;
    /**
     * The xp required per level.
     */
    @ConfigurableProperty(category = ConfigurableTypeCategory.GENERAL, categoryRaw = "ability", comment = "The xp required per level.", requiresMcRestart = true)
    public static int xpPerLevel = 100;

    /**
     * The unique instance.
     */
    public static AbilityConfig _instance;

    /**
     * Make a new instance.
     */
    public AbilityPoisonConfig() {
        super(
                true,
                "poison",
                "Entities in the area become poisoned"
        );
    }

    @Override
    protected IConfigurable initSubInstance() {
        return new AbilityTypePotionEffectRadius(getNamedId(), rarity, maxLevel, xpPerLevel, MobEffects.POISON);
    }
}
