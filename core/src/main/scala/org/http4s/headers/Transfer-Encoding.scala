package org.http4s
package headers

import org.http4s.parser.HttpHeaderParser
import org.http4s.util.NonEmptyList

object `Transfer-Encoding` extends HeaderKey.Internal[`Transfer-Encoding`] with HeaderKey.Recurring {
  override def parse(s: String): ParseResult[`Transfer-Encoding`] =
    HttpHeaderParser.TRANSFER_ENCODING(s)
}

final case class `Transfer-Encoding`(values: NonEmptyList[TransferCoding]) extends Header.RecurringRenderable {
  override def key: `Transfer-Encoding`.type = `Transfer-Encoding`
  def hasChunked: Boolean = values.exists(_.renderString.equalsIgnoreCase("chunked"))
  type Value = TransferCoding
}

