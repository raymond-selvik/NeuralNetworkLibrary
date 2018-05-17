package neuralnetwork;

import java.util.ArrayList;
import java.util.List;

public class ANN {

    public int numInputs;
    public int numOutputs;
    public int numHiddenLayers;
    public int numNeuronsPerHidden;

    public double alpha;

    List<Layer> layers = new ArrayList<Layer>();

    public ANN(int numInputs, int numOutputs, int numHiddenLayers, int numNeuronsPerHidden, double alpha){
        this.numInputs = numInputs;
        this.numOutputs = numOutputs;
        this.numHiddenLayers = numHiddenLayers;
        this.numNeuronsPerHidden = numNeuronsPerHidden;
        this.alpha = alpha;

        if(numHiddenLayers > 0){
            layers.add(new Layer(numNeuronsPerHidden, numInputs));

            for(int i = 0; i < numHiddenLayers - 1; i++){
                layers.add(new Layer(numNeuronsPerHidden, numNeuronsPerHidden));
            }

            layers.add(new Layer(numOutputs, numNeuronsPerHidden));
        }
        else {
            layers.add(new Layer(numOutputs, numInputs));
        }
    }

    public List<Double> Train(List<Double> inputValues, List<Double> desiredOutput){
        List<Double> inputs = new ArrayList<Double>();
        List<Double> outputs = new ArrayList<Double>();

        if(inputValues.size() != numInputs){
            System.out.println("Number of INPUTS must be " + numInputs);
            return outputs;
        }

        inputs = new ArrayList<Double>(inputValues);

        for(int i = 0; i < numHiddenLayers + 1; i++){
            if(i > 0){
                inputs = new ArrayList<Double>(outputs);
            }
            outputs.clear();

            for(int j = 0; j < layers.get(i).getNumNeurons(); j++){
                double N = 0;
                layers.get(i).neurons.get(j).inputs.clear();

                for(int k = 0; k < layers.get(i).neurons.get(j).numInputs; k++){
                    layers.get(i).neurons.get(j).inputs.add(inputs.get(k));
                    N += layers.get(i).neurons.get(j).weights.get(k) * inputs.get(k);
                }

                N -= layers.get(i).neurons.get(j).bias;
                layers.get(i).neurons.get(j).output = ActivationFunction(N);
                outputs.add(layers.get(i).neurons.get(j).output);
            }
        }
        UpdateWeights(outputs, desiredOutput);

        return outputs;
    }

    void UpdateWeights(List<Double> outputs, List<Double> desiredOutputs){

        double error;

        for(int i = numHiddenLayers; i >= 0; i--){
            for(int j = 0; j < layers.get(i).getNumNeurons(); j++){
                if (i == numHiddenLayers){
                    error = desiredOutputs.get(j) - outputs.get(j);
                    layers.get(i).neurons.get(j).errorGradient = outputs.get(j) * (1 - outputs.get(j)) * error;
                }
                else {
                    layers.get(i).neurons.get(j).errorGradient = layers.get(i).neurons.get(j).output * (1 - layers.get(i).neurons.get(j).output);
                    double errorGradSum = 0;

                    for(int p = 0; p < layers.get(i + 1).numNeurons; p++){
                        errorGradSum += layers.get(i + 1).neurons.get(p).errorGradient * layers.get(i + 1).neurons.get(p).weights.get(j);
                    }
                    layers.get(i).neurons.get(j).errorGradient *= errorGradSum;
                }
                for(int k = 0; k < layers.get(i).neurons.get(j).numInputs; k++){

                    if(i == numHiddenLayers){
                        error = desiredOutputs.get(j) - outputs.get(j);

                        layers.get(i).neurons.get(j).weights.set(
                                k,
                                layers.get(i).neurons.get(j).weights.get(k) + alpha * layers.get(i).neurons.get(j).inputs.get(k) * error
                        );
                    }
                }
                layers.get(i).neurons.get(j).bias += alpha * -1 * layers.get(i).neurons.get(j).errorGradient;
            }
        }
    }

    double ActivationFunction(double value){
        return Step(value);
    }

    double Step(double value) //(aka binary step)
    {
        if(value < 0) return 0;
        else return 1;
    }
    double Sigmoid(double value) //(aka logistic softstep)
    {
        double k = (double) Math.exp(value);
        return k / (1.0f + k);
    }

}
