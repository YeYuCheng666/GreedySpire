package greedyspire.patches;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;

@SpirePatch(clz = RewardItem.class, method = "applyGoldBonus")
public class RewardCP
{
    @SpireInsertPatch(rloc = 11, localvars = {"tmp"})
    public static void Insert(RewardItem __instance, boolean t, int tmp) {
        if (AbstractDungeon.player.hasRelic("GreedySpire:GoldenCrown")) {
            __instance.bonusGold += MathUtils.round((float) tmp * 0.5F);
        }
    }
}
