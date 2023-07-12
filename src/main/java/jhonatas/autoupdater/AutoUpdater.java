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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

class AutoUpdater {

	private String currentversion;
	private String latestVersion;
	private String repository_name;
	private String owner_repository;
	private String github_user;
	private String github_token;
	private GitHub github;
	private GHRepository repository;
	public AutoUpdater(String currentversion, String github_user, String github_token, String owner_repository
			,String repository) {
		this.currentversion = currentversion;
		this.repository_name = repository;
		this.owner_repository = owner_repository;
		this.github_user = github_user;
		this.github_token = github_token;
		
		try {
			GitHub githubInstance = new GitHubBuilder().withOAuthToken(github_token,github_user).build();
			githubInstance.checkApiUrlValidity();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
	}


	public boolean isUpdateAvailable() {
		
		if(github == null) {
			
    		System.out.println("Falha na autenticação");
    		
    	}
    	
    	if(repository == null) {
    		
    		System.out.println("Repositorio não encontrado");
    		
    		return false;
    	}
    	
    	try {
			GHRelease release =  repository.getLatestRelease();
			
			this.latestVersion = release.getName();
			
		  return VersionComparator.compareVersions(latestVersion, currentversion) < 0;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
    	
		
		
	}
	
	
	public boolean performDownload() {
	 	if(github == null) {
    		System.out.println("Falha na autenticação");
    	}
    	
    	if(repository == null) {
    		System.out.println("Repositorio não encontrado");
    		return false;
    	}
    	try {
    		GHRelease release =  repository.getLatestRelease();
    		if(release == null) {
    			throw new RuntimeException("Release nao encontrada");
    		}
    		List<GHAsset>assets = release.getAssets();
    		if(assets == null || assets.isEmpty()) {
    			throw new RuntimeException("Asset nao encontrada");
    		}
    		
			OkHttpClient client = new OkHttpClient();
			  Request request = new Request.Builder()
				        .url(assets.get(0).getUrl())
				        .addHeader("accept"," application/octet-stream")
				         .addHeader("Authorization","Bearer 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")
				        .build();
			  
			  Response response = client.newCall(request).execute();
			if(!response.isSuccessful()) {
			    System.out.println("FALHA NO DOWNLOAD"+response.body().string());
	        	return false;
	        }
			ResponseBody body = response.body();
			
			InputStream in=body.byteStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(body.byteStream()));
			
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
		return false;
	}
}
