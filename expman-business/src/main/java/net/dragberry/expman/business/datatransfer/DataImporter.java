package net.dragberry.expman.business.datatransfer;

import java.io.InputStream;
import java.io.Serializable;

public interface DataImporter extends Serializable {
	
	void doImport(InputStream is) throws Exception;
}
