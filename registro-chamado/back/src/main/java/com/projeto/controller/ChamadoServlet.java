package com.projeto.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.projeto.model.Chamado;
import com.projeto.model.ChamadoDAO;

@WebServlet(urlPatterns = {"/chamado"})
public class ChamadoServlet extends HttpServlet {
    private static String responseType = "application/json;charset=UTF-8";
    private ChamadoDAO chamadoDAO;

    public ChamadoServlet() {
        chamadoDAO = new ChamadoDAO(); 
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType(responseType);

        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();


        try {
            out.print(new JSONArray(chamadoDAO.listarTodos()));
        } catch (Exception e) {
            json.put("success", false);
            json.put("error", e.toString());
            out.print(json.toString());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType(responseType);
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();

        try {
            Chamado prod = new Gson().fromJson(request.getReader(), Chamado.class);
            var chamado = chamadoDAO.inserir(prod);

            JSONObject jsonProd = new JSONObject();
            jsonProd.put("chamado", chamado);
            jsonProd.put("titulo", prod.getTitulo());
            jsonProd.put("status", prod.getStatus());
            jsonProd.put("comentario", prod.getComentario());

            json.put("success", true);
            json.put("object", jsonProd);

            out.print(json.toString());


        } catch (Exception e) {
            json.put("success", false);
            json.put("error", e.toString());
            out.print(json.toString());

        }

        
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType(responseType);
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();

        try {
            Chamado prod = new Gson().fromJson(request.getReader(), Chamado.class);
            JSONObject jsonProd = new JSONObject();
            jsonProd.put("Chamado", prod.getChamado());
            jsonProd.put("titulo", prod.getTitulo());
            jsonProd.put("status", prod.getStatus());
            jsonProd.put("comentario", prod.getComentario());
           

            chamadoDAO.atualizar(prod);

            json.put("success", true);
            json.put("object", jsonProd);
            out.print(json.toString());
        } catch (Exception e) {
            json.put("success", false);
            json.put("error", e.toString());
            out.print(json.toString());

        }
        
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType(responseType);
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();

        try {
            Chamado prod = new Gson().fromJson(request.getReader(), Chamado.class);
            JSONObject jsonProd = new JSONObject();
            jsonProd.put("Chamado", prod.getChamado());
            jsonProd.put("titulo", prod.getTitulo());
            jsonProd.put("status", prod.getStatus());
            jsonProd.put("comentario", prod.getComentario());
            

            chamadoDAO.remover(prod);


            json.put("success", true);
            json.put("object", jsonProd);
            out.print(json.toString());
        } catch (Exception e) {
            json.put("success", false);
            json.put("error", e.toString());
            out.print(json.toString());

        }
        
    }
}
