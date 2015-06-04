package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.Document;
import model.Project;
import model.db.ProjectDB;
import model.db.exception.DatabaseAccessError;
import model.exception.InvalidDataException;

/**
 * Servlet implementation class UploadFile
 */
@WebServlet("/UploadFile")
@MultipartConfig
public class UploadFile extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2710705774800519463L;
	private final static Logger LOGGER = Logger.getLogger(UploadFile.class.getCanonicalName());

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=UTF-8");

		final String pathOnServer = request.getServletContext().getRealPath("/") + "Uploaded Files\\" + request.getSession().getAttribute("userId");

		// check whether the path exists, if not, create it.
		try
		{
			File myFilePath = new File(pathOnServer);
			if (!myFilePath.exists())
			{
				myFilePath.mkdirs();
				System.out.println("Creating success");
			}
		} catch (Exception e)
		{
			System.out.println("Creating folder failed");
			e.printStackTrace();
		}

		final Part filePart = request.getPart("file");
		String fileName = getFileName(filePart);
		System.out.println("original name: " + fileName);
		if (fileName.lastIndexOf("\\") >= 0)
		{
			fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
		} else
		{
			fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
		}
		OutputStream out = null;
		InputStream filecontent = null;
		final PrintWriter writer = response.getWriter();

		try
		{
			String filePath = pathOnServer + File.separator + fileName;
			out = new FileOutputStream(new File(pathOnServer + File.separator + fileName));
			filecontent = filePart.getInputStream();

			int read = 0;
			final byte[] bytes = new byte[1024];

			while ((read = filecontent.read(bytes)) != -1)
			{
				out.write(bytes, 0, read);
			}
			LOGGER.log(Level.INFO, "File{0} being uploaded to {1}", new Object[] { fileName, pathOnServer });

			// Add this documents.
			String projectName = request.getParameter("projectName");
			Project project = ProjectDB.getProject(projectName);
			Document doc = new Document(fileName, filePath);
			Date date = new Date();
			doc.setAdded(date);
			project.addDocument(doc);
			// response.sendRedirect(request.getContextPath()+"/PrepareProjectDetails?projectName="+projectName);
			request.getRequestDispatcher("/PrepareProjectDetails").forward(request, response);
		} catch (FileNotFoundException | DatabaseAccessError | InvalidDataException fne)
		{
			writer.println("You either did not specify a file to upload or are " + "trying to upload a file to a protected or nonexistent " + "location.");
			writer.println("<br/> ERROR: " + fne.getMessage());
			writer.println(pathOnServer);

			LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", new Object[] { fne.getMessage() });
		} finally
		{
			if (out != null)
				out.close();
			if (filecontent != null)
				filecontent.close();
			if (writer != null)
				writer.close();
		}
	}

	private String getFileName(final Part part)
	{
		final String partHeader = part.getHeader("content-disposition");
		LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
		for (String content : part.getHeader("content-disposition").split(";"))
		{
			if (content.trim().startsWith("filename"))
			{
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}
