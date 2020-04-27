/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rilevamentoterremoti.servlet;

import com.mycompany.rilevamentoterremoti.DAO.UtenteDAO;
import com.mycompany.rilevamentoterremoti.entity.Utente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jennifer
 */
public class Dati extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        String username = (String) session.getAttribute("username");

        List<Utente> utenteList = UtenteDAO.findUtenteEntities();
        for (Utente u : utenteList) {
            if (username.equals(u.getUsername())) {
                String permesso = u.getPermissions();

                if (permesso.equals("admin")) {
                    request.getRequestDispatcher("inserimentoDati.html").forward(request, response);
                } else {
                    request.getRequestDispatcher("userNotPermitted.html").forward(request, response);
                }
            }
        }
    }
}
