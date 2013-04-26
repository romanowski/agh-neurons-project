package edu.agh.neurons.scala.kohonen.neighbours

import edu.agh.neurons.scala.kohonen.Neuron

/**
 * Created with IntelliJ IDEA.
 * User: krzysiek
 * Date: 26.04.13
 * Time: 01:37
 * To change this template use File | Settings | File Templates.
 */
object NONE extends NeighbourBuilder {
  def buildNeighbour(neurons: Seq[Neuron]) = neurons
}
