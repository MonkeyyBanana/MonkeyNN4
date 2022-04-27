package monkey.nn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class NeuralNetwork {
	static Random RANDOM = new Random(1);
	
	NetLayer[] layers;
	NetConnection[] connections;
	
	public NeuralNetwork(StandardLayer[] layers) {
		this.layers = layers;
		connectLayers();
	}
	
	public NeuralNetwork(File location) throws FileNotFoundException {
		Scanner scan = new Scanner(location);
		String desc = scan.nextLine();
		layers = new NetLayer[scan.nextInt()];
		connections = new NetConnection[scan.nextInt()];
		scan.nextLine();
		for (int i = 0; i < layers.length; i++) {
			String str = "";
			for (int j = 0; j < 12; j++)
				str += scan.nextLine() + "\n";
			layers[i] = new StandardLayer(str);
		}
		for (int i = 0; i < connections.length; i++) {
			String str = "";
			for (int j = 0; j < 7; j++)
				str += scan.nextLine() + "\n";
			connections[i] = new StandardConnection(str);
			connections[i].prevLayer = layers[i];
			connections[i].nextLayer = layers[i + 1];
		}
		scan.close();
	}
	
	private void connectLayers() {
		connections = new StandardConnection[layers.length - 1];
		for (int i = 0; i < layers.length - 1; i++)
			connections[i] = new StandardConnection(((StandardLayer) layers[i]), ((StandardLayer) layers[i + 1]));
	}
	
	public float[] feedForward(float[] inputs) {
		layers[0].neuronValue = inputs;
		
		for (int i = 0; i < connections.length; i++)
			connections[i].feedForward();
		
		return layers[layers.length - 1].neuronValue;
	}
	
	public void backProp(float[] goal) {
		for (int i = connections.length - 1; i >= 0; i--) 
			if (i == connections.length - 1)
				connections[i].backpropEnd(goal); 
			else
				((StandardConnection)connections[i]).backpropHidden(((StandardLayer)layers[i + 2]).gamma, ((StandardConnection)connections[i + 1]).weights);
		
		for (int i = 0; i < connections.length; i++)
			connections[i].updateWeights();
	}
	
	public void dumpToFile(File location) throws IOException {
		FileWriter fw = new FileWriter(location);
		fw.write("Version (0.2)\n");
		fw.write(layers.length + "\n");
		fw.write(connections.length + "\n");
		for (int i = 0; i < layers.length; i++)
			fw.write(layers[i].toString());
		for (int i = 0; i < connections.length; i++)
			fw.write(connections[i].toString());
		fw.close();
	}
}
