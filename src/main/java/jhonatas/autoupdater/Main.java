package jhonatas.autoupdater;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;

import org.kohsuke.github.GHAsset;
import org.kohsuke.github.GHRelease;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.kohsuke.github.PagedIterable;
import org.kohsuke.github.PagedIterator;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

class Main {
    /*
	public static void main(String[] args) {
	
		
		UpdateHandler handler = new UpdateHandler(
				"v0.1-alfa",
				"jhonatas48",
				"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",
				"jhonatas48",
				"mysql_api_simples",
				Main.class
				);
		
		System.out.println(handler.constainsUpdate());
	   if(handler.executeUpdate()) {
		   System.out.println("UPDATE REALIZADO");
	   }	
	}
	*/
}
