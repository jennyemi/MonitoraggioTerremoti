/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rilevamentoterremoti.servlet;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
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
public class Login extends HttpServlet {
@Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Utente u = UtenteDAO.findUtentebyUsername(username);
        
        String salt = u.getSalt();

        String passwordEncrypted = password + salt;

        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(passwordEncrypted, Charsets.UTF_8);
        String sha256 = hasher.hash().toString();

        if (sha256.equals(u.getPassword())) {

            HttpSession session = request.getSession();
            session.setAttribute("username", u.getUsername());
            
            request.setCharacterEncoding("UTF-8");
            
            request.getRequestDispatcher("index.html").forward(request, response);

        } else {

            request.getRequestDispatcher("accessoNegato.html").forward(request, response);
        }

    }
}
