
import com.mycompany.rilevamentoterremoti.DAO.CentralinaDAO;
import com.mycompany.rilevamentoterremoti.DAO.PuntoDiRilevamentoDAO;
import com.mycompany.rilevamentoterremoti.DAO.RilevazioneDAO;
import com.mycompany.rilevamentoterremoti.DAO.SismografoDAO;
import com.mycompany.rilevamentoterremoti.entity.Centralina;
import com.mycompany.rilevamentoterremoti.entity.PuntoDiRilevamento;
import com.mycompany.rilevamentoterremoti.entity.Rilevazione;
import com.mycompany.rilevamentoterremoti.entity.Sismografo;
import java.io.*;
import java.util.List;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Elia
 */
public class TableUpdate extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Centralina> lc = CentralinaDAO.findCentralinaEntities();
        List<Rilevazione> lr = RilevazioneDAO.findRilevazioneEntities();
        List<PuntoDiRilevamento> lp = PuntoDiRilevamentoDAO.findPuntoDiRilevamentoEntities();
        List<Sismografo> ls = SismografoDAO.findSismografoEntities();
        
        String path = "C:/html.txt";
        File file = new File(path);
        
        
    }

}
