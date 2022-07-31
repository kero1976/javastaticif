package kero.domain.staticinterface;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
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
		if(src == null) {
			log.info("Error(src is null.)");
			throw new IOException("src is null.");
		}
		File srcFile = FileUtils.toFile(src);
		log.debug("Src File:{}", srcFile);
		Path destPath = Paths.get(dest);
		log.debug("Dest Path:{}", destPath.toAbsolutePath());
		if(srcFile.isFile()) {
			log.debug("Src is File.");
			FileUtils.copyFile(srcFile, destPath.toFile());
		}else if(srcFile.isDirectory()) {
			log.debug("Src is Directory.");
			FileUtils.copyToDirectory(srcFile, destPath.toFile());
		}
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
