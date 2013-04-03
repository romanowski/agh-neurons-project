package edu.agh.neurons.scala

import edu.agh.neurons.{Neuron, Layer}

case class ScalaLayer(override val neurons: java.util.List[Neuron]) extends Layer {

}
