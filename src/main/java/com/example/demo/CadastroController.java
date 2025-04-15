package com.example.demo;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class CadastroController {

    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if ("usuario".equalsIgnoreCase(email)) {
            response.sendRedirect("http://localhost:8080/cadastro.html");
        } else {

            HttpSession session = request.getSession();
            session.setAttribute("logado", true);
            session.setAttribute("usuario", email);

            Cookie horarioCookie = new Cookie("horario_cadastro", LocalDateTime.now().toString());
            horarioCookie.setMaxAge(60 * 10);
            response.addCookie(horarioCookie);

            Cookie usuarioCookie = new Cookie("usuario", email);
            usuarioCookie.setMaxAge(60 * 10);
            response.addCookie(usuarioCookie);

            response.sendRedirect("http://localhost:8080/pagina.html");
        }
    }
}
