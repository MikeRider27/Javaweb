package Controlador;

import DAO.DepositoDAO;
import DAO.EmpleadoDAO;
import DAO.Factura_CompraDAO;
import DAO.MercaderiaDAO;
import DAO.Orden_CompraDAO;
import DTO.Factura_CompraDTO;
import DTO.Orden_CompraDTO;
import Generico.Genericos;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Factura_CompraCTR extends HttpServlet {

    JRBeanCollectionDataSource datos;
    ServletContext contexto;
    InputStream input;
    ServletOutputStream out_report;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        System.out.println("Llegamos al controlador");
        String cadenaJSON = Genericos.deRequestToJson(request);
        System.out.println("JSON OBTENIDO " + cadenaJSON);
        Gson gson = new Gson();
        Factura_CompraDTO factura_compraDTO;
        Factura_CompraDAO factura_compraDAO;
        factura_compraDTO = gson.fromJson(cadenaJSON, Factura_CompraDTO.class);
        factura_compraDAO = new Factura_CompraDAO();
        
        Orden_CompraDTO orden_compraDTO;
        Orden_CompraDAO orden_compraDAO;
        orden_compraDTO = gson.fromJson(cadenaJSON, Orden_CompraDTO.class);
        orden_compraDAO = new Orden_CompraDAO();
        
        switch (factura_compraDTO.getBandera()) {
            case 1:
                if (orden_compraDAO.agregar(orden_compraDTO)) {
                    //Operación exitosa => al cliente
                } else {
                    //Operación errónea => al cliente
                }
                break;
            case 2:
                response.setContentType("application/json, charset=UTF-8");
                String jsonCabDetCompra;
                jsonCabDetCompra = gson.toJson(orden_compraDAO.recuperarOrdenCompra(orden_compraDTO));
                System.out.println("json Cab Obtenido " + jsonCabDetCompra);
                out.println(jsonCabDetCompra);
                out.close();
                break;
            case 3:
                if (orden_compraDAO.eliminar(orden_compraDTO)) {
                    //Operación exitosa => al cliente
                } else {
                    //Operación errónea => al cliente
                }
                break;
            case 4:
                //Seleccionar Solicitud
                response.setContentType("application/json, charset=UTF-8");
                Orden_CompraDAO ordenDAO = new Orden_CompraDAO();
                String cadenaorden = gson.toJson(ordenDAO.seleccionarTodos());
                if (cadenaorden != null) {
                    //enviar al js la cadena 
                    System.out.println("Cadena " + cadenaorden);
                    out.println(cadenaorden);
                } else {
                    //enviar alguna respuesta para indicar error
                }
                break;
            case 5:
                //Seleccionar Deposito
                response.setContentType("application/json, charset=UTF-8");
                DepositoDAO depositoDAO = new DepositoDAO();
                String cadenadeposito = gson.toJson(depositoDAO.seleccionarTodos());
                if (cadenadeposito != null) {
                    //enviar al js la cadena 
                    System.out.println("Cadena " + cadenadeposito);
                    out.println(cadenadeposito);
                } else {
                    //enviar alguna respuesta para indicar error
                }
                break;
            case 6:
                //Seleccionar Mercaderia
                response.setContentType("application/json, charset=UTF-8");
                MercaderiaDAO mercaderiaDAO = new MercaderiaDAO();
                String cadenademercaderia = gson.toJson(mercaderiaDAO.seleccionarTodos());
                if (cadenademercaderia != null) {
                    //enviar al js la cadena 
                    System.out.println("Cadena " + cadenademercaderia);
                    out.println(cadenademercaderia);
                } else {
                    //enviar alguna respuesta para indicar error
                }
                break;
            case 7:
                //Seleccionar Empleado
                response.setContentType("application/json, charset=UTF-8");
                EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                String cadenaempleado = gson.toJson(empleadoDAO.seleccionarTodos());
                if (cadenaempleado != null) {
                    //enviar al js la cadena 
                    System.out.println("Cadena " + cadenaempleado);
                    out.println(cadenaempleado);
                } else {
                    //enviar alguna respuesta para indicar error
                }
                break;
//            case 8:
//                //Seleccionar Proveedor
//                response.setContentType("application/json, charset=UTF-8");
//                ProveedorDAO proveedorDAO = new ProveedorDAO();
//                String cadenaproveedor = gson.toJson(proveedorDAO.seleccionarTodos());
//                if (cadenaproveedor != null) {
//                    //enviar al js la cadena 
//                    System.out.println("Cadena " + cadenaproveedor);
//                    out.println(cadenaproveedor);
//                } else {
//                    //enviar alguna respuesta para indicar error
//                }
//                break;
            case 9:
                if (orden_compraDAO.modificar(orden_compraDTO)) {
                    //Operación exitosa => al cliente
                } else {
                    //Operación errónea => al cliente
                }
                break;
            case 10:
                //Seleccionar Solicitud
                response.setContentType("application/json, charset=UTF-8");
//                Orden_CompraDAO orden_compraDAO = new Orden_CompraDAO();
                String cadenaorden_compra = gson.toJson(orden_compraDAO.seleccionarTodos());
                if (cadenaorden_compra != null) {
                    //enviar al js la cadena 
                    System.out.println("Cadena " + cadenaorden_compra);
                    out.println(cadenaorden_compra);
                } else {
                    //enviar alguna respuesta para indicar error
                }
                break;
            case 11:
                response.setContentType("application/json, charset=UTF-8");
                String jsonCabDet_Orden_Compra;
                jsonCabDet_Orden_Compra = gson.toJson(orden_compraDAO.recuperarOrdenCompra(orden_compraDTO));
                System.out.println("json Cab Obtenido " + jsonCabDet_Orden_Compra);
                out.println(jsonCabDet_Orden_Compra);
                out.close();
                break;
            default:
                throw new AssertionError();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
