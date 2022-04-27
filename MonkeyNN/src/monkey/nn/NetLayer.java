package monkey.nn;

import java.util.Scanner;

public abstract class NetLayer {
	String LAYER_SIGNATURE = "Abstract Network Layer";
	String NETWORK_VERSION = "0.2";
	
    float[] neuronValue;
    NetActivator activator;
    
    public abstract void runAtForward(int i);
    public abstract void runAtBackprop(int i);
    
    public abstract String toString();
}
