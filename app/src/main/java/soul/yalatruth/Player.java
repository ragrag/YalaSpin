package soul.yalatruth;

import java.io.Serializable;

/**
 * Created by Raggi on 6/5/2017.
 */

public class Player implements Serializable , Comparable<Player>{


        boolean turn;
        String name;
        int scoreup;
        int scoredown;


        @Override
        public int compareTo(Player o) {
                return this.scoreup - o.scoreup;
        }

        public Player(String name)
{
        this.name = name;
        scoreup=0;
        scoredown =0;
        turn=false;

}

public void setTurn(boolean turn)
{
        this.turn = turn;
}

        public void answered()
        {
                scoreup++;
        }
public void passed()
{
 scoredown++;
}


public String getName()
{
        return name;
}

        @Override
        public String toString() {
                return this.name + " " + scoreup + "/" +scoredown;
        }
}
