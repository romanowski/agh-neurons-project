package edu.agh.neurons.scala.kohonen.neighbours

import edu.agh.neurons.scala.kohonen.Neuron

object OneD extends NeighbourBuilder {
  def buildNeighbour(neurons: Seq[Neuron]) = {
    neurons.zip(neurons.drop(1)).foreach {
      case (prev, next) =>
        prev.neighbours = prev.neighbours :+ next
        next.neighbours = next.neighbours :+ prev
    }
    neurons
  }
}



object TwoD extends NeighbourBuilder {
  def buildNeighbour(neurons: Seq[Neuron]) = {
    neurons.zip(neurons.drop(1)).foreach {
      case (prev, next) =>
        prev.neighbours = prev.neighbours :+ next
        next.neighbours = next.neighbours :+ prev
    }
    neurons
  }
}