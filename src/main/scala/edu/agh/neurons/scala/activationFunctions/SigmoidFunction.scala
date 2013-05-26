package edu.agh.neurons.scala.activationFunctions


object SigmoidFunction extends ActivationFunction {
  def activate(in: Double): Double =
    2 / (1 + math.pow(math.E, - in)) - 1

  def name(): String = "sigm"
}
