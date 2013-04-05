package edu.agh.neurons.scala

import edu.agh.neurons.{Neuron, Network, Layer, ActivationFunction}
import java.{lang, util}
import scala.collection.JavaConversions._

case class ScalaNetwork(override val layers: java.util.List[Layer]) extends Network {

  override lazy val inLayer: Layer = layers.head
  override val outLayer: Layer = layers.last

  def compute(inputs: util.List[lang.Double]): util.List[lang.Double] = {
    layers.foldLeft(inputs) {
      case (values, net) =>
        //println(values)
        net.neurons.map {
          neuron =>
            if (values.size() != neuron.weights.size) {
              throw new IllegalArgumentException("weight vector size differ form inputs size")
            }
            neuron.activationFunction.activate(values.zip(neuron.weights).map(el => el._1 * el._2).sum + neuron.sigma): java.lang.Double
        }
    }
  }
}