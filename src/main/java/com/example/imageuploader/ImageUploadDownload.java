package com.example.imageuploader;



import com.example.imageuploader.dao.ImageDAO;
import com.example.imageuploader.dao.ImageDAOImp;
import com.example.imageuploader.model.Image;

import java.io.*;
import java.util.Random;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "imageDownloader", value = "/download")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class ImageUploadDownload extends HttpServlet {

    private String filePath;
    private ImageDAO imageDAOI;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("init running");
        File directory = new File(getServletContext().getRealPath("/images/"));
        imageDAOI =  new ImageDAOImp();
        if(!directory.exists()){
            directory.mkdir();
            System.out.println("directory created at " + directory.getAbsolutePath());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("fileName");
        if(fileName == null || fileName.equals("")){
            throw new ServletException("File Name can't be null or empty");
        }
        File file = new File(filePath);
        if(!file.exists()){
            throw new ServletException("File doesn't exists on server.");
        }
        System.out.println("File location on server::"+file.getAbsolutePath());
        ServletContext ctx = getServletContext();
        InputStream fis = new FileInputStream(file);
        String mimeType = ctx.getMimeType(file.getAbsolutePath());
        resp.setContentType(mimeType != null? mimeType:"application/octet-stream");
        resp.setContentLength((int) file.length());
        resp.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        ServletOutputStream os = resp.getOutputStream();
        byte[] bufferData = new byte[1024];
        int read=0;
        while((read = fis.read(bufferData))!= -1){
            os.write(bufferData, 0, read);
        }

        os.flush();
        os.close();
        fis.close();
        System.out.println("File downloaded at client successfully");
        file.delete();
        if (file.exists()) {
            throw new IOException("Deletiion failed");
        }
        else{
            System.out.println("file deleted successfully");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("do post is running");

        try {
            Part filePart = request.getPart("file");
            String fileName = filePart.getSubmittedFileName();
            Image image = new Image(fileName,getUniqueId());
            imageDAOI.addImage(image);
            System.out.println(fileName);
            filePath = getServletContext().getRealPath("/images/") + fileName;

            for (Part part : request.getParts()) {
                System.out.println("for loop");
                System.out.println(filePath);
                filePart.write(filePath);
            }
            StringBuffer url = request.getRequestURL();
            String uri = request.getRequestURI();
            System.out.println(uri);
            String host = url.substring(0, url.indexOf(uri));
            request.setAttribute("link", host+"/Image_Uploader"+"/ready?id="+image.getImageId());

            request.setAttribute("imageName", fileName);
            request.getRequestDispatcher("download.jsp").forward(request, response);
//            PrintWriter printWriter = response.getWriter();
//            printWriter.write("<h1> Image Uploaded</h1> </br>");
//            printWriter.write("<a href=\"image-downloader?fileName="+fileName+"\">Download "+fileName+"</a>");
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }

    }

    public void destroy() {

    }

    public String getUniqueId (){


            String id = RandomIdGenerator.getBase62(16);
            if(imageDAOI.getAllImages()
                    .stream()
                    .map((image -> image.getImageId()))
                    .anyMatch((imageId)->id.equals(imageId))){
                return getUniqueId();
            }
            else{
                return id;
            }



    }

    static class RandomIdGenerator {
        private static char[] _base62chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
                .toCharArray();

        private static Random _random = new Random();

        public static String getBase62(int length) {
            StringBuilder sb = new StringBuilder(length);

            for (int i = 0; i < length; i++)
                sb.append(_base62chars[_random.nextInt(62)]);

            return sb.toString();
        }



    }
}