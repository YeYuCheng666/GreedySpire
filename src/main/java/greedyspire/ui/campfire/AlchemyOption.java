package greedyspire.ui.campfire;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import greedyspire.vfx.campfire.CampfireAlchemyEffect;

public class AlchemyOption extends AbstractCampfireOption {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    public AlchemyOption(boolean active) {
        this.label = TEXT[0];
        this.usable = active;
        this.description = active ? TEXT[1] : TEXT[2];
        this.img = ImageMaster.loadImage("GreedySpireResources/images/UI/Alchemy.png");
    }

    public void useOption() {
        if (this.usable) {
            AbstractDungeon.effectList.add(new CampfireAlchemyEffect());
        }

    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("Alchemy Option");
        TEXT = uiStrings.TEXT;
    }
}
