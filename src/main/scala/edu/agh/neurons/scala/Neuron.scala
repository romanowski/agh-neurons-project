package edu.agh.neurons.scala

import activationFunctions.ActivationFunction

case class Neuron(var weights: Seq[Double],
                  bias: Double,
                  activationFunction: ActivationFunction) {

  def activate(values: Seq[Double]) =
    activationFunction.activate(values.zip(weights).map(el => el._1 * el._2).sum + bias)
}
