package monkey.nn;

import java.util.Scanner;

public abstract class NetConnection {
    NetLayer prevLayer;
    NetLayer nextLayer;
    
    public abstract void feedForward();
    public abstract void backpropEnd(float[] goal);
    public abstract void updateWeights();
}
