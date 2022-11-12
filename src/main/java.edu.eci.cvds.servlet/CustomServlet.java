package edu.eci.cvds.servlet;

import edu.eci.cvds.servlet.model.Todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Optional;

@WebServlet(
        urlPatterns = "/CVDSProEdition"
)

public class CustomServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id; Todo todo; String name;
        Writer writer = resp.getWriter();

        try{
            ArrayList<Todo> todoList = new ArrayList<Todo>();
            Optional<String> optionalName = Optional.ofNullable(req.getParameter("id"));

            if(optionalName.isPresent() && !optionalName.get().isEmpty()){
                resp.setStatus(HttpServletResponse.SC_OK);
                name = optionalName.get();
            }
            else{
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                name = "";
            }

            id = Integer.parseInt(name);

            todo = Service.getTodo(id);
            todoList.add(todo);
            writer.write(Service.todosToHTMLTable(todoList));
        }
        catch(NumberFormatException n){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writer.write("requerimiento inválido");
        }
        catch(MalformedURLException m){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writer.write("error interno en el servidor");
        }
        catch(Exception e){
            resp.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            writer.write("requerimiento inválido");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id; Todo todo; String name;
        Writer writer = resp.getWriter();

        try{
            ArrayList<Todo> todoList = new ArrayList<Todo>();
            Optional<String> optionalName = Optional.ofNullable(req.getParameter("id"));

            if(optionalName.isPresent() && !optionalName.get().isEmpty()){
                resp.setStatus(HttpServletResponse.SC_OK);
                name = optionalName.get();
            }
            else{
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                name = "";
            }

            id = Integer.parseInt(name);

            todo = Service.getTodo(id);
            todoList.add(todo);
            writer.write(Service.todosToHTMLTable(todoList));
        }
        catch(NumberFormatException n){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writer.write("requerimiento inválido");
        }
        catch(MalformedURLException m){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writer.write("error interno en el servidor");
        }
        catch(Exception e){
            resp.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            writer.write("requerimiento inválido");
        }
    }
}