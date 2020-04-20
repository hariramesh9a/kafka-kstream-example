import java.util.Properties
import java.util.concurrent.TimeUnit

import org.apache.kafka.streams.kstream.Printed
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.scala.kstream._
import com.goyeau.kafka.streams.circe.CirceSerdes._
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}


object LogEndpointCountExample extends App {

  import ImplicitConversions._
  import Serdes._

  val config: Properties = {
    val p = new Properties()
    p.put(StreamsConfig.APPLICATION_ID_CONFIG, "endpointCount")
    val bootstrapServers = if (args.length > 0) args(0) else "localhost:9092"
    p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
    p
  }

  case class Logs(tid: String, name: String, endpint: String, postCommand: String, source: String, code1: String, code2: String,
                  code3: String, uid: String, size1: String, size2: String, size3: String, size4: String, status: String, nameSpace: String,
                  hashId: String, ip: String) {
    def as[T](implicit f: Logs => T): T = f(this)
  }


  val streamsBuilder = new StreamsBuilder()
  //  val textLines: KStream[String, String] = builder.stream[String, String]("logs")


  val logStreams = streamsBuilder.stream[String, String]("logs")

  val ktable = logStreams.mapValues(_.split('|')).map((a, b) => (a, b(3))).groupBy((_, api) => api).count()
  ktable.toStream.print(Printed.toSysOut())
  //
  //  println(wordCounts)

  val streams: KafkaStreams = new KafkaStreams(streamsBuilder.build(), config)
  streams.start()

  sys.ShutdownHookThread {
    streams.close(10, TimeUnit.SECONDS)
  }

}