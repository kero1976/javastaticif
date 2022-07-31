package kero.domain.staticinterface;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResourcesReaderTest {

	@DisplayName("test1.txtの読込")
	@Test
	void getURL1() {
		String str = "test1.txt";
		var result = ResourcesReader.getURL(str);
		assertThat(result).isNotNull();
	}
	
	@DisplayName("test2/test2.txtの読込")
	@Test
	void getURL2() {
		String str = "test2/test2.txt";
		var result = ResourcesReader.getURL(str);
		assertThat(result).isNotNull();
	}
	
	@DisplayName("存在しないファイルの読込")
	@Test
	void getURL_Nothing() {
		String str = "Nothing";
		var result = ResourcesReader.getURL(str);
		assertThat(result).isNull();
	}
	
	@DisplayName("test2の読込")
	@Test
	void getURL2_Dir() {
		String str = "test2/";
		var result = ResourcesReader.getURL(str);
		assertThat(result).isNotNull();
	}
	
	@DisplayName("test1.txtの読込")
	@Test
	void getFile1() {
		String str = "test1.txt";
		var result = ResourcesReader.getFile(str);
		assertThat(result).isNotNull();
	}
	
	@DisplayName("test2/test2.txtの読込")
	@Test
	void getFile2() {
		String str = "test2/test2.txt";
		var result = ResourcesReader.getFile(str);
		assertThat(result).isNotNull();
	}
	
	@DisplayName("存在しないファイルの読込")
	@Test
	void getFile_Nothing() {
		String str = "Nothing";
		var result = ResourcesReader.getFile(str);
		assertThat(result).isNull();
	}
	
	@DisplayName("test2の読込")
	@Test
	void getFile2_Dir() {
		String str = "test2/";
		var result = ResourcesReader.getFile(str);
		assertThat(result).isNotNull();
	}
	
	@DisplayName("test1.txtのコピー")
	@Test
	void copy1() throws IOException {
		String str = "test1.txt";
		URL url = ResourcesReader.getURL(str);
		ResourcesReader.copy(url, str);
		assertThat(Files.exists(Paths.get(str))).isTrue();
	}
	
	@DisplayName("test2/test2.txtのコピー")
	@Test
	void copy2() throws IOException {
		String str = "test2/test2.txt";
		URL url = ResourcesReader.getURL(str);
		ResourcesReader.copy(url, str);
		assertThat(Files.exists(Paths.get(str))).isTrue();
	}
	
	@DisplayName("存在しないファイルのコピー")
	@Test
	void copy_Nothing() throws IOException {
		String str = "Nothing";
		URL url = ResourcesReader.getURL(str);
		
	    assertThatThrownBy(() -> {
	    	ResourcesReader.copy(url, str);
	    }).isInstanceOfSatisfying(IOException.class, e -> {
	        assertThat(e.getMessage()).isEqualTo("src is null.");
	    });
	}
	
	@DisplayName("test2のコピー")
	@Test
	void copy2_Dir() throws IOException {
		String str = "test2/";
		URL url = ResourcesReader.getURL(str);
		ResourcesReader.copy(url, str);
		assertThat(Files.exists(Paths.get(str))).isTrue();
	}
}
