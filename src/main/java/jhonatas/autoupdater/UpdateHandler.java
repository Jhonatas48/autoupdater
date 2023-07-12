package jhonatas.autoupdater;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class UpdateHandler {

	private String currentVersion;
	private Class<?> updaterClass;
	private AutoUpdater autoUpdater;
	public UpdateHandler(String currentVersion,String github_user,String owner_repository, String github_token,String repository,Class<?> updaterClass) {
	
		Checkers.validateStringNotNull(currentVersion,"currentVersion");
		Checkers.validateStringNotNull(github_user,"gihub_user");
		Checkers.validateStringNotNull(github_token,"github_token");
		Checkers.validateStringNotNull(owner_repository,"owner_repository");
		Checkers.validateStringNotNull(repository,"repository");
		Checkers.validateStringNotNull("","");
		autoUpdater= new AutoUpdater(currentVersion,  github_user,,github_token , owner_repository,repository);
		this.updaterClass = updaterClass;
		
	}
	
	public boolean constainsUpdate() {
		return autoUpdater.isUpdateAvailable();
	}
	
	public boolean executeUpdate() {
		
		 try {
			 
			    if(autoUpdater.performDownload()) {
			    	System.out.println("Erro ao tentar efetuar o download da atualização");
			    }
	            String java = System.getProperty("java.home") + "/bin/java";
	            String jarPath = new File(updaterClass.getProtectionDomain().getCodeSource().getLocation().toURI()).getCanonicalPath();
	            ProcessBuilder builder = new ProcessBuilder(java, "-jar", jarPath);
	            builder.start();
	            System.exit(0);
	            return true;
	        } catch (IOException | URISyntaxException e) {
	            e.printStackTrace();
	            // Lide com exceções ou erros aqui
	        }
		return false;
	}
	
	
}
