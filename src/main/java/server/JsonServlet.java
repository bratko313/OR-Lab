package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JsonServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    resp.setContentType("text/json");
	    resp.setStatus(HttpServletResponse.SC_OK);
	    resp.setHeader("Content-Disposition", "attachment; filename=board_games.json");

	    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("board_games.json");
	    if (inputStream == null) {
	        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found!");
	        return;
	    }

	    OutputStream os = resp.getOutputStream();
	    
	    byte[] buffer = new byte[1024];
	    int bytesRead;
	    
	    while ((bytesRead = inputStream.read(buffer)) != -1) {
	        os.write(buffer, 0, bytesRead);
	    }
	    
	    inputStream.close();
	    os.flush();
	    os.close();
	}
}
