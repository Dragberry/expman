package net.dragberry.expman.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.dragberry.expman.web.common.Constants;

@Controller
public class ExportController implements Serializable {

	private static final long serialVersionUID = 7229937828612348308L;

	private static final Logger LOG = LogManager.getLogger(ExportController.class.getName());

	@RequestMapping(value = Constants.Path.EXPORT, method = RequestMethod.GET)
	public String exportPage() {
		return Constants.View.EXPORT;
	}

	@RequestMapping(value = Constants.Path.DO_EXPORT, method = RequestMethod.GET)
	public void doExport(HttpServletResponse response) {
		File downloadFile = new File("c:\\Users\\Maksi\\Downloads\\Receipt1451854383261.pdf");
		try (InputStream is = new FileInputStream(downloadFile)){
			// set content attributes for the response
	        response.setContentLength((int) downloadFile.length());
	        response.setContentType("application/pdf");
	 
	        // set headers for the response
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
	        response.setHeader(headerKey, headerValue);
	        IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException ex) {
			LOG.info("Error writing file to output stream.", ex);
			throw new RuntimeException("IOError writing file to output stream");
		}

	}

}
