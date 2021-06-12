package data.scripts.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;

import java.awt.*;

public class RS_SeparatorHullmod extends BaseHullMod {
    @Override
    public Color getBorderColor() {
        return new Color(255, 255, 255, 0);
    }
}