package edu.agh.neurons.scala.kohonen

import scala.io.Source
import neighbours._
import scala.util.Random
import scala._
import edu.agh.neurons.scala.kohonen.Params

case class Params(toEpoch: Int, fMove: Double, nMove: Double)


object Demo extends App {

  val neighbourStr :: neuronsSizeStr :: inputSizeStr :: distanceFuncStr :: genFuncStr :: paramsStr :: minErrorStr :: points =
    Source.fromFile(args(0)).getLines().toList


  val params = paramsStr.split(", +").map {
    entry => {
      val to :: fMove :: nMove :: _ = entry.split(" +").toList
      Params(to.toInt, fMove.toDouble, nMove.toDouble)
    }
  }

  val neighbour = neighbourStr match {
    case "1D" => OneD
    case "2D" => TwoD
    case _ => NONE
  }

  val distanceFunc: KohonenNetwork.DistFun = distanceFuncStr match {
    case "EUQLIDES" => DistanceFuncs.Euqlides _
    case _ => DistanceFuncs.Taxi _
  }

  val genFunc: Int => Double = genFuncStr.split(" ").toList match {
    case "RANDOM" :: from :: to :: _ =>
      _ => Random.nextDouble() * (to.toDouble - from.toDouble) + from.toDouble
    case _ => _ => 0

  }

  val network = Initialaizers.initNetwork(genFunc, distanceFunc)(
    amount = neuronsSizeStr.toInt,
    inputs = inputSizeStr.toInt,
    nBuilder = neighbour
  )

  println("init network")
  println(network)

  println(Trainer.train(
    minError = minErrorStr.toDouble,
    points = points.map(_.split(" ").toSeq.map(_.toDouble)),
    params = params
  )(network))

  println("final network")
  println(network)
}
