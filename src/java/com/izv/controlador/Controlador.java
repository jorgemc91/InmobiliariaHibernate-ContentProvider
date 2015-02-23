package com.izv.controlador;


import com.izv.hibernate.Inmueble;
import com.izv.modelo.ModeloInmobiliaria;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet(name = "Controlador", urlPatterns = {"/Control"})
@MultipartConfig
public class Controlador extends HttpServlet {
    
    boolean error;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destino = "index.html";
        boolean forward = false;
        String target,op,action,view;
        
        //...
        target = request.getParameter("target");
        op = request.getParameter("op");
        action = request.getParameter("action");
        view = request.getParameter("view");
        
        if(target.equals("inmueble") && op.equals("select") && action.equals("view")){
            forward = true;
            destino = "WEB-INF/inmueble/index.jsp";
            request.setAttribute("datos", ModeloInmobiliaria.get());
        }else if(target.equals("inmueble") && op.equals("delete") && action.equals("op")){
            forward = false;
            ModeloInmobiliaria.delete(request.getParameter("id"));
            destino = "Control?target=inmueble&op=select&action=view";
        }else if(target.equals("inmueble") && op.equals("target") && action.equals("view")){
            forward = true;
            destino = "WEB-INF/inmueble/insert.jsp";
        }else if(target.equals("inmueble") && op.equals("insert") && action.equals("op") && view.equals("web")){
            forward = false;
            Inmueble inmueble = new Inmueble();
            inmueble.setDireccion(request.getParameter("direccion"));
            inmueble.setNumero(request.getParameter("numero"));
            inmueble.setPrecio(request.getParameter("precio"));
            inmueble.setTipo(request.getParameter("tipo"));
            inmueble.setDescripcion(request.getParameter("descripcion"));
            
            error = false;
            Part filePart = request.getPart("archivo");
            String fileName = getFileName(filePart);
            InputStream fileContent = filePart.getInputStream();
            String destinoImg = getServletContext().getRealPath("/") + "imagenes/";
            guardarImagen(fileContent, fileName, destinoImg);
            response.setContentType("application/json");
            inmueble.setImagen(fileName);
            ModeloInmobiliaria.insert(inmueble);
            destino = "Control?target=inmueble&op=select&action=view";
        }else if(target.equals("inmueble") && op.equals("insert") && action.equals("op") && view.equals("android")){
            forward = false;
            Inmueble inmueble = new Inmueble();
            inmueble.setDireccion(request.getParameter("direccion"));
            inmueble.setNumero(request.getParameter("numero"));
            inmueble.setPrecio(request.getParameter("precio"));
            inmueble.setTipo(request.getParameter("tipo"));
            inmueble.setDescripcion(request.getParameter("descripcion"));
            inmueble.setImagen(request.getParameter("imagen"));
            ModeloInmobiliaria.insert(inmueble);
            destino = "Control?target=inmueble&op=select&action=view";
        }else if(target.equals("inmueble") && op.equals("update") && action.equals("view")){
            forward = true;
            request.setAttribute("inmueble", ModeloInmobiliaria.getParametro(request.getParameter("id")));
            destino = "WEB-INF/inmueble/update.jsp";
        }else if(target.equals("inmueble") && op.equals("update") && action.equals("op")){
            forward = false;
            Inmueble inmueble = new Inmueble();
            inmueble.setDireccion(request.getParameter("direccion"));
            inmueble.setNumero(request.getParameter("numero"));
            inmueble.setPrecio(request.getParameter("precio"));
            inmueble.setTipo(request.getParameter("tipo"));
            inmueble.setDescripcion(request.getParameter("descripcion"));
            inmueble.setImagen(request.getParameter("imagen"));
            inmueble.setId(Integer.parseInt(request.getParameter("id")));
            ModeloInmobiliaria.update(inmueble);
            destino = "Control?target=inmueble&op=select&action=view";
        }
        
        //transferencia de control
        if(forward){
            request.getRequestDispatcher(destino).forward(request, response);
        }else{
            response.sendRedirect(destino);
        }
    }
    
    
    private void guardarImagen(InputStream fileContent, String fileName, String destino){
        try {
            BufferedImage img = ImageIO.read(fileContent);
            if(fileName.endsWith("png"))
                ImageIO.write(img, "png", new File(destino + fileName));
            else if (fileName.endsWith("jpg"))
                ImageIO.write(img, "jpg", new File (destino + fileName));
        } catch (Exception ex) {
            error = true;
        }
    }

    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
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
