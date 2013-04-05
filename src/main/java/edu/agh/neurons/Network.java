package edu.agh.neurons;

import java.util.List;

public interface Network {

    public Layer inLayer();

    public Layer outLayer();

    public List<Layer> layers();

    public List<Double> compute(List<Double> inputs);

}
