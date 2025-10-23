package greedyspire.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.watcher.DevotionPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import greedyspire.helpers.ModHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import greedyspire.ui.campfire.AlchemyOption;

import java.util.ArrayList;

public class GoldenCrown extends CustomRelic {
    public static final String ID = ModHelper.makePath("GoldenCrown");
    private static final String IMG_PATH = "GreedySpireResources/images/Relics/GoldenCrown.png";
    private static final RelicTier RELIC_TIER = RelicTier.SPECIAL;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;
    private int MonsterType;
    private boolean RoomType = false;

    public GoldenCrown() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        this.flash();
        if (card.type == AbstractCard.CardType.ATTACK){
            if (AbstractDungeon.player.gold > 0){
                AbstractDungeon.player.loseGold(1);
            }else{
                this.addToBot(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, 1));
            }
        }else if (card.type == AbstractCard.CardType.SKILL){
            if (AbstractDungeon.player.gold > 1){
                AbstractDungeon.player.loseGold(2);
            }else if (AbstractDungeon.player.gold > 0){
                AbstractDungeon.player.loseGold(1);
                this.addToBot(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, 1));
            }else{
                this.addToBot(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, 2));
            }
        }else if (card.type == AbstractCard.CardType.POWER){
            if (AbstractDungeon.player.gold > 2){
                AbstractDungeon.player.loseGold(3);
            }else if (AbstractDungeon.player.gold > 0){
                int g = AbstractDungeon.player.gold;
                AbstractDungeon.player.loseGold(g);
                this.addToBot(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, 3 - g));
            }else{
                this.addToBot(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, 3));
            }
        }
    }

    public void atBattleStart() {
        this.counter = 0;
        MonsterType = 0;
        for(AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (m.type == AbstractMonster.EnemyType.BOSS) {
                MonsterType = 3;
            }else if (m.type == AbstractMonster.EnemyType.ELITE) {
                MonsterType = 2;
            }else{
                if (MonsterType == 0 || MonsterType == 1){
                    MonsterType = 1;
                }
            }
        }
        if (AbstractDungeon.player.gold >= 100){
            this.flash();
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
        }
        if (AbstractDungeon.player.gold >= 200){
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 1), 1));
        }
        if (AbstractDungeon.player.gold >= 300){
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player, 5), 5));
        }
        if (AbstractDungeon.player.gold >= 400){
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DrawPower(AbstractDungeon.player, 1), 1));
        }
        if (AbstractDungeon.player.gold >= 500){
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BerserkPower(AbstractDungeon.player, 1), 1));
        }
        if (AbstractDungeon.player.gold >= 600){
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 2), 2));
        }
        if (AbstractDungeon.player.gold >= 700){
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 2), 2));
        }
        if (AbstractDungeon.player.gold >= 800){
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RepairPower(AbstractDungeon.player, 10), 10));
        }
        if (AbstractDungeon.player.gold >= 900){
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BufferPower(AbstractDungeon.player, 1), 1));
        }
        if (AbstractDungeon.player.gold >= 1000){
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DevotionPower(AbstractDungeon.player, 3), 3));
        }
        if (AbstractDungeon.player.gold >= 1100){
            int g = (AbstractDungeon.player.gold - 1000) / 100;
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 1), 1));
        }
    }

    public void atTurnStart() {
        ++this.counter;
    }

    public void onVictory() {
        if (MonsterType == 1){
            if (this.counter <= 5){
                this.flash();
                AbstractDungeon.player.gainGold((6 - this.counter) * 3);
            }
        }else if (MonsterType == 2){
            if (this.counter <= 7){
                this.flash();
                AbstractDungeon.player.gainGold((8 - this.counter) * 4);
            }
        }else{
            if (this.counter <= 9){
                this.flash();
                AbstractDungeon.player.gainGold((10 - this.counter) * 5);
            }
        }
    }

    public void addCampfireOption(ArrayList<AbstractCampfireOption> options) {
        options.add(new AlchemyOption(true));
    }

    public void onEnterRoom(AbstractRoom room) {
        RoomType = room instanceof ShopRoom;
    }

    public void update() {
        super.update();
        if (this.hb.hovered && InputHelper.justReleasedClickRight && RoomType) {
            this.flash();
            int h = (int)((float)AbstractDungeon.player.maxHealth * 0.2F);
            AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, h));
            AbstractDungeon.player.gainGold(100);
        }
    }

    public AbstractRelic makeCopy() {
        return new GoldenCrown();
    }
}