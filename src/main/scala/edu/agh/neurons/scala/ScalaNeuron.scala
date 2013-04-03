package edu.agh.neurons.scala

import edu.agh.neurons.Neuron
import java.lang.{Double => JDouble}

case class ScalaNeuron(override val weights: java.util.List[JDouble],
                       override val sigma: Double) extends Neuron {
}
