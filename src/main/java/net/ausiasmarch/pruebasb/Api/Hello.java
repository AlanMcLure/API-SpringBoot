package net.ausiasmarch.pruebasb.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import net.ausiasmarch.pruebasb.bean.ResponseBean;
import net.ausiasmarch.pruebasb.bean.UserBean;
import net.ausiasmarch.pruebasb.bean.EquationBean;

@RestController
@RequestMapping("/hello")

public class Hello {

    @Autowired
    private HttpServletRequest oRequest;

    @GetMapping(value = "/", produces = "application/json")
    public ResponseBean hello() {
        ResponseBean oResponseBean = new ResponseBean();
        oResponseBean.setMessage("Hello World");
        oResponseBean.setCode(10);
        return oResponseBean;
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseBean helloPost(@RequestBody UserBean oUserBean) {

        if (oUserBean.getUsername().equals("alan") && oUserBean.getPassword().equals("1234")) {
            oRequest.getSession().setAttribute("daw", oUserBean);
            ResponseBean oResponseBean = new ResponseBean();
            oResponseBean.setMessage("Bienvenido");
            oResponseBean.setCode(10);
            return oResponseBean;
        } else {
            ResponseBean oResponseBean = new ResponseBean();
            oResponseBean.setMessage("Error");
            oResponseBean.setCode(20);
            return oResponseBean;
        }

    }

    @PostMapping(value = "/calcula", produces = "application/json")
    public ResponseBean calcula(@RequestBody EquationBean equation) {
        // Verifica si el usuario está autenticado
        UserBean authenticatedUser = (UserBean) oRequest.getSession().getAttribute("daw");
        if (authenticatedUser == null) {
            ResponseBean oResponseBean = new ResponseBean();
            oResponseBean.setMessage("Usuario no autenticado");
            oResponseBean.setCode(401); // Código de respuesta no autorizado
            return oResponseBean;
        }

        // El usuario está autenticado, realiza el cálculo de la ecuación de segundo
        // grado
        double a = equation.getA();
        double b = equation.getB();
        double c = equation.getC();

        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            // La ecuación no tiene soluciones reales
            ResponseBean oResponseBean = new ResponseBean();
            oResponseBean.setMessage("La ecuación no tiene soluciones reales");
            oResponseBean.setCode(20);
            return oResponseBean;
        } else if (discriminant == 0) {
            // Hay una única solución real
            double x = -b / (2 * a);
            ResponseBean oResponseBean = new ResponseBean();
            oResponseBean.setMessage("La solución única es x = " + x);
            oResponseBean.setCode(10);
            return oResponseBean;
        } else {
            // Hay dos soluciones reales
            double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            ResponseBean oResponseBean = new ResponseBean();
            oResponseBean.setMessage("Las soluciones son x1 = " + x1 + " y x2 = " + x2);
            oResponseBean.setCode(10);
            return oResponseBean;
        }
    }

    @GetMapping(value = "/check", produces = "application/json")
    public ResponseBean checkLoginStatus() {
        // Verifica si hay una sesión activa con el nombre "daw"
        if (oRequest.getSession().getAttribute("daw") != null) {
            // El usuario está logueado
            ResponseBean oResponseBean = new ResponseBean();
            oResponseBean.setMessage("Usuario logueado");
            oResponseBean.setCode(10);
            return oResponseBean;
        } else {
            // El usuario no está logueado
            ResponseBean oResponseBean = new ResponseBean();
            oResponseBean.setMessage("Usuario no logueado");
            oResponseBean.setCode(20);
            return oResponseBean;
        }
    }

    @DeleteMapping(value = "/logout", produces = "application/json")
    public ResponseBean logout() {
        // Invalida la sesión actual
        oRequest.getSession().invalidate();

        // Retorna una respuesta de éxito
        ResponseBean oResponseBean = new ResponseBean();
        oResponseBean.setMessage("Sesión cerrada exitosamente");
        oResponseBean.setCode(10);
        return oResponseBean;
    }

}
