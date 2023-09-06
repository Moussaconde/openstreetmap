package io.github.joxit.pizzeria.webapp

import io.github.joxit.pizzeria.service.PizzeriaService
import jakarta.ws.rs.QueryParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping(path = ["/index.html", "/"])
class PizzeriaWebApp(
  @Autowired private val pizzeriaService: PizzeriaService
) {
  @RequestMapping(method = [RequestMethod.GET])
  fun getPizzeria(@QueryParam("type") type: String?, model: ModelMap): String {
    model.addAttribute("pizzas", pizzeriaService.getAll(type))
    return "index"
  }
}
