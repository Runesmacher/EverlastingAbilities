package org.cyclops.everlastingabilities;

import org.apache.logging.log4j.Level;
import org.cyclops.cyclopscore.config.ConfigurableProperty;
import org.cyclops.cyclopscore.config.ConfigurableType;
import org.cyclops.cyclopscore.config.ConfigurableTypeCategory;
import org.cyclops.cyclopscore.config.extendedconfig.DummyConfig;
import org.cyclops.cyclopscore.init.ModBase;
import org.cyclops.cyclopscore.tracking.Analytics;
import org.cyclops.cyclopscore.tracking.Versions;

/**
 * A config with general options for this mod.
 * @author rubensworks
 *
 */
public class GeneralConfig extends DummyConfig {

    /**
     * The current mod version, will be used to check if the player's config isn't out of date and
     * warn the player accordingly.
     */
    @ConfigurableProperty(category = ConfigurableTypeCategory.CORE, comment = "Config version for " + Reference.MOD_NAME +".\nDO NOT EDIT MANUALLY!")
    public static String version = Reference.MOD_VERSION;

    /**
     * If the debug mode should be enabled. @see Debug
     */
    @ConfigurableProperty(category = ConfigurableTypeCategory.CORE, comment = "Set 'true' to enable development debug mode. This will result in a lower performance!", requiresMcRestart = true)
    public static boolean debug = false;

    /**
     * If the recipe loader should crash when finding invalid recipes.
     */
    @ConfigurableProperty(category = ConfigurableTypeCategory.CORE, comment = "If the recipe loader should crash when finding invalid recipes.", requiresMcRestart = true)
    public static boolean crashOnInvalidRecipe = false;

    /**
     * If mod compatibility loader should crash hard if errors occur in that process.
     */
    @ConfigurableProperty(category = ConfigurableTypeCategory.CORE, comment = "If mod compatibility loader should crash hard if errors occur in that process.", requiresMcRestart = true)
    public static boolean crashOnModCompatCrash = false;

    /**
     * If an anonymous mod startup analytics request may be sent to our analytics service.
     */
    @ConfigurableProperty(category = ConfigurableTypeCategory.CORE, comment = "If an anonymous mod startup analytics request may be sent to our analytics service.")
    public static boolean analytics = true;

    /**
     * If the version checker should be enabled.
     */
    @ConfigurableProperty(category = ConfigurableTypeCategory.CORE, comment = "If the version checker should be enabled.")
    public static boolean versionChecker = true;

    /**
     * The maximum rarity of totems to spawn when a player first logs in. [0-3], -1 disables totem spawning.
     */
    @ConfigurableProperty(category = ConfigurableTypeCategory.GENERAL, comment = "The maximum rarity of totems to spawn when a player first logs in. [0-3], -1 disables totem spawning.")
    public static int totemMaximumSpawnRarity = 1;

    /**
     * How many abilities should be dropped on player death.
     */
    @ConfigurableProperty(category = ConfigurableTypeCategory.GENERAL, comment = "How many abilities should be dropped on player death.", isCommandable = true)
    public static int dropAbilitiesOnPlayerDeath = 1;

    /**
     * true: Abilities drop when players die; false: Abilities drop when players die by the hand of other players.
     */
    @ConfigurableProperty(category = ConfigurableTypeCategory.GENERAL, comment = "true: Abilities drop when players die; false: Abilities drop when players die by the hand of other players.", isCommandable = true)
    public static boolean alwaysDropAbilities = false;

    /**
     * 1/x chance for mobs to have abilities.
     */
    @ConfigurableProperty(category = ConfigurableTypeCategory.GENERAL, comment = "1/x chance for mobs to have abilities.", isCommandable = true)
    public static int mobAbilityChance = 50;

    /**
     * If the magnetize ability should move xp.
     */
    @ConfigurableProperty(category = ConfigurableTypeCategory.GENERAL, comment = "If the magnetize ability should move xp.", isCommandable = true)
    public static boolean magnetizeMoveXp = true;

    /**
     * The multiplier for ability XP requirement.
     */
    @ConfigurableProperty(category = ConfigurableTypeCategory.GENERAL, comment = "The multiplier for ability XP requirement", isCommandable = true)
    public static int abilityXpMultiplier = 10;

    /**
     * If players should have particle effects for the abilities they carry.
     */
    @ConfigurableProperty(category = ConfigurableTypeCategory.GENERAL, comment = "If players should have particle effects for the abilities they carry.", isCommandable = true)
    public static boolean showPlayerParticles = false;

    /**
     * The maximum amount of abilities a player can have, -1 is infinite.
     */
    @ConfigurableProperty(category = ConfigurableTypeCategory.GENERAL, comment = "The maximum amount of abilities a player can have, -1 is infinite.", isCommandable = true)
    public static int maxPlayerAbilities = -1;

    /**
     * The amount of exhaustion that should by applied to the player per active ability per second.
     */
    @ConfigurableProperty(category = ConfigurableTypeCategory.GENERAL, comment = "The amount of exhaustion that should by applied to the player per active ability per second.", isCommandable = true)
    public static double exhaustionPerAbilityTick = 0.01;

    /**
     * The type of this config.
     */
    public static ConfigurableType TYPE = ConfigurableType.DUMMY;

    /**
     * Create a new instance.
     */
    public GeneralConfig() {
        super(EverlastingAbilities._instance, true, "general", null, GeneralConfig.class);
    }

    @Override
    public void onRegistered() {
        // Check version of config file
        if(!version.equals(Reference.MOD_VERSION)) {
            getMod().log(Level.WARN, "The config file of " + Reference.MOD_NAME + " is out of date and might cause problems, please remove it so it can be regenerated.");
        }

        getMod().putGenericReference(ModBase.REFKEY_CRASH_ON_INVALID_RECIPE, GeneralConfig.crashOnInvalidRecipe);
        getMod().putGenericReference(ModBase.REFKEY_DEBUGCONFIG, GeneralConfig.debug);
        getMod().putGenericReference(ModBase.REFKEY_CRASH_ON_MODCOMPAT_CRASH, GeneralConfig.crashOnModCompatCrash);

        if(analytics) {
            Analytics.registerMod(getMod(), Reference.GA_TRACKING_ID);
        }
        if(versionChecker) {
            Versions.registerMod(getMod(), EverlastingAbilities._instance, Reference.VERSION_URL);
        }
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
}
