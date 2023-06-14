package io.github.joxit.pizzeria.vertx

import io.vertx.core.Promise
import io.vertx.core.Verticle
import io.vertx.core.spi.VerticleFactory
import java.util.concurrent.Callable
import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component
class SpringVerticleFactory : VerticleFactory, ApplicationContextAware {
  private lateinit var applicationContext: ApplicationContext

  override fun prefix(): String = "pizzeria-vertx"

  override fun createVerticle(verticleName: String, classLoader: ClassLoader, promise: Promise<Callable<Verticle>>) {
    val clazz = VerticleFactory.removePrefix(verticleName)
    promise.complete {
      applicationContext.getBean(Class.forName(clazz)) as Verticle
    }
  }

  @Throws(BeansException::class)
  override fun setApplicationContext(applicationContext: ApplicationContext) {
    this.applicationContext = applicationContext
  }
}