package edu.agh.neurons.scala

import edu.agh.neurons.{Network, Layer, ActivationFunction}
import java.{lang, util}
import scala.collection.JavaConversions._

case class ScalaNetwork(override val inLayer: Layer,
                        override val outLayer: Layer,
                        override val innerLayers: java.util.List[Layer],
                        override val activationFunction: ActivationFunction) extends Network {
  val allLayers: List[Layer] = inLayer :: (innerLayers.toList ++ List(outLayer))

  def compute(inputs: util.List[lang.Double]): util.List[lang.Double] = {
    allLayers.foldLeft(inputs) {
      case (values, net) =>
        net.neurons.map {
          neuron =>
            if (values.size() != neuron.weights) {
              throw new IllegalArgumentException("weight vector size differ form inputs size")
            }
            activationFunction.activate(values.zip(neuron.weights).map(el => el._1 * el._2).sum + neuron.sigma): java.lang.Double
        }
    }
  }
}
