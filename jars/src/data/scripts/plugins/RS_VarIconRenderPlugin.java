package data.scripts.plugins;

import com.fs.starfarer.api.GameState;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.BaseEveryFrameCombatPlugin;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.graphics.SpriteAPI;
import com.fs.starfarer.api.input.InputEventAPI;
import org.lazywizard.lazylib.combat.CombatUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;

public class RS_VarIconRenderPlugin extends BaseEveryFrameCombatPlugin {
    private static final Map<ShipAPI.HullSize, Vector2f> baseOffset = new HashMap<>();
    static {
        baseOffset.put(ShipAPI.HullSize.FIGHTER, new Vector2f());
        baseOffset.put(ShipAPI.HullSize.FRIGATE, new Vector2f(80f, 25f));
        baseOffset.put(ShipAPI.HullSize.DESTROYER, new Vector2f(105f, 25f));
        baseOffset.put(ShipAPI.HullSize.CRUISER, new Vector2f(155f, 25f));
        baseOffset.put(ShipAPI.HullSize.CAPITAL_SHIP, new Vector2f(180f, 25f));
    }

    private static boolean renderIcons = true;
    private boolean keyDownLastFrame = false;

    @Override
    public void advance(float amount, List<InputEventAPI> events) {
        CombatEngineAPI engine = Global.getCombatEngine();
        if (engine == null || engine.isUIShowingDialog() || (engine.getCombatUI() != null && engine.getCombatUI().isShowingCommandUI())) return;
        if (Global.getCurrentState() != GameState.COMBAT) return;
        if (engine.isCombatOver()) return;

        boolean isActivationKeyDown = Keyboard.isKeyDown(Keyboard.KEY_P);
        if (isActivationKeyDown && !keyDownLastFrame) renderIcons = !renderIcons;
        keyDownLastFrame = isActivationKeyDown;
        if (!renderIcons) return;

        glPushMatrix();
        glLoadIdentity();
        glOrtho(0, Global.getSettings().getScreenWidth(), 0, Global.getSettings().getScreenHeight(), -1, 1);

        float zoomMult = 1f / engine.getViewport().getViewMult();

        for (ShipAPI ship : engine.getShips()) {
            if (!ship.isAlive()) continue;

            String variant = null;
            for (String id : ship.getVariant().getPermaMods()) {
                if (id.startsWith("RS_Var")) {
                    variant = id;
                    break;
                }
            }
            if (variant == null) continue;

            String sprite = Global.getSettings().getHullModSpec(variant).getSpriteName();
            SpriteAPI hullmodIcon = Global.getSettings().getSprite(sprite);

            hullmodIcon.setSize(32f * zoomMult, 32f * zoomMult);

            Vector2f renderPosition = CombatUtils.toScreenCoordinates(Vector2f.add(ship.getLocation(), baseOffset.get(ship.getHullSize()), new Vector2f()));

            hullmodIcon.renderAtCenter(renderPosition.x, renderPosition.y);
        }

        glEnd();
        glDisable(GL_BLEND);
        glPopAttrib();
        glColor4f(1, 1, 1, 1);
        glPopMatrix();
    }
}
