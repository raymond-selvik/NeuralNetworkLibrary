package neuralnetwork;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    public int numNeurons;
    public List<Neuron> neurons = new ArrayList<Neuron>();

    public Layer(int numNeurons, int numNeuronInputs){
        this.numNeurons = numNeurons;

        for(int i = 0; i < numNeurons; i++){
            neurons.add(new Neuron(numNeuronInputs));
        }
    }

    public int getNumNeurons() {
        return numNeurons;
    }
}
