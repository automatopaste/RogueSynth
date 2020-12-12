package data.scripts.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.LabelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import data.scripts.campaign.RS_VariantManager;
import org.lwjgl.util.vector.Vector2f;

import java.awt.Color;
import java.util.*;

public class RS_BaseVariantHullmod extends BaseHullMod {
    public enum VARIANT_RARITY {
        NULL,
        STANDARD,
        ALPHA,
        ULTRA,
        MYTHICAL
    }
    protected VARIANT_RARITY rarity;
    protected RS_VariantManager.VariantStats variantStats;

    private static final Color NULL_COLOR = new Color(150, 150, 150, 255);
    private static final Color NULL_HIGHLIGHT_COLOR = new Color(220, 220, 220, 255);
    private static final Color STANDARD_COLOR = new Color(54, 97, 201, 255);
    private static final Color STANDARD_HIGHLIGHT_COLOR = new Color(0, 105, 255, 255);
    private static final Color ALPHA_COLOR = new Color(69, 193, 200, 255);
    private static final Color ALPHA_HIGHLIGHT_COLOR = new Color(0, 232, 255, 255);
    private static final Color ULTRA_COLOR = new Color(212, 117, 47, 255);
    private static final Color ULTRA_HIGHLIGHT_COLOR = new Color(255, 124, 0, 255);
    private static final Color MYTHICAL_COLOR = new Color(80, 207, 59, 255);
    private static final Color MYTHICAL_HIGHLIGHT_COLOR = new Color(41, 255, 0, 255);

    private static final Map<VARIANT_RARITY, Color> RARITY_COLOUR = new HashMap<>();
    static {
        RARITY_COLOUR.put(VARIANT_RARITY.NULL, NULL_COLOR);
        RARITY_COLOUR.put(VARIANT_RARITY.STANDARD, STANDARD_COLOR);
        RARITY_COLOUR.put(VARIANT_RARITY.ALPHA, ALPHA_COLOR);
        RARITY_COLOUR.put(VARIANT_RARITY.ULTRA, ULTRA_COLOR);
        RARITY_COLOUR.put(VARIANT_RARITY.MYTHICAL, MYTHICAL_COLOR);
    }

    private static final Map<VARIANT_RARITY, Color> RARITY_HIGHLIGHT_COLOUR = new HashMap<>();
    static {
        RARITY_HIGHLIGHT_COLOUR .put(VARIANT_RARITY.NULL, NULL_HIGHLIGHT_COLOR);
        RARITY_HIGHLIGHT_COLOUR.put(VARIANT_RARITY.STANDARD, STANDARD_HIGHLIGHT_COLOR);
        RARITY_HIGHLIGHT_COLOUR.put(VARIANT_RARITY.ALPHA, ALPHA_HIGHLIGHT_COLOR);
        RARITY_HIGHLIGHT_COLOUR.put(VARIANT_RARITY.ULTRA, ULTRA_HIGHLIGHT_COLOR);
        RARITY_HIGHLIGHT_COLOUR.put(VARIANT_RARITY.MYTHICAL, MYTHICAL_HIGHLIGHT_COLOR);
    }

    private static final Color DEBUFF_HIGHLIGHT_COLOUR = new Color(191, 0, 8, 255);

    private static final Map<VARIANT_RARITY, String> VARIANT_ICON = new HashMap<>();
    static {
        VARIANT_ICON.put(VARIANT_RARITY.NULL, "graphics/icons/variants/RS_null_icon.png");
        VARIANT_ICON.put(VARIANT_RARITY.STANDARD, "graphics/icons/variants/RS_standard_icon.png");
        VARIANT_ICON.put(VARIANT_RARITY.ALPHA, "graphics/icons/variants/RS_alpha_icon.png");
        VARIANT_ICON.put(VARIANT_RARITY.ULTRA, "graphics/icons/variants/RS_ultra_icon.png");
        VARIANT_ICON.put(VARIANT_RARITY.MYTHICAL, "graphics/icons/variants/RS_mythical_icon.png");
    }
    private static final Vector2f iconSize = new Vector2f(64f, 64f);

    protected String variantDesignation;
    protected String flavourText;
    protected String additionalInfo;
    protected Color infoColour;
    protected boolean isStubText = false;

    protected boolean doJitterUnder;
    protected boolean doWeaponGlow;
    protected boolean doVentColour;
    protected boolean doEngineColourShift;

    @Override
    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        Map<RS_VariantManager.VariantStats.StatType, Float> statValues = variantStats.getStats();
        for (RS_VariantManager.VariantStats.StatType statType : statValues.keySet()) {
            Float mod = statValues.get(statType);
            if (mod == null) continue;

            switch (statType) {
                case LINEAR_ACC:
                    stats.getAcceleration().modifyPercent(id, mod);
                    break;
                case TURN_ACC:
                    stats.getTurnAcceleration().modifyPercent(id, mod);
                    break;
                case TOP_SPEED:
                    stats.getMaxSpeed().modifyPercent(id, mod);
                    break;
                case ZERO_FLUX_BOOST:
                    stats.getZeroFluxSpeedBoost().modifyFlat(id, mod);
                    break;
                case ENGINE_HEALTH:
                    stats.getEngineHealthBonus().modifyPercent(id, mod);
                    break;
                case FLUX_DISSIPATION:
                    stats.getFluxDissipation().modifyPercent(id, mod);
                    break;
                case FLUX_CAPACITY:
                    stats.getFluxCapacity().modifyPercent(id, mod);
                    break;
                case VENT_RATE:
                    stats.getVentRateMult().modifyPercent(id, mod);
                    break;
                case MIN_LEVEL_ZERO_FLUX_BOOST:
                    stats.getZeroFluxMinimumFluxLevel().modifyFlat(id, mod);
                    break;
                case ECCM_CHANCE:
                    stats.getEccmChance().modifyPercent(id, mod);
                    break;
                case EMP_RECEIVED:
                    stats.getEmpDamageTakenMult().modifyPercent(id, mod);
                    break;
                case SIGHT_RADIUS:
                    stats.getSightRadiusMod().modifyPercent(id, mod);
                    break;
                case TIME_MULT:
                    stats.getTimeMult().modifyPercent(id, mod);
                    break;
                case CR_DECAY_SPEED:
                    stats.getCRLossPerSecondPercent().modifyPercent(id, mod);
                    break;
                case MALFUNCTION_CHANCE:
                    stats.getCriticalMalfunctionChance().modifyPercent(id, mod);
                    stats.getEngineMalfunctionChance().modifyPercent(id, mod);
                    //stats.getShieldMalfunctionChance().modifyPercent(id, mod);
                    stats.getWeaponMalfunctionChance().modifyPercent(id, mod);
                    break;
                case HULL:
                    stats.getHullBonus().modifyPercent(id, mod);
                    break;
                case ARMOUR:
                    stats.getArmorBonus().modifyFlat(id, mod);
                    break;
                case BREAK_PROB:
                    stats.getBreakProb().modifyPercent(id, mod);
                    break;
                case MOUNT_HEALTH:
                    stats.getWeaponHealthBonus().modifyPercent(id, mod);
                    break;
                case BALLISTIC_RANGE:
                    stats.getBallisticWeaponRangeBonus().modifyPercent(id, mod);
                    break;
                case ENERGY_RANGE:
                    stats.getEnergyWeaponRangeBonus().modifyPercent(id, mod);
                    break;
                case PD_RANGE:
                    stats.getNonBeamPDWeaponRangeBonus().modifyPercent(id, mod);
                    stats.getBeamPDWeaponRangeBonus().modifyPercent(id, mod);
                    break;
                case BALLISTIC_AMMO:
                    stats.getBallisticAmmoBonus().modifyPercent(id, mod);
                    break;
                case BALLISTIC_DAMAGE:
                    stats.getBallisticWeaponDamageMult().modifyPercent(id, mod);
                    break;
                case BALLISTIC_ROF:
                    stats.getBallisticRoFMult().modifyPercent(id, mod);
                    break;
                case BALLISTIC_FLUX_COST:
                    stats.getBallisticWeaponFluxCostMod().modifyPercent(id, mod);
                case ENERGY_AMMO:
                    stats.getEnergyAmmoBonus().modifyPercent(id, mod);
                    break;
                case ENERGY_DAMAGE:
                    stats.getEnergyWeaponDamageMult().modifyPercent(id, mod);
                    break;
                case ENERGY_ROF:
                    stats.getEnergyRoFMult().modifyPercent(id, mod);
                    break;
                case ENERGY_FLUX_COST:
                    stats.getEnergyWeaponFluxCostMod().modifyPercent(id, mod);
                    break;
                case MISSILE_HEALTH:
                    stats.getMissileHealthBonus().modifyPercent(id, mod);
                    break;
                case MISSILE_GUIDANCE:
                    stats.getMissileGuidance().modifyPercent(id, mod);
                    break;
                case MISSILE_AMMO:
                    stats.getMissileAmmoBonus().modifyPercent(id, mod);
                    break;
                case MISSILE_DAMAGE:
                    stats.getMissileWeaponDamageMult().modifyPercent(id, mod);
                    break;
                case MISSILE_ROF:
                    stats.getMissileRoFMult().modifyPercent(id, mod);
                    break;
                case AUTOFIRE_ACCURACY:
                    stats.getAutofireAimAccuracy().modifyPercent(id, mod);
                    break;
                case RECOIL_PER_SHOT:
                    stats.getRecoilPerShotMult().modifyPercent(id, mod);
                    break;
                case TURRET_TURN:
                    stats.getWeaponTurnRateBonus().modifyPercent(id, mod);
                    break;
                case SHIELD_DAMAGE_TAKEN:
                    stats.getShieldDamageTakenMult().modifyPercent(id, mod);
                    break;
                case SHIELD_TURN_RATE:
                    stats.getShieldTurnRateMult().modifyPercent(id, mod);
                    stats.getShieldUnfoldRateMult().modifyPercent(id, mod);
                    break;
                case SENSOR_STRENGTH:
                    stats.getSensorStrength().modifyPercent(id, mod);
                    break;
                case SENSOR_PROFILE:
                    stats.getSensorProfile().modifyPercent(id, mod);
                    break;
                case BURN:
                    stats.getMaxBurnLevel().modifyFlat(id, mod);
                    break;
                case FUEL_CAPACITY:
                    stats.getFuelMod().modifyPercent(id, mod);
                    break;
                case FUEL_USE:
                    stats.getFuelUseMod().modifyPercent(id, mod);
                    break;
                case CARGO_CAPACITY:
                    stats.getCargoMod().modifyPercent(id, mod);
                    break;
                case CREW_CAPACITY:
                    stats.getMaxCrewMod().modifyPercent(id, mod);
                    break;
                case MIN_CREW:
                    stats.getMinCrewMod().modifyPercent(id, mod);
                    break;
                case MAINTENANCE_COST:
                    stats.getSuppliesPerMonth().modifyPercent(id, mod);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public String getDescriptionParam(int index, ShipAPI.HullSize hullSize, ShipAPI ship) {
        if (index == 0) return ship.getName();
        return null;
    }

    @Override
    public void advanceInCombat(ShipAPI ship, float amount) {
        if (ship == null || !ship.isAlive()) return;

        if (doVentColour) ship.setVentFringeColor(RARITY_COLOUR.get(rarity));

        if (doWeaponGlow) ship.setWeaponGlow(1.5f, RARITY_HIGHLIGHT_COLOUR.get(rarity), EnumSet.allOf(WeaponAPI.WeaponType.class));

        if (doJitterUnder) {
            float intensity = 0.75f;
            if (rarity == VARIANT_RARITY.MYTHICAL) intensity *= 2f;
            ship.setJitterShields(false);
            ship.setJitterUnder(this, RARITY_HIGHLIGHT_COLOUR.get(rarity), intensity, 3, 6f);
        }

        if (doEngineColourShift) {
            ship.getEngineController().fadeToOtherColor(this, null, RARITY_HIGHLIGHT_COLOUR.get(rarity), 0.4f, 0.6f);
        }
    }

    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, ShipAPI.HullSize hullSize, ShipAPI ship, float width, boolean isForModSpec) {
        if (isForModSpec) return;

        String qualityText = rarity.toString().toUpperCase();
        String variantIcon = VARIANT_ICON.get(rarity);

        TooltipMakerAPI img = tooltip.beginImageWithText(variantIcon, iconSize.y);
        img.addTitle(qualityText, RARITY_HIGHLIGHT_COLOUR.get(rarity));
        img.addPara(variantDesignation, RARITY_COLOUR.get(rarity), 2f);
        LabelAPI flavourLabel = img.addPara("..." + flavourText, RARITY_COLOUR.get(rarity), 2f);
        flavourLabel.setAlignment(Alignment.RMID);
        tooltip.addImageWithText(10f);

        if (isStubText) return;

        tooltip.addSectionHeading("VARIANT DATA", Alignment.MID, 10f);
        tooltip.addImage("graphics/icons/desc/HullmodBanner.png", 368f, 25f, 8f);


        tooltip.addPara("This variant has the following qualities affecting performance.",8f);

        float pad = 3f;
        Map<RS_VariantManager.VariantStats.StatType, Float> statValues = variantStats.getStats();

        Map<String, Boolean> bullets = new HashMap<>();
        for (RS_VariantManager.VariantStats.StatType statType : statValues.keySet()) {
            String text = "    - ";

            int value = (int) (float) statValues.get(statType);
            int valueText = value;

            boolean isDebuff = false;

            String disc;

            switch (statType) {
                case LINEAR_ACC:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Movement acceleration " + disc + " by " + valueText + "%";
                    break;
                case TURN_ACC:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Turning acceleration " + disc + " by " + valueText + "%";
                    break;
                case TOP_SPEED:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Top speed " + disc + " by " + valueText + "%";
                    break;
                case ZERO_FLUX_BOOST:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Zero flux boost " + disc + " by a flat " + valueText + " units";
                    break;
                case ENGINE_HEALTH:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Engine health " + disc + " by " + valueText + "%";
                    break;
                case FLUX_DISSIPATION:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Flux dissipation " + disc + " by " + valueText + "%";
                    break;
                case FLUX_CAPACITY:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Flux capacity " + disc + " by " + valueText + "%";
                    break;
                case VENT_RATE:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Vent rate " + disc + " by " + valueText + "%";
                    break;
                case MIN_LEVEL_ZERO_FLUX_BOOST:
                    valueText = (int) ((statValues.get(statType)) * 100f);
                    text += "Minimum flux level for boost at " + valueText + "%"; //always buff
                    break;
                case ECCM_CHANCE:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "ECCM chance " + disc + " by " + valueText + "%";
                    break;
                case EMP_RECEIVED:
                    if (value > 0f) {
                        disc = "increased";
                        isDebuff = true;
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                    }
                    text += "EMP vulnerability " + disc + " by " + valueText + "%";
                    break;
                case SIGHT_RADIUS:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Combat sight radius " + disc + " by " + valueText + "%";
                    break;
                case TIME_MULT:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Timeflow multiplier " + disc + " by " + valueText + "%";
                    break;
                case CR_DECAY_SPEED:
                    if (value > 0f) {
                        disc = "increased";
                        isDebuff = true;
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                    }
                    text += "CR decay speed " + disc + " by " + valueText + "%";
                    break;
                case MALFUNCTION_CHANCE:
                    if (value > 0f) {
                        disc = "increased";
                        isDebuff = true;
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                    }
                    text += "Malfunction chance " + disc + " by " + valueText + "%";
                    break;
                case HULL:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Hull health " + disc + " by " + valueText + "%";
                    break;
                case ARMOUR:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Armour " + disc + " by a flat " + valueText + " units";
                    break;
                case BREAK_PROB:
                    if (value > 0f) {
                        disc = "increased";
                        isDebuff = true;
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                    }
                    text += "Destruction chance " + disc + " by " + valueText + "%";
                    break;
                case MOUNT_HEALTH:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Weapon mount health " + disc + " by " + valueText + "%";
                    break;
                case BALLISTIC_RANGE:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Ballistic weapon range " + disc + " by " + valueText + "%";
                    break;
                case ENERGY_RANGE:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Energy weapon range " + disc + " by " + valueText + "%";
                    break;
                case PD_RANGE:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Point defence weapon range " + disc + " by " + valueText + "%";
                    break;
                case BALLISTIC_AMMO:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Ballistic weapon ammo " + disc + " by " + valueText + "%";
                    break;
                case BALLISTIC_DAMAGE:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Ballistic weapon damage " + disc + " by " + valueText + "%";
                    break;
                case BALLISTIC_ROF:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Ballistic fire rate " + disc + " by " + valueText + "%";
                    break;
                case BALLISTIC_FLUX_COST:
                    if (value > 0f) {
                        disc = "increased";
                        isDebuff = true;
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                    }
                    text += "Ballistic flux cost " + disc + " by " + valueText + "%";
                    break;
                case ENERGY_AMMO:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Energy weapon ammo " + disc + " by " + valueText + "%";
                    break;
                case ENERGY_DAMAGE:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Energy weapon damage " + disc + " by " + valueText + "%";
                    break;
                case ENERGY_ROF:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Energy fire rate " + disc + " by " + valueText + "%";
                    break;
                case ENERGY_FLUX_COST:
                    if (value > 0f) {
                        disc = "increased";
                        isDebuff = true;
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                    }
                    text += "Energy flux cost " + disc + " by " + valueText + "%";
                    break;
                case MISSILE_HEALTH:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Missile health " + disc + " by " + valueText + "%";
                    break;
                case MISSILE_GUIDANCE:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Missile tracking performance " + disc + " by " + valueText + "%";
                    break;
                case MISSILE_AMMO:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Missile weapon ammo " + disc + " by " + valueText + "%";
                    break;
                case MISSILE_DAMAGE:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Missile weapon damage " + disc + " by " + valueText + "%";
                    break;
                case MISSILE_ROF:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Missile fire rate " + disc + " by " + valueText + "%";
                    break;
                case AUTOFIRE_ACCURACY:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Autofire accuracy " + disc + " by " + valueText + "%";
                    break;
                case RECOIL_PER_SHOT:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Weapon recoil " + disc + " by " + valueText + "%";
                    break;
                case TURRET_TURN:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Weapon turn rate " + disc + " by " + valueText + "%";
                    break;
                case SHIELD_DAMAGE_TAKEN:
                    if (value > 0f) {
                        disc = "decreased";
                        isDebuff = true;
                    } else {
                        disc = "increased";
                        valueText = -valueText;
                    }
                    text += "Shield efficiency " + disc + " by " + valueText + "%";
                    break;
                case SHIELD_TURN_RATE:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Shield turn and fold rate " + disc + " by " + valueText + "%";
                    break;
                case SENSOR_STRENGTH:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Sensor strength " + disc + " by " + valueText + "%";
                    break;
                case SENSOR_PROFILE:
                    if (value > 0f) {
                        disc = "increased";
                        isDebuff = true;
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                    }
                    text += "Sensor profile " + disc + " by " + valueText + "%";
                    break;
                case BURN:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Burn level " + disc + " by " + valueText;
                    break;
                case FUEL_CAPACITY:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Fuel capacity " + disc + " by " + valueText + "%";
                    break;
                case FUEL_USE:
                    if (value > 0f) {
                        disc = "increased";
                        isDebuff = true;
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                    }
                    text += "Fuel use " + disc + " by " + valueText + "%";
                    break;
                case CARGO_CAPACITY:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Cargo capacity " + disc + " by " + valueText + "%";
                    break;
                case CREW_CAPACITY:
                    if (value > 0f) {
                        disc = "increased";
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                        isDebuff = true;
                    }
                    text += "Crew capacity " + disc + " by " + valueText + "%";
                    break;
                case MIN_CREW:
                    if (value > 0f) {
                        disc = "increased";
                        isDebuff = true;
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                    }
                    text += "Minimum crew complement " + disc + " by " + valueText + "%";
                    break;
                case MAINTENANCE_COST:
                    if (value > 0f) {
                        disc = "increased";
                        isDebuff = true;
                    } else {
                        disc = "decreased";
                        valueText = -valueText;
                    }
                    text += "Maintenance cost " + disc + " by " + valueText + "%";
                    break;
                default:
                    break;
            }

            bullets.put(text + "!" + valueText, isDebuff);
        }

        Collection<String> keys = bullets.keySet();

        List<String> buffKeys = new ArrayList<>();
        for (String k : keys) {
            if (bullets.get(k)) continue;
            buffKeys.add(k);
        }
        List<String> debuffKeys = new ArrayList<>();
        for (String k : keys) {
            if (!bullets.get(k)) continue;
            debuffKeys.add(k);
        }

        Collections.sort(buffKeys, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                float n1 = Float.parseFloat(s1.substring(s1.indexOf("!") + 1));
                float n2 = Float.parseFloat(s2.substring(s2.indexOf("!") + 1));
                return Float.compare(n2 * n2, n1 * n1); //sort backwards, highest on top, by magnitude
            }
        });
        Collections.sort(debuffKeys, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                float n1 = Float.parseFloat(s1.substring(s1.indexOf("!") + 1));
                float n2 = Float.parseFloat(s2.substring(s2.indexOf("!") + 1));
                return Float.compare(n1 * n1, n2 * n2);
            }
        });

        Map<String, Boolean> tmp = new LinkedHashMap<>();
        for (String key : buffKeys) {
            tmp.put(key, bullets.get(key));
        }
        for (String key : debuffKeys) {
            tmp.put(key, bullets.get(key));
        }
        bullets = tmp;

        for (String key : bullets.keySet()) {
            if (bullets.get(key)) break; //break when debuffs hit

            int index = key.indexOf("!");
            String para = key.substring(0, index);
            String value = key.substring(index + 1);

            LabelAPI label = tooltip.addPara(para, pad);

            if (para.endsWith("%")) label.setHighlight(value + "%");
            else label.setHighlight(value + "");

            label.setHighlightColor(RARITY_HIGHLIGHT_COLOUR.get(rarity));
        }
        for (String key : bullets.keySet()) {
            if (!bullets.get(key)) continue; //do debuffs second

            int index = key.indexOf("!");
            String para = key.substring(0, index);
            String valueText = key.substring(index + 1);

            LabelAPI label = tooltip.addPara(para, pad);

            if (para.endsWith("%")) label.setHighlight(valueText + "%");
            else label.setHighlight(valueText + "");

            label.setHighlightColor(DEBUFF_HIGHLIGHT_COLOUR);
        }

        if (additionalInfo != null) {
            LabelAPI label = tooltip.addPara(additionalInfo, 20f);

            if (infoColour != null) {
                label.setHighlight(label.getText());
                label.setHighlightColor(infoColour);
            }
        }

        /*LabelAPI l = tooltip.addPara("L U D D   I S   N O T   P L E A S E D", 20f);
        l.setHighlight(l.getText());
        l.setHighlightColor(new Color(255, 0, 0, 255));
        l.setAlignment(Alignment.MID);*/
    }
}
