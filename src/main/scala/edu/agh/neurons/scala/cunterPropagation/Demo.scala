package edu.agh.neurons.scala.cunterPropagation

import io.Source
import edu.agh.neurons.scala.kohonen.neighbours._
import edu.agh.neurons.scala.kohonen._
import util.Random
import edu.agh.neurons.scala.Layer
import edu.agh.neurons.scala.{Neuron => NNeuron}
import edu.agh.neurons.scala.activationFunctions.ActivationFunctions

object Demo extends App {

  val neighbourStr :: neuronsSizeStr :: inputSizeStr :: outputSizeStr :: distanceFuncStr :: paramsStr :: genFuncStr :: activationFunctionStr :: minErrorStr :: maxIteratrionString :: points =
    Source.fromFile(args(0)).getLines().toList


  val outMove :: fMove :: nMove :: Nil = paramsStr.split(" +").map(_.toDouble).toList

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

  val trainSet = points.map(_.split(" ").map(_.toDouble)).map(l => l.head -> l.tail.toList)

  val outLayer = Layer((1 to outputSizeStr.toInt).map {
    _ => NNeuron((1 to neuronsSizeStr.toInt).map(genFunc), 0, ActivationFunctions.forName(activationFunctionStr))
  })


  val cp = CPNetwork(network, outLayer)

  (1 to maxIteratrionString.toInt).takeWhile {
    _ =>
      trainSet.foreach {
        case (e, data) =>
          cp.train(fMove, nMove, outMove)(data, List(e))
      }
      val minError = trainSet.map {
        case (e, data) => math.abs(cp.output(data).head - e)
      }
      minError.max > minErrorStr.toDouble
  }

  trainSet.foreach {
    case (e, data) =>
      println("for: %s got %s expected %s".format(
        data.mkString("[", ", ", "]"),
        cp.kochNetwork.output(data),
        e
      ))
      println(cp.kochNetwork.findBest(data))
  }

  println(cp.kochNetwork)


}
