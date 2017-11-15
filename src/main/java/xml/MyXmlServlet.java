package xml;

import dao.PersonDao;
import dao.PersonDaoImpl;
import model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MyXmlServlet extends HttpServlet{
    private PersonDao personDao;

    @Override
    public void init() throws ServletException {
        personDao = PersonDaoImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        write(personDao.readAll(), resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person p = getPerson(req);
        personDao.save(p);
        write(personDao.readAll(), resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person p = getPerson(req);
        p.setId(Integer.parseInt(req.getParameter("id")));
        personDao.delete(p);
        write(personDao.readAll(), resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person p = getPerson(req);
        personDao.update(p, Integer.parseInt(req.getParameter("id")));
        write(personDao.readAll(), resp);
    }

    private Person getPerson(HttpServletRequest req){
        String name = req.getParameter("name");
        String age= req.getParameter("age");
        Person p = new Person();
        p.setName(name);
        p.setAge(Integer.parseInt(age));
        return p;
    }

    private void write(List<Person> persons, HttpServletResponse resp) throws IOException {
        PrintWriter pw = resp.getWriter();
        for(Person p: persons){
            pw.append(p+"\n");
        }
        pw.flush();

    }

}
