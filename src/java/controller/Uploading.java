package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet(name = "Uploading", urlPatterns = {"/Uploading"})
public class Uploading extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get email parameter from the request
        String email = request.getParameter("email");
        System.out.println(email);

        // Get the application path and construct the path to the upload directory
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadPath = applicationPath + File.separator + "files";

        // Ensure the upload directory exists
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Get the file part from the request
        Part filePart = request.getPart("file");

        // Ensure file part is not null and has content
        if (filePart != null && filePart.getSize() > 0) {
            // Get the original filename
            String fileName = filePart.getSubmittedFileName();

            // Construct path to save the uploaded file with its original name
            File newFile = new File(uploadDir, fileName);

            // Use try-with-resources to ensure InputStream is closed
            try (InputStream inputStream = filePart.getInputStream()) {
                Files.copy(inputStream, newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            // Output success message
            response.getWriter().println("File uploaded successfully as " + newFile.getName());
        } else {
            response.getWriter().println("No file selected or file is empty.");
        }
    }
}
