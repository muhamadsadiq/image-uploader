package com.example.imageuploader;



import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "imageDownloader", value = "/image-downloader")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class ImageUploadDownload extends HttpServlet {

    private String filePath;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("init running");
        File directory = new File(getServletContext().getRealPath("/images/"));

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
            System.out.println(fileName);
            filePath = getServletContext().getRealPath("/images/") + fileName;

            for (Part part : request.getParts()) {
                System.out.println("for loop");
                System.out.println(filePath);
                filePart.write(filePath);
            }
            request.setAttribute("link", request.getRequestURL()+"?fileName="+fileName);
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
}