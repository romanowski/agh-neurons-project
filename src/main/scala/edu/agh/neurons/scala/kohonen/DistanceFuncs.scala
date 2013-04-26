package edu.agh.neurons.scala.kohonen

/**
 * Created with IntelliJ IDEA.
 * User: krzysiek
 * Date: 26.04.13
 * Time: 01:54
 * To change this template use File | Settings | File Templates.
 */

import KohonenNetwork._

object DistanceFuncs {

  def Euqlides(p: Point)(n: Neuron): Double =
    math.sqrt(n.weights.zip(p).map(el => el._1 * el._1 + el._2 * el._2).sum)

  def Taxi(p: Point)(n: Neuron): Double =
    n.weights.zip(p).map(el => el._1 + el._2).map(math.abs _).sum

}
