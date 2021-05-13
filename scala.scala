import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object Twitter {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("scala")
    val sc = new SparkContext(conf)
    val result = sc.textFile(args(0)).map(line => { val ids = line.split(",")
                                           			(ids(1).toLong, 1)})
                                      .reduceByKey(_ + _)
                                      .map{case(key, value) => (value, 1)}
                                      .reduceByKey(_ + _).sortByKey().collect()

    result.foreach(println)
  }
}
