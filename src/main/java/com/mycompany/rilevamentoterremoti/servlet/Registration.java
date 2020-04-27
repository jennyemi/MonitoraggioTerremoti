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
import java.security.SecureRandom;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Jennifer
 */
public class Registration extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //SALT
        Random random = new SecureRandom();

        byte[] SaltGeneration = new byte[16];
        random.nextBytes(SaltGeneration);

        String salt = DatatypeConverter.printBase64Binary(SaltGeneration);

        //SHA
        String passwordEncrypted = password + salt;

        Hasher hasher = Hashing.sha256().newHasher();

        hasher.putString(passwordEncrypted, Charsets.UTF_8);

        String sha256 = hasher.hash().toString();

        Utente u = new Utente();
        u.setUsername(username);
        u.setPassword(sha256);
        u.setSalt(salt);
        if (username.equals("admin")) {
            u.setPermissions("admin");
        } else {
            u.setPermissions("user");
        }

        UtenteDAO.create(u);

        request.getRequestDispatcher("index.html").forward(request, response);

    }
}
