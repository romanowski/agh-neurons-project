package edu.agh.neurons;

/**
 * Created with IntelliJ IDEA.
 * User: krzysiek
 * Date: 03.04.13
 * Time: 20:26
 * To change this template use File | Settings | File Templates.
 */
public interface ActivationFunction {

    public double activate(double in);

    public String name();

}
