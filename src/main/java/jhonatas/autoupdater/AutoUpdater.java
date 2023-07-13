package jhonatas.autoupdater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
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
	private GHRepository repositoryInstance;
	private Class<?> updaterClass;
	private String jarName;
	public AutoUpdater(String currentversion, 
			String github_user,
			String github_token,
			String owner_repository,
			String repository,Class<?> updaterClass,
			String jarName) {
		Checkers.validateStringNotNull(currentversion,"currentVersion");
		Checkers.validateStringNotNull(github_user,"gihub_user");
		Checkers.validateStringNotNull(github_token,"github_token");
		Checkers.validateStringNotNull(owner_repository,"owner_repository");
		Checkers.validateStringNotNull(repository,"repository");
		Checkers.validadeObjectNotNull(updaterClass,"UpdaterClass");
		Checkers.validateStringNotNull(jarName, "jarName");
		this.currentversion = currentversion;
		this.owner_repository = owner_repository;
		this.repository_name = repository;
		this.github_user = github_user;
		this.github_token = github_token;
		this.updaterClass = updaterClass;
		this.jarName = jarName;
		try {
			GitHub githubInstance =  new GitHubBuilder().withOAuthToken(github_token,github_user).build();
			githubInstance.checkApiUrlValidity();
			github = githubInstance;
			repositoryInstance = github.getRepository(owner_repository+"/"+repository_name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//
	}


	public boolean isUpdateAvailable() {
		
		if(github == null) {
			
    		System.out.println("Falha na autenticação");
    		return false;
    	}
    	
    	if(repositoryInstance == null) {
    		
    		System.out.println("Repositorio não encontrado");
    		
    		return false;
    	}
    	
    	try {
			GHRelease release =  repositoryInstance.getLatestRelease();
			if(release == null) {
				return false;
			}
			this.latestVersion = release.getName();
			System.out.println(VersionComparator.compareVersions(latestVersion, currentversion));
			
		  return VersionComparator.compareVersions(latestVersion, currentversion) > 0;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
    	
		
		
	}
	
	
	public boolean performDownload() {
		
		if(! (VersionComparator.compareVersions(latestVersion, currentversion) > 0)) {
			System.out.println("Executando a versão mais recente");
			return true;
		}
	 	if(github == null) {
    		System.out.println("Falha na autenticação");
    		return false;
    	}
    	
    	if(repositoryInstance == null) {
    		System.out.println("Repositorio não encontrado");
    		return false;
    	}
    	Checkers.validadeObjectNotNull(updaterClass, "updaterClass");
    	
    	try {
    		GHRelease release =  repositoryInstance.getLatestRelease();
    		if(release == null) {
    			throw new RuntimeException("Release nao encontrada");
    		}
    		List<GHAsset>assets = release.getAssets();
    		if(assets == null || assets.isEmpty()) {
    			throw new RuntimeException("Asset nao encontrada");
    		}
    		System.out.println("Baixando Atualização");
			OkHttpClient client = new OkHttpClient();
			  Request request = new Request.Builder()
				        .url(assets.get(0).getUrl())
				        .addHeader("accept"," application/octet-stream")
				         .addHeader("Authorization","Bearer "+github_token)
				        .build();
			  
			  Response response = client.newCall(request).execute();
			if(!response.isSuccessful()) {
			    System.out.println("FALHA NO DOWNLOAD ERRO: "+response.body().string());
	        	return false;
	        }
			System.out.println("Instalando Atualização");
			ResponseBody body = response.body();
			
			InputStream in=body.byteStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(body.byteStream()));
			
			 ReadableByteChannel channel = Channels.newChannel(in);
			 //String jarPath = new File(updaterClass.getProtectionDomain().getCodeSource().getLocation().getPath()).getAbsolutePath().toString();
			
			// String jarPath = new File(updaterClass.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();

			 FileOutputStream output = new FileOutputStream(jarName);
	         output.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);

	         in.close();
	         output.close();
	        
	        reader.close();
	        System.out.println("Instalação Concluida");
	        return true;
		} catch (Exception
				//| URISyntaxException
				e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
