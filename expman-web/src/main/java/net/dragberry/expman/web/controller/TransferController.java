package net.dragberry.expman.web.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.dragberry.expman.business.datatransfer.DataExporter;
import net.dragberry.expman.business.datatransfer.DataImporter;
import net.dragberry.expman.web.common.Constants;

@Controller
public class TransferController implements Serializable {

	private static final long serialVersionUID = 3478284891817483290L;

	private static final Logger LOG = LogManager.getLogger(TransferController.class.getName());

	@Autowired
	private DataImporter dataImporter;
	@Autowired
	private DataExporter dataExporter;
	
	@RequestMapping(value = Constants.Path.TRANSFER, method = RequestMethod.GET)
	public String importPage() {
		return Constants.View.TRANSFER;
	}
	
	@RequestMapping(value = Constants.Path.DO_IMPORT, method = RequestMethod.POST)
	public String doImport(@RequestParam("file") MultipartFile file) {
		try {
			InputStream is = file.getInputStream();
			dataImporter.doImport(is);
		} catch (Exception e) {
			LOG.error("File upload error!", e);
			throw new RuntimeException("File upload error!", e);
		}

		return Constants.View.TRANSFER;
	}
	
	@RequestMapping(value = Constants.Path.DO_EXPORT, method = RequestMethod.GET)
	public void doExport(HttpServletResponse response) throws Exception {
		byte[] data = dataExporter.doExport();
		try (InputStream is = new ByteArrayInputStream(data)){
			// set content attributes for the response
	        response.setContentLength(data.length);
	        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	 
	        // set headers for the response
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"", "expman-data.xls");
	        response.setHeader(headerKey, headerValue);
	        IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException ex) {
			LOG.info("Error writing file to output stream.", ex);
			throw new RuntimeException("IOError writing file to output stream");
		}
	}

}
