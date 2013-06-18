package edu.agh.neurons.scala.activationFunctions


object StepFunction extends ActivationFunction {
  def activate(in: Double): Double = if (in > 0) 1.0 else 0.0

  def name(): String = "step"

  def d(in: Double): Double = ???
}
