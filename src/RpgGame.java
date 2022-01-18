/**
 * @ClassName RpgGame
 * @Description It is the abstract class for the RPG game.
 */

public abstract class RpgGame extends Game {
    /**
     * the world of the game
     */
    protected World world;

    RpgGame(){
        this.world = World.getInstance();
        world.init();
    }

    RpgGame(World world){
        this.world = world;
    }
}
