package edu.agh.neurons.scala.kohonen

import edu.agh.neurons.Layer
import edu.agh.neurons.scala.ScalaNetwork

/**
 * Created with IntelliJ IDEA.
 * User: krzysiek
 * Date: 25.04.13
 * Time: 18:29
 * To change this template use File | Settings | File Templates.
 */

object KohonenNetwork {
  type Point = Seq[Double]
  type DistFun = Point => Neuron => Double
}

import KohonenNetwork._

case class KohonenNetwork(distFunc: DistFun, neurons: Seq[Neuron]) {
  assert(neurons.size > 0)

  def findBest(to: Point): Neuron =
    neurons.minBy(distFunc(to))

  def adjustTo(fMove: Double, nMove: Double)(point: Point) = {
    val best = findBest(point)
    best.moveTo(point, fMove)
    best.neighbours.foreach(_.moveTo(point, nMove))
    distFunc(point)(best)
  }
}


