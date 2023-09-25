package io.github.joxit.osm.service
import io.github.joxit.osm.utils.Svg.getTile as getTileSVG

import io.github.joxit.osm.model.Tile
import java.io.IOException
import org.springframework.stereotype.Service
import org.springframework.util.StreamUtils
import kotlin.math.pow

/**
 * Service pour retourner les tuiles.
 *
 * @author Jones Magloire @Joxit
 * @since 2019-11-03
 */
@Service
class TileService {
  /**
   * Ici il faut prendre les coordonnées de la tuile et renvoyer la donnée PNG associée.
   * Vous pouvez y ajouter des fonctionnalités en plus pour améliorer les perfs.
   *
   * @param tile qu'il faut renvoyer
   * @return le byte array au format png
   */
  fun getTile(tile: Tile?): ByteArray? {

    if (tile != null) {
      if (tile.z < 0 || tile.z > 24 || tile.x < 0 || tile.y < 0 || tile.x >= 2.0.pow(tile.z.toDouble()).toInt() || tile.y >= 2.0.pow(tile.z.toDouble()).toInt()) {
        throw IllegalArgumentException("Valeurs de tuile invalides.")
      }
    }

    return tile?.let { getTileSVG(it) }
  }

  /**
   * Attention, il ne faut pas utiliser des fonctions utilisant un fichier de votre file system comme File ou FileInputStream.
   * Vous devez utiliser le classpath du projet à la place Cf Resources de Spring.
   *
   * @return le contenu du fichier prefectures.geojson
   */
  @Throws(IOException::class)
  fun getPrefectures(): String {
    val inputStream = javaClass.classLoader.getResourceAsStream("prefectures.geojson")

    if (inputStream != null) {
      try {
        return StreamUtils.copyToString(inputStream, Charsets.UTF_8)
      } finally {
        inputStream.close()
      }
    } else {
      throw IOException("Fichier GeoJSON introuvable dans les ressources du classpath.")
    }
  }

  /**
   * Il faudra créer votre DAO pour récuperer les données.
   * Utilisez ce que vous voulez pour faire le DAO.
   *
   * @return les éléments contenus dans la base de données
   */
  /**fun getPOIs(): GeoJsonObject {

  }**/
}
