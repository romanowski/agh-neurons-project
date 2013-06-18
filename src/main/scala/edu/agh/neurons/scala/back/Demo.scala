package edu.agh.neurons.scala.back

import io.Source
import edu.agh.neurons.scala.activationFunctions.LineFunction

/**
 * Created with IntelliJ IDEA.
 * User: krzysiek
 * Date: 18.06.13
 * Time: 08:43
 * To change this template use File | Settings | File Templates.
 */
object Demo extends App {


  val sizesStr :: learnParams :: data = Source.fromFile("back/sample.txt").getLines().toList

  val sizes = sizesStr.split(" ").toList.map(_.toInt)
  val errSr :: iterSr :: learnSpeedStr :: _ = learnParams.split(" ").toList
  val points = data.map(_.split("#").toList.map(_.split(" ").toList.map(_.toDouble))).map {
    case in :: out :: Nil =>
      in -> out
  }

  val net = BackPropagationNetwork.build(sizes, LineFunction)

  val err = BackPropagationTrainer(net, learnSpeedStr.toDouble).upToMinErr(points)(errSr.toDouble, iterSr.toInt)

  println(s"Error $err")
  println(net)


  points.foreach {
    case (i, o) =>
      println(s"For $o got ${net.compute(i)}")
  }

}
