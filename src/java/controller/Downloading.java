package controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Downloading", urlPatterns = {"/Downloading"})
public class Downloading extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the absolute path of the web application directory
        String applicationPath = req.getServletContext().getRealPath("");

        // Create a file object for the file to be downloaded
        File fileToDownload = new File(applicationPath + "//files//hello.png");

        // Check if the file exists
        if (!fileToDownload.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 error
            return;
        }

        // Set the content type and header for the response
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=" + fileToDownload.getName() +"");

        // Use try-with-resources to ensure the stream is closed after use
        try (OutputStream outputStream = resp.getOutputStream()) {
            Files.copy(
                    fileToDownload.toPath(),
                    outputStream
            );
        }
    }
}
