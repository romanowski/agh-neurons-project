package edu.agh.neurons.scala

import activationFunctions.ActivationFunctions
import io.Source
import scala.collection.JavaConversions._

class NetworkBuilder {
  def createNetwork(confFile: String): Network = {
    val split = " +"
    val layersLines = Source.fromFile(confFile).getLines().toList
    Network(
      layersLines.map(_.split(split).toList).groupBy(_.head).map {
        case (layerNr, data) =>
          layerNr -> Layer(data.sortBy(_.get(1).toInt).map {
            line => {
              val _ :: _ :: activationFunc :: sigma :: weights = line
              Neuron(weights.map(_.toDouble),
                sigma.toDouble,
                ActivationFunctions.forName(activationFunc)
              )
            }
          })
      }.toList.sortBy(_._1).map(_._2))
  }
}
