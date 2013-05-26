package edu.agh.neurons.scala.activationFunctions


object LineFunction extends ActivationFunction {
  def activate(in: Double): Double = in

  def name(): String = "line"
}
