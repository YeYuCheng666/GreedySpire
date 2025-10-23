package greedyspire.modcore;

import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import basemod.BaseMod;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import greedyspire.relics.GoldenCrown;

import java.util.ArrayList;

@SpireInitializer
public class GreedySpire implements EditStringsSubscriber, EditRelicsSubscriber, PostCreateStartingRelicsSubscriber {
    public GreedySpire() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        new GreedySpire();
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new GoldenCrown(), RelicType.SHARED);
    }

    public void receiveEditStrings() {
        String lang = "ENG";
        if (Settings.language == Settings.GameLanguage.ZHS)
            lang = "ZHS";
        BaseMod.loadCustomStringsFile(RelicStrings.class, "GreedySpireResources/localization/" + lang + "/relics.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, "GreedySpireResources/localization/" + lang + "/ui.json");
    }

    public void receivePostCreateStartingRelics(AbstractPlayer.PlayerClass chosenClass, ArrayList<String> relics) {
        relics.add("GreedySpire:GoldenCrown");
    }
}