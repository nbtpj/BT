package engine;

import interfaces.Collision;

import java.util.*;

public class SpriteManager {

//#####################################################################################################################
// declare variables
    private final static List<Sprite> GAME_ACTORS_LIST = new ArrayList<>();
    private final static List<Sprite> COLLISION_LIST = new ArrayList<>();
    // CLEAN_UP_SPRITES_SET is used to avoid the case that a sprite is removed when COLLISION_LIST is being checked
    // But I also create removeSprites method in case of necessity
    private final static Set<Sprite> CLEAN_UP_SPRITES_SET = new HashSet<>();

//#####################################################################################################################
// getter and setter
    public List<Sprite> getGameActorsList() {
        return GAME_ACTORS_LIST;
    }
    public List<Sprite> getCollisionList() {
        return COLLISION_LIST;
    }
    public Set<Sprite> getCleanUpSpritesList() {
        return CLEAN_UP_SPRITES_SET;
    }

//#####################################################################################################################
// global method
    public void addSprites(Sprite... sprites) {
        GAME_ACTORS_LIST.addAll(Arrays.asList(sprites));
    }

    public void removeSprites(Sprite... sprites) {
        GAME_ACTORS_LIST.removeAll(Arrays.asList(sprites));
    }

    public void addSpritesToBeRemoved(Sprite... sprites) {
        if (sprites.length > 1) {
            CLEAN_UP_SPRITES_SET.addAll(Arrays.asList(sprites));
        } else {
            CLEAN_UP_SPRITES_SET.add(sprites[0]);
        }
    }

    public void resetCollisionList() {
        COLLISION_LIST.clear();

        for (Sprite sprite : GAME_ACTORS_LIST) {
            if (sprite instanceof Collision) {
                COLLISION_LIST.add(sprite);
            }
        }
    }

    public void cleanupSprites() {
        GAME_ACTORS_LIST.removeAll(CLEAN_UP_SPRITES_SET);
        CLEAN_UP_SPRITES_SET.clear();
    }
}
