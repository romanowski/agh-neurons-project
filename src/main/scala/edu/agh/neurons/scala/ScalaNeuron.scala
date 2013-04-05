package edu.agh.neurons.scala

import edu.agh.neurons.{ActivationFunction, Neuron}
import java.lang.{Double => JDouble}

case class ScalaNeuron(override val weights: java.util.List[JDouble],
                       override val sigma: Double,
                       override val activationFunction: ActivationFunction) extends Neuron {
}
