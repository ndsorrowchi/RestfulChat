/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DataAccess.MyChatService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import Utils.ConvertModels;
import Models.MessageListModel;
import Models.MessageModel;
import Utils.CommonUtils;
import ViewModels.MessageListViewModel;
import ViewModels.MessageViewModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

/**
 *
 * @author chiming
 */
public class Chat extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            try{
                StringBuilder sb=new StringBuilder();
                BufferedReader br=request.getReader();
                String str;
                while( (str = br.readLine()) != null ){
                    sb.append(str);
                }    
                JSONObject obj = new JSONObject(sb.toString());
                String fromid=obj.isNull("from")?null:obj.getString("from");
                String toid=obj.isNull("to")?null:obj.getString("to");
                String msgbody=obj.isNull("message")?null:obj.getString("message");
                long ptime=obj.isNull("time")?0:obj.getLong("time");
                //Logger.getLogger(Chat.class.getName()).info(sb.toString());
                //Logger.getLogger(Chat.class.getName()).info((String)obj.get("message"));
                MessageViewModel mvm=new MessageViewModel(fromid,toid,msgbody,ptime);
                MessageModel mm=ConvertModels.view2db(mvm);

                if(mm==null)
                {
                    response.setStatus(400);
                    out.println(CommonUtils.getErrorJSON("Invalid input", "one or more user IDs missing"));
                }
                else if(mm.getMessage()==null)
                {
                    MessageListModel mlm=MyChatService.update(mm);
                    if(mlm==null)
                    {
                        response.setStatus(400);
                        out.println(CommonUtils.getErrorJSON("Invalid input", "Invalid user IDs."));
                    }
                    else
                    {
                        JSONObject result=new JSONObject();
                        JSONArray jarr=new JSONArray();
                        for(int i=0;i<mlm.getMessagelist().length;i++)
                        {
                            JSONObject temp=new JSONObject();
                            temp.put("from", mlm.getMessagelist()[i].getFrom());
                            temp.put("to", mlm.getMessagelist()[i].getTo());
                            temp.put("message", mlm.getMessagelist()[i].getMessage());
                            temp.put("time", mlm.getMessagelist()[i].getTime());
                            jarr.put(temp);
                        }
                        result.put("msglist", jarr);
                        out.println(result.toString());
                    }                    
                }
                else
                {
                    String resultjson=MyChatService.post(mm);
                    if(resultjson==null)
                    {
                        response.setStatus(400);
                        out.println(CommonUtils.getErrorJSON("Invalid input", "Failed to send message. Invalid user IDs."));
                    }
                    else
                    {
                        out.println(resultjson);
                    }
                }
            } catch(Exception ex)
            {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                out.println(CommonUtils.getErrorJSON(ex));
            }
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
