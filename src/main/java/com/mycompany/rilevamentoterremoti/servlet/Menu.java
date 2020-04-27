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
public class Menu extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String scelta = request.getParameter("menu");
        
        if (scelta.equals("data")){
        
        } else if (scelta.equals("località")){
        
        } else if (scelta.equals("magnitudo")){
        
        } else if (scelta.equals("profondità")){
        
        }

    }
}
