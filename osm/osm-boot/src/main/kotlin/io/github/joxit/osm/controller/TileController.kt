package io.github.joxit.osm.controller
import io.github.joxit.osm.model.Tile
import io.github.joxit.osm.service.TileService
import java.io.IOException
import mil.nga.sf.geojson.GeoJsonObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


/**
 * C'est le controlleur de l'application, il faut le déclarer comme tel et activer les CrossOrigin
 *
 * @author Jones Magloire @Joxit
 * @since 2019-11-03
 */
@RestController
@CrossOrigin
class TileController (@Autowired private val tileService: TileService){
  /**
   * Cette méthode est le point d'entrée de l'API, il prend les requêtes au format `/{z}/{x}/{y}.png`.
   * Attention, il doit renvoyer le header Content-Type image/png; voir les MediaType de Spring
   *
   * @param z zoom
   * @param x coordonée
   * @param y coordonée
   * @return l'image au format PNG
   */
  @GetMapping(value=["/{z}/{x}/{y}.png"], produces=["image/png"])
  fun getTile(@PathVariable z: Int,@PathVariable x: Int,@PathVariable y: Int): ByteArray? {
    return (tileService.getTile(Tile(z,x,y)))
  }

  /**
   * Cette méthode est le point d'entrée des préfectures, il prend les requêtes sur l'entrée `/prefectures.geojson`.
   * Attention, il doit renvoyer le header Content-Type application/json
   *
   * @return String representant le GeoJSON des prefectures
   */

  @Throws(IOException::class)
  @GetMapping(value = ["/prefectures.geojson"], produces = ["application/json"])
  fun getPrefectures(): String? {
    return tileService.getPrefectures()
  }

  /**
   * Cette méthode est le point d'entrée de l'API POIs sous persistence, il prend les requêtes sur l'entrée `/pois.geojson`.
   * Attention, il doit renvoyer le header Content-Type application/json
   *
   * @return le geojson des POIs à renvoyer
   */
  fun getPOIs(): GeoJsonObject {
    TODO("À implémenter, lisez la JAVADOC et les consignes !")
  }
}
