package edu.agh.neurons;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: krzysiek
 * Date: 03.04.13
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */
public interface Neuron {

    public List<Double> weights();

    public double bias();

    public ActivationFunction activationFunction();
}
