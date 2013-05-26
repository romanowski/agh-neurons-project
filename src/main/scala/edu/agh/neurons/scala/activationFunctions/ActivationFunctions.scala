package edu.agh.neurons.scala.activationFunctions

/**
 * Created with IntelliJ IDEA.
 * User: krzysiek
 * Date: 05.04.13
 * Time: 11:06
 * To change this template use File | Settings | File Templates.
 */
object ActivationFunctions {

  val all = LineFunction :: SigmoidFunction :: StepFunction :: Nil

  def forName(name: String): ActivationFunction = {
    all.filter(_.name() == name).head
  }

}
