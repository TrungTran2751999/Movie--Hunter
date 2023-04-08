package com.example.casestudy2.services;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class MoreDAO {
    public static String createImage(HttpServletRequest request, HttpServletResponse response, String image, String newName){
        String realPath = null;
        String result = "";
        try {
            Part part = request.getPart(image);
            realPath = request.getServletContext().getRealPath("admin/upload-admin");
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

            String extension = fileName.split("\\.")[1];
            if(extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg")) {
                if (!Files.exists(Paths.get(realPath))) {
                    Files.createDirectories(Paths.get(realPath));
                }
                part.write(realPath + "/" + fileName);
                PrintWriter out = response.getWriter();

                deleteImage(request, response, (newName + ".png"));

                File file1 = new File(realPath + "/" + fileName);
                File file2 = new File(realPath + "/" + newName + ".png");
                boolean bbb = file1.renameTo(file2);
                File fileTarget = new File("C:\\Users\\Admin\\Desktop\\All-Case\\Module-3\\Case-study-md3\\src\\main\\webapp\\admin\\upload-admin\\" + newName + ".png");
                Files.copy(file2.toPath(), fileTarget.toPath());
                result = "/admin/upload-admin/" + newName + ".png";
                return result;

            }else{
                result = "";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static void deleteImage(HttpServletRequest request, HttpServletResponse response, String nameImage){
        File fileRoot = new File(request.getServletContext().getRealPath("admin/upload-admin"), nameImage);
        File filePath = new File("C:\\Users\\Admin\\Desktop\\All-Case\\Module-3\\Case-study-md3\\src\\main\\webapp\\admin\\upload-admin\\", nameImage);
        boolean a = fileRoot.delete();
        boolean b = filePath.delete();
    }
    public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = "";
        String pass = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                name = cookie.getValue();
            }
            if (cookie.getName().equals("password")) {
                pass = cookie.getValue();
            }
        }
        if (!name.equals("") && !pass.equals("")) {
            return true;
        } else {
            return false;
        }
    }
}
