package edu.agh.neurons.scala.kohonen

import scala.io.Source
import neighbours.{OneD, NONE}
import scala.util.Random
import scala._

object Demo extends App {

  val neighbourStr :: neuronsSizeStr :: inputSizeStr :: distanceFuncStr :: genFuncStr :: paramsStr :: minErrorStr :: points =
    Source.fromFile(args(0)).getLines().toList


  val params = paramsStr.split(",").map {
    entry => {
      val from :: fMove :: nMove :: _ = entry.split(" ").toList
      Params(from.toInt, fMove.toDouble, nMove.toDouble)
    }
  }

  val neighbour = neighbourStr match {
    case "1D" => OneD
    case "2D" => NONE
    case _ => NONE
  }

  val distanceFunc: KohonenNetwork.DistFun = distanceFuncStr match {
    case "EUQLIDES" => DistanceFuncs.Euqlides _
    case _ => DistanceFuncs.Taxi _
  }

  val genFunc: Int => Double = distanceFuncStr.split(" ").toList match {
    case "RANDOM" :: from :: to :: _ =>
      _ => Random.nextDouble() * (to.toDouble - from.toDouble) + from.toDouble
    case _ => _ => 0

  }

  val network = Initialaizers.initNetwork(genFunc, distanceFunc)(
    amount = neighbourStr.toInt,
    inputs = inputSizeStr.toInt,
    nBuilder = neighbour
  )

  println(Trainer.train(
    minError = minErrorStr.toDouble,
    points = points.map(_.split(" ").toSeq.map(_.toDouble)),
    params = params
  )(network))


}
