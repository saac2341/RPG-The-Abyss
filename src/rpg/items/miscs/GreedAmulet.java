package rpg.items.miscs;

public class GreedAmulet extends Misc {
    public GreedAmulet(){
        super("Amuleto de la codicia");
    }

    @Override
    protected void initItem() {
        this.name="Amuleto de la codicia";
        this.description="Amuleto mágico que te da un 15% más del oro.";
        this.price=1015;
    }

}