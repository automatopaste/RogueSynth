package data.scripts.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import data.scripts.campaign.RS_VariantManager;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.*;

public class RS_Misc {
    private static Map<ShipAPI.HullSize, Float> weight = new HashMap<>();
    static {
        weight.put(ShipAPI.HullSize.FRIGATE, 1f);
        weight.put(ShipAPI.HullSize.DESTROYER, 1.05f);
        weight.put(ShipAPI.HullSize.CRUISER, 1.15f);
        weight.put(ShipAPI.HullSize.CAPITAL_SHIP, 1.3f);
    }

    private enum Rarity {
        STANDARD,
        ALPHA,
        ULTRA,
        MYTHICAL
    }

    private static Map<Rarity, List<String>> variants = new HashMap<>();

    public static void rollVariant(final float chance, final ShipAPI.HullSize hullSize, final FleetMemberAPI member) {
        Random random = new Random();
        float pick = random.nextFloat();
        if (chance < pick) return;

        float num = random.nextFloat();

        //num += weight.get(hullSize) / 8f;
        num *= weight.get(hullSize);

        Rarity rarity;
        if (num < 0.4f) {
            rarity = Rarity.STANDARD;
        } else if (num < 0.7f) {
            rarity = Rarity.ALPHA;
        } else if (num < 0.875f) {
            rarity = Rarity.ULTRA;
        } else {
            rarity = Rarity.MYTHICAL;
        }

        List<String> ids = variants.get(rarity);
        if (ids.isEmpty()) return;
        String id = ids.get(random.nextInt(ids.size()));
        member.getVariant().addPermaMod(id);
        Global.getLogger(RS_VariantManager.class).info("Applying variant to fleetmember: " + member.getVariant().getFullDesignationWithHullName() + " with id " + id);
    }

    public static void parseRarities() throws IOException, JSONException {
        JSONArray array = Global.getSettings().loadCSV("data/config/RS_rarities.csv");

        List<String> standard = new ArrayList<>();
        List<String> alpha = new ArrayList<>();
        List<String> ultra = new ArrayList<>();
        List<String> mythical = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            String id = array.getJSONObject(i).getString("id");
            if (id.startsWith("#") || id.equals("")) continue;
            Rarity rarity = Rarity.valueOf(array.getJSONObject(i).getString("rarity"));

            switch (rarity) {
                case STANDARD:
                    standard.add(id);
                    break;
                case ALPHA:
                    alpha.add(id);
                    break;
                case ULTRA:
                    ultra.add(id);
                    break;
                case MYTHICAL:
                    mythical.add(id);
                    break;
            }
        }

        variants.put(Rarity.STANDARD, standard);
        variants.put(Rarity.ALPHA, alpha);
        variants.put(Rarity.ULTRA, ultra);
        variants.put(Rarity.MYTHICAL, mythical);
    }
}
