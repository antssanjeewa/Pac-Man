
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/game", "/UpdateGame"})
public class GameHandler extends HttpServlet {

    Player player;

    @Override
    public void init() {
        player = new Player("P1", 8, 0, 0);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/event-stream;charset=UTF-8");
        PrintWriter out = response.getWriter();
        while (!Thread.interrupted()) {
            synchronized (this) {
                String code = "{ \"DOTS\":[ "+ player.getdot() +"], \"PLAYERS\": [ " + player.getupdate() + " , [\"P2\", 5, 44, 0], [\"P3\", -6, 0, 44], [\"P4\", 10, 44, 44]] }";
                out.print("data: ");
                out.println(code);
                out.println();
                out.flush();
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        synchronized (this) {
            String key = request.getParameter("keypress");
            int val = Integer.valueOf(key);
            if (val == 37) {
                player.update(-1, 0);
            } else if (val == 38) {
                player.update(0,-1);
            } else if (val == 39) {
                player.update(1,0);
            } else if (val == 40) {
                player.update(0, 1);
            }
            notifyAll();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
