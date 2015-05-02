/**
 * @author Petri Kivikangas
 * @date 8.11.2012
 *
 */
package cdl.neo4j.wrappers

import java.io.File
import java.net.URI

import org.glassfish.jersey.client.ClientProperties
import org.neo4j.cypher.ExecutionResult

import cdl.objects.UW
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.core.{ MediaType, Response }

class RestNeoWrapper(uri: String) extends CDLNeoWrapper {
  def getHyponyms(uw: UW): Iterator[UW] = {
    //TODO: implement
    return null
  }
  def query(q: String): ExecutionResult = {
    //TODO: implement
    return null
  }
  def cleanDB(): Boolean = {
    //TODO: implement
    return false
  }
  def fetchUWs(hw: String): Iterator[UW] = {
    //TODO: implement
    return null
  }
  def importDocuments(docs: Array[File]): Boolean = {
    //TODO: implement
    return false
  }
  def importOntology(): Boolean = {
    //TODO: implement
    return false
  }

  def start: Boolean = {
    if (neoURI == null) {
      try {
        neoURI = new URI(uri)
      } catch { case ex: Exception => log.error(ex.toString); neoURI = null; return false }
      if (isConnected) {
        log.info("RestNeoWrapper started at "+getNeoURI)
        return true
      }
    }
    return false
  }

  def stop: Boolean = {
    neoURI = null
    return true
  }

  def isConnected: Boolean = {
    val request = ClientBuilder.newClient
      .target(neoURI)
      .path("/")
      .request(MediaType.APPLICATION_JSON_TYPE)
      .property(ClientProperties.CONNECT_TIMEOUT, 1000)
    var response: Response = null
    try {
      response = request.get
    } catch { case ex: Exception => log.error(ex.toString); return false }
    log.info("Connection to <"+neoURI.getPath+"> works")
    return response.getStatus == 200
  }
  /*
  private def isValidURL(url: String): Boolean = {
    try {
      val url = new URL(accessPoint)
      return true
    } catch { case ex: MalformedURLException => log.error(ex.toString); return false }
  }
  */
}