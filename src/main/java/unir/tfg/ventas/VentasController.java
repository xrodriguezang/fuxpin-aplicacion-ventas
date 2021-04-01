package unir.tfg.ventas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import unir.tfg.ventas.model.AlmazenFigolers;
import unir.tfg.ventas.services.IAlmazenFigolersServices;

import java.util.List;

@Controller
public class VentasController {

    @Autowired
    private IAlmazenFigolersServices almazenFigolersServices;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("almacenes")
    public String getAlmacenes(Model model) {
        List<AlmazenFigolers> almazenes = almazenFigolersServices.findAll();

        model.addAttribute("almacenes", almazenes);

        return "showAlmacenes";
    }

}
