package edu.agh.neurons.scala

import edu.agh.neurons.{NetworkFactory, Network}
import io.Source
import scala.collection.JavaConversions._
import java.lang.{Double => JDouble}

class NetworkBuilder extends NetworkFactory {
  def createNetwork(confFile: String): Network = {
    val split = "\\W+"
    val info :: layersLines = Source.fromFile(confFile).getLines().toList
    val activationFunc :: Nil = info.split(split).toList
    val layers = layersLines.map(_.split(split).toList).groupBy(_.head).map {
      case (layerNr, data) =>
        layerNr -> ScalaLayer(data.sortBy(_.get(1).toInt).map {
          line => {
            val _ :: _ :: sigma :: weights = line
            ScalaNeuron(weights.map(_.toDouble: JDouble), sigma.toDouble: JDouble)
          }
        })
    }.toList.sortBy(_._1).map(_._2)
    null
  }
}
