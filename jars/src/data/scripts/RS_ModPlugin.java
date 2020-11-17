package data.scripts;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import data.scripts.campaign.RS_VariantManager;
import data.scripts.util.RS_Misc;
import org.json.JSONException;

import java.io.IOException;

public class RS_ModPlugin extends BaseModPlugin {
    @Override
    public void onGameLoad(boolean newGame) {
        if (!Global.getSector().getListenerManager().hasListenerOfClass(RS_VariantManager.class)) {
            RS_VariantManager manager = new RS_VariantManager();
            Global.getSector().addListener(manager);
        }
    }

    @Override
    public void onNewGameAfterEconomyLoad() {
        if (!Global.getSector().getListenerManager().hasListenerOfClass(RS_VariantManager.class)) {
            RS_VariantManager manager = new RS_VariantManager();
            Global.getSector().addListener(manager);
        }
    }

    @Override
    public void onApplicationLoad() {
        try {
            RS_Misc.parseRarities();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
