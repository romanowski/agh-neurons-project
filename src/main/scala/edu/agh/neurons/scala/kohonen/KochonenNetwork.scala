package edu.agh.neurons.scala.kohonen


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

  def findBest(to: Point, notTo: Set[Int] = Set()): Neuron = {
    neurons.filterNot(notTo contains _.index).minBy(distFunc(to))
  }

  def getDistance(p: Point): Point =
    neurons.map(distFunc(p))

  def output(p: Point): Seq[Double] = {

    val distances = getDistance(p)
    val min = distances.min

    distances.map {
      w => if (w == min) 1.0 else 0.0
    }
  }

  var last = Set[Int]()

  def findBestAndAdjust(fMove: Double, nMove: Double)(point: Point) = {
    val best = findBest(point, last)
    last = last + best.index
    if (last.size == neurons.size)
      last = Set();

    best.moveTo(point, fMove)
    best.neighbours.foreach(_.moveTo(point, nMove))
    val dist = neurons.map(distFunc(point))
    println(dist + (best.index - 1).toString)
    best
  }

  def mapOut(neuron: Neuron) = {
    neurons.map(n => if (neuron eq n) 1.0 else 0.0)
  }

  def adjustTo(fMove: Double, nMove: Double)(point: Point) = {
    val best = findBestAndAdjust(fMove, nMove)(point)
    distFunc(point)(best)
  }

  override def toString = "Network: " + neurons.mkString("\n")
}


