package edu.agh.neurons.scala.activationFunctions

/**
 * Created with IntelliJ IDEA.
 * User: krzysiek
 * Date: 24.05.13
 * Time: 19:21
 * To change this template use File | Settings | File Templates.
 */
trait ActivationFunction {
  def activate(in: Double): Double

  def name(): String
}
