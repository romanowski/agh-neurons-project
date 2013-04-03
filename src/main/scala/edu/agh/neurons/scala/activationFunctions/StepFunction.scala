package edu.agh.neurons.scala.activationFunctions

import edu.agh.neurons.ActivationFunction


object StepFunction extends ActivationFunction {
  def activate(in: Double): Double = if (in > 0) {
    1.0
  } else 0.0
}
