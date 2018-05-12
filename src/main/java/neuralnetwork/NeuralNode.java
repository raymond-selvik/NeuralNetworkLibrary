package neuralnetwork;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NeuralNode {
    private TrainingSet[] trainingSets;

    private double[] weights;
    private double bias;
    private double totalError;


    public NeuralNode(TrainingSet[] trainingSets){
        this.trainingSets = trainingSets;

        weights = new double[trainingSets[0].getInputs().length];
        Arrays.fill(weights, 0.0f);

        bias = 0;
        totalError = 0;
    }


    public void Train(int epochs){
        InitWeights();

        for(int epoch = 0; epoch < epochs; epoch++){
            totalError = 0;
            for(int i = 0; i < trainingSets.length; i++){
                UpdateWeights(i);
            }
            System.out.println("TOTAL ERROR: " + totalError);
        }
    }

    void InitWeights(){
        Random r = new Random();

        for(int i = 0; i < weights.length; i++){
            weights[i] = -1.0f + (1.0f - (-1.0f)) * r.nextDouble();
        }
        bias = -1.0f + (1.0f - (-1.0f)) * r.nextDouble();
    }

    void UpdateWeights(int j){
        double error = trainingSets[j].getOutput() - CalcOutput(j);
        totalError += Math.abs((float) error);

        for(int i = 0; i < weights.length; i++){
            weights[i] = weights[i] + error * trainingSets[j].getInput(i);
        }
        bias += error;
    }

    double CalcOutput(int i){
        double dp = DotProductBias(weights, trainingSets[i].getInputs());

        if(dp > 0) return 1;
        else  return 0;
    }

    double DotProductBias(double[] v1, double[] v2)
    {
        if (v1 == null || v2 == null)
            return -1;

        if (v1.length != v2.length)
            return -1;

        double d = 0;
        for (int x = 0; x < v1.length; x++)
        {
            d += v1[x] * v2[x];
        }

        d += bias;

        return d;
    }
}
