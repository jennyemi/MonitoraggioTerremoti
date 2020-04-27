/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rilevamentoterremoti.servlet;

import com.mycompany.rilevamentoterremoti.DAO.CentralinaDAO;
import com.mycompany.rilevamentoterremoti.DAO.PuntoDiRilevamentoDAO;
import com.mycompany.rilevamentoterremoti.DAO.RilevazioneDAO;
import com.mycompany.rilevamentoterremoti.DAO.SismografoDAO;
import com.mycompany.rilevamentoterremoti.entity.Centralina;
import com.mycompany.rilevamentoterremoti.entity.PuntoDiRilevamento;
import com.mycompany.rilevamentoterremoti.entity.Rilevazione;
import com.mycompany.rilevamentoterremoti.entity.Sismografo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jennifer
 */
public class InserimentoDati extends HttpServlet {
@Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    try {
        Float magnitudo = Float.parseFloat(request.getParameter("magnitudo"));
        int profondità = Integer.parseInt(request.getParameter("profondità"));
        String Località = request.getParameter("località");
        
        Date dataOra = new Date();
        Rilevazione rilevazione = new Rilevazione();
        rilevazione.setDataOra(dataOra);
        rilevazione.setMagnitudo(magnitudo);
        rilevazione.setProfondità(profondità);
        rilevazione.setLocalità(Località);
        
        RilevazioneDAO.create(rilevazione);
        
        
        Long id_p_rilevamento = Long.parseLong(request.getParameter("p_rilevamento"));
                
        PuntoDiRilevamento puntoDiRilevamento = new PuntoDiRilevamento();
        
        puntoDiRilevamento.setId(id_p_rilevamento);
        
        PuntoDiRilevamentoDAO.create(puntoDiRilevamento);
        
        
        Long id_centralina = Long.parseLong(request.getParameter("centralina"));
        
        Centralina centralina = new Centralina();
        centralina.setId(id_centralina);
        centralina.setPuntoDiRilevamento(puntoDiRilevamento);
        
        CentralinaDAO.create(centralina);

        
        Long id_sismografo = Long.parseLong(request.getParameter("sismografo"));
        Sismografo sismografo = new Sismografo();
        sismografo.setId(id_sismografo);
        sismografo.setCentralina(centralina);
        
        SismografoDAO.create(sismografo);
        
        
        

        request.getRequestDispatcher("home.jsp").forward(request, response);
    } catch (Exception ex) {
        Logger.getLogger(InserimentoDati.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}
