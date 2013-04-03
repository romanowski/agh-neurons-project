package edu.agh.neurons.scala.activationFunctions

import edu.agh.neurons.ActivationFunction

object SigmoidFunction extends ActivationFunction {
  def activate(in: Double): Double = 1 / (1 + math.pow(math.E, -in))
}
