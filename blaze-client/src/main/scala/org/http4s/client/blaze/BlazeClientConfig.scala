package org.http4s.client
package blaze

import java.nio.channels.AsynchronousChannelGroup
import java.util.concurrent.ExecutorService
import javax.net.ssl.SSLContext

import org.http4s.headers.`User-Agent`

import scala.concurrent.duration.Duration

/** Config object for the blaze clients
  *
  * @param idleTimeout duration that a connection can wait without traffic before timeout
  * @param requestTimeout maximum duration for a request to complete before a timeout
  * @param userAgent optional custom user agent header
  * @param sslContext optional custom `SSLContext` to use to replace the default
  * @param endpointAuthentication require endpoint authentication for encrypted connections
  * @param maxResponseLineSize maximum length of the request line
  * @param maxHeaderLength maximum length of headers
  * @param maxChunkSize maximum size of chunked content chunks
  * @param lenientParser a lenient parser will accept illegal chars but replaces them with � (0xFFFD)
  * @param bufferSize internal buffer size of the blaze client
  * @param customExecutor custom executor to run async computations. Will not be shutdown with client.
  * @param group custom `AsynchronousChannelGroup` to use other than the system default
  */
final case class BlazeClientConfig(// HTTP properties
                                   idleTimeout: Duration,
                                   requestTimeout: Duration,
                                   userAgent: Option[`User-Agent`],

                                   // security options
                                   sslContext: Option[SSLContext],
                                   endpointAuthentication: Boolean,

                                   // parser options
                                   maxResponseLineSize: Int,
                                   maxHeaderLength: Int,
                                   maxChunkSize: Int,
                                   lenientParser: Boolean,

                                   // pipeline management
                                   bufferSize: Int,
                                   customExecutor: Option[ExecutorService],
                                   group: Option[AsynchronousChannelGroup]
                                  )

object BlazeClientConfig {
  /** Default user configuration
    */
  val defaultConfig =
    BlazeClientConfig(
      idleTimeout = bits.DefaultTimeout,
      requestTimeout = Duration.Inf,
      userAgent = bits.DefaultUserAgent,

      sslContext = None,
      endpointAuthentication = true,

      maxResponseLineSize = 4*1024,
      maxHeaderLength = 40*1024,
      maxChunkSize = Integer.MAX_VALUE,
      lenientParser = false,

      bufferSize = bits.DefaultBufferSize,
      customExecutor = None,
      group = None
    )
}
