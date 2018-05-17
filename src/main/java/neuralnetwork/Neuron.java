package neuralnetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Neuron {
   public int numInputs;
   public double bias;
   public double output;
   public double errorGradient;

   public List<Double> weights = new ArrayList<Double>();
   public List<Double> inputs = new ArrayList<Double>();

   public Neuron(int numInputs){
       Random random = new Random();
       bias = random.nextDouble() * (1.0f - (-1.0f) + (-1.0f));

       this.numInputs = numInputs;

       for(int i = 0; i < numInputs; i++){
           weights.add(random.nextDouble() * (1.0f - (-1.0f) + (-1.0f)));
       }
   }
}
