package edu.agh.neurons.scala

import activationFunctions.ActivationFunctions
import edu.agh.neurons.{NetworkFactory, Network}
import io.Source
import scala.collection.JavaConversions._
import java.lang.{Double => JDouble}

class NetworkBuilder extends NetworkFactory {
  def createNetwork(confFile: String): Network = {
    val split = " +"
    val layersLines = Source.fromFile(confFile).getLines().toList
    ScalaNetwork(
      layersLines.map(_.split(split).toList).groupBy(_.head).map {
        case (layerNr, data) =>
          layerNr -> ScalaLayer(data.sortBy(_.get(1).toInt).map {
            line => {
              val _ :: _ :: activationFunc :: sigma :: weights = line
              ScalaNeuron(weights.map(_.toDouble: JDouble),
                sigma.toDouble: JDouble,
                ActivationFunctions.forName(activationFunc)
              )
            }
          })
      }.toList.sortBy(_._1).map(_._2))
  }
}
