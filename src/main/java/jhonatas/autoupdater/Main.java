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

public class Main {

	public static void main(String[] args) {
	   
		//System.out.println("TESTE");
		/*
		try {
			GitHub github = new GitHubBuilder().withOAuthToken("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","jhonatas48").build();
			github.checkApiUrlValidity();
			System.out.println("TESTE: "+github.getApiUrl());
			GHRepository repository =  github.getRepository("jhonatas48/bot-java");
			if(repository == null) {
				return;
			}
			
			GHRelease release = repository.getLatestRelease();
			
			@SuppressWarnings("deprecation")
			List<GHAsset>assets= release.getAssets();
		    GHAsset asset = assets.get(0);
		    System.out.println("Nome: "+asset.getName());
		
				//String url  = asset.getBrowserDownloadUrl();
				
				String url = asset.getUrl().toString();
				
				OkHttpClient client = new OkHttpClient();
				  Request request = new Request.Builder()
					        .url(url)
					        .addHeader("accept"," application/octet-stream")
					         .addHeader("Authorization","Bearer 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")
					        .build();
				  Response response;
				try {
					response = client.newCall(request).execute();
					if(!response.isSuccessful()) {
					    System.out.println("FALHA NO DOWNLOAD"+response.body().string());
			        	return ;
			        }
					ResponseBody body = response.body();
					//body.charStream();
					InputStream in=body.byteStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(body.byteStream()));
					//BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					 ReadableByteChannel channel = Channels.newChannel(in);

			         FileOutputStream output = new FileOutputStream("api.jar");
			         output.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);

			         in.close();
			         output.close();
			        
			        reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  
			
			
			  
			System.out.println("TESTE: "+release.getUrl().toString());
			System.out.println("Finalizou");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	    }*/
		
		
	}

}
