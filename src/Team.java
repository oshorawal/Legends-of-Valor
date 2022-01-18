import java.util.ArrayList;

/**
 * @ClassName Team
 * @Description It is the abstract class for all kinds of teams in the game.
 */
public abstract class Team {
    /**
     * the members of the team
     */
    protected ArrayList<Character> characters;

    public Team(ArrayList<Character> characters) {
        this.characters = characters;
    }

    public Team() {
        this.characters = new ArrayList<>();
    }

    public void addMember(Character character){
        this.characters.add(character);
    }

    public Character getMember(int i){
        return this.characters.get(i);
    }

    public void removeMember(Character character){
        this.characters.remove(character);
    }

    public int getSize(){
        return this.characters.size();
    }

    abstract void displayMembers();

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    // whether all the members in the team are faint or not
    public boolean allFaint(){
        for (int i = 0; i < this.characters.size(); i++) {
            if(characters.get(i).isAlive()) return false;
        }
        return true;
    }

    // whether any one of the team member has reached the opposite nexus
    public abstract boolean reachNexus();
}
