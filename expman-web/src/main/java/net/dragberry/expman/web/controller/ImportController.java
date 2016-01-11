package net.dragberry.expman.web.controller;

import java.io.InputStream;
import java.io.Serializable;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.dragberry.expman.business.datatransfer.DataImporter;
import net.dragberry.expman.web.common.Constants;

@Controller
public class ImportController implements Serializable {

	private static final long serialVersionUID = 3478284891817483290L;

	private static final Logger LOG = LogManager.getLogger(ImportController.class.getName());

	@Autowired
	private DataImporter dataImporter;
	
	@RequestMapping(value = Constants.Path.IMPORT, method = RequestMethod.GET)
	public String importPage() {
		return Constants.View.IMPORT;
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

		return Constants.View.IMPORT;
	}

}
