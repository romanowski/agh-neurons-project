package edu.agh.neurons.scala.back

import edu.agh.neurons.scala.activationFunctions.ActivationFunction
import edu.agh.neurons.scala.Layer

/**
 * Created with IntelliJ IDEA.
 * User: krzysiek
 * Date: 18.06.13
 * Time: 08:01
 * To change this template use File | Settings | File Templates.
 */
case class BackPropagationTrainer(var net: BackPropagationNetwork, learnSpeed: Double) {

  type DataSet = Seq[(Seq[Double], Seq[Double])]


  def onPoint(el: (Seq[Double], Seq[Double])): Double = onPoint(el._1, el._2)

  def onPoint(input: Seq[Double], out: Seq[Double]): Double = {

    //println(net)


    val computed = net.compute(input)

    val errors = ouputErrors(computed, out)

    println(errors, out)



    val backErrors = net.layers.reverse.foldLeft(Seq(errors)) {
      (prev, layer) =>
        val laterError = layer.neurons.zip(prev.head).map {
          case (n, w) => n.weights.map(w *)
        }.foldLeft(layer.neurons.map(_ => 0.0)) {
          case (o, err) => o.zip(err).map(e => e._1 + e._2)
        }
        laterError +: prev

    }.reverse

    //println(backErrors)

    net.layers.zip(backErrors).foldLeft((List[Layer](), input)) {
      case ((list, input), (layer, errors)) =>
        val out = layer.neurons.map(_.activate(input))
        layer.neurons.zip(errors).map {
          case (n, err) =>
            n.weights = n.weights.zip(input).map {
              case (w, x) => w + computeDelta(n.activationFunction)(err)(x)
            }
        }
        (layer +: list) -> out
    }

    overallError(errors)
  }

  def ouputErrors(in: Seq[Double], out: Seq[Double]): Seq[Double] =
    out.zip(in).map(e => e._1 - e._2)

  def error(in: Seq[Double], out: Seq[Double]): Double = overallError(ouputErrors(in, out))

  def onDataSet(dataSet: DataSet): Double = {
    println("Epoch")
    overallError(dataSet.map(onPoint))
  }


  def upToMinErr(dataSet: DataSet)(minErr: Double, maxIter: Int): Double =
    (1 to maxIter - 1)
      .toStream.map(_ => onDataSet(dataSet)).dropWhile(_ > minErr)
      .headOption.getOrElse(onDataSet(dataSet))


  def computeDelta(func: ActivationFunction)(err: Double)(input: Double): Double =
    learnSpeed * err * func.d(input)

  def overallError(errs: Seq[Double]) = errs.map(math.abs).max
}
