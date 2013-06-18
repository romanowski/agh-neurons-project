package edu.agh.neurons.scala.back

import edu.agh.neurons.scala.{Neuron, Layer, Network}
import edu.agh.neurons.scala.activationFunctions.{ActivationFunction, LineFunction}
import util.Random

/**
 * Created with IntelliJ IDEA.
 * User: krzysiek
 * Date: 18.06.13
 * Time: 07:56
 * To change this template use File | Settings | File Templates.
 */
class BackPropagationNetwork(layers: Seq[Layer]) extends Network(layers) {


}

object BackPropagationNetwork {
  def build(sizes: Seq[Int], af: ActivationFunction) = {

    val layers = sizes.tail.map(s =>
      Layer((1 to s).map(_ => Neuron(Nil, 0, af)))
    )

    layers.zip(sizes).foreach {
      case (layer, prevSize) =>
        layer.neurons.foreach {
          n => n.weights = List.fill(prevSize)(Random.nextDouble())
        }
    }

    new BackPropagationNetwork(layers)
  }
}
