package annotation;

import dao.CarDao;
import dao.CarDaoImpl;
import model.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/cars")
public class MyAnnoServlet extends HttpServlet {
    private CarDao carDao;

    @Override
    public void init() throws ServletException {
        carDao = CarDaoImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        writeData(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Car c = getCar(req);
        c.setId(Integer.parseInt(req.getParameter("id")));
        carDao.update(c);
        writeData(resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        carDao.save(getCar(req));
        writeData(resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Car c = getCar(req);
        c.setId(Integer.parseInt(req.getParameter("id")));
        carDao.delete(c);
        writeData(resp);
    }

    private void writeData(HttpServletResponse resp) throws IOException {
        List<Car> cars = carDao.readAll();
        PrintWriter pw = resp.getWriter();
        for(Car c: cars){
            pw.append(c+"\n");
        }
        pw.flush();
    }

    private Car getCar(HttpServletRequest req){
        int cost = Integer.parseInt(req.getParameter("cost"));
        String model= req.getParameter("model");
        Car c = new Car();
        c.setModel(model);
        c.setCost(cost);
        return c;
    }
}
