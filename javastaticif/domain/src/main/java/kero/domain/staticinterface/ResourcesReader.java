package kero.domain.staticinterface;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * リソースファイルの読込用インタフェース.
 * @author kero
 *
 */
public interface ResourcesReader {
	
	/** ロガー. */
	static Logger log = LoggerFactory.getLogger(ResourcesReader.class);
	
	/**
	 * リソースファイルをコピーする.(ファイルが存在する場合は上書きする)
	 * @param src コピー元のリソースファイルのURL
	 * @param dest コピー先
	 * @throws IOException
	 */
	public static void copy(URL src, String dest) throws IOException {
		log.info("START(src: {}, dest: {})", src, dest);
		Path destPath = Paths.get(dest);
		log.debug("Dest Path:{}", destPath.toAbsolutePath());
		//long size = Files.copy(src.openStream(), destPath, StandardCopyOption.REPLACE_EXISTING);
		
		
		 //FileUtils.copyURLToFile(src, new File(dest));
		log.info("END");
	}
	
	/**
	 * リソースファイルのURLを取得する.
	 * @param path リソースファイルのパス
	 * @return リソースファイルのURL
	 */
	public static URL getURL(String path) {
		log.info("START(path: {})", path);
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		var result =  classloader.getResource(path);
		log.info("END({})",result);
		
		return result;
	}
	
	/**
	 * リソースファイルのFileを取得する.
	 * @param path リソースファイルのパス
	 * @return リソースファイルのFile
	 */
	public static File getFile(String path) {
		log.info("START(path: {})", path);
		URL url = getURL(path);
		if(url == null) {
			log.info("END(null)");
			return null;
		}
		String file = url.getFile();
		log.debug("File:{}", file);
		var result = new File(file);
		log.info("END({})",result);
		
		return result;
	}
}
