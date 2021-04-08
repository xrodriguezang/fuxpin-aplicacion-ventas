package unir.tfg.ventas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import unir.tfg.ventas.model.Almacen;
import unir.tfg.ventas.services.IAlmacenService;

import java.util.List;

@Controller
public class VentasController {

    @Autowired
    private IAlmacenService almacenService;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("listAlmacenes")
    public String listAlmacenes(Model model) {
        List<Almacen> almazenes = almacenService.findAll();

        model.addAttribute("almacenes", almazenes);

        return "showListAlmacenes";
    }

    @GetMapping("listAlmacenes2")
    public String listAlmacenes2(Model model) {
        List<Almacen> almazenes = almacenService.findAll();

        model.addAttribute("almacenes", almazenes);

        return "showAlmacenes";
    }

}
