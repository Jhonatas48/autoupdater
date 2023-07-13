package jhonatas.autoupdater;

public class UpdateHandler {

	
	//private Class<?> updaterClass;
	private AutoUpdater autoUpdater;
	@SuppressWarnings("static-access")
	public UpdateHandler(String currentVersion,
			String github_user,
			String github_token,
			String owner_repository,
			String repository,
			Class<?> updaterClass,
			String jarUrl) {
	
		Checkers.validateStringNotNull(currentVersion,"currentVersion");
		Checkers.validateStringNotNull(github_user,"gihub_user");
		Checkers.validateStringNotNull(github_token,"github_token");
		Checkers.validateStringNotNull(owner_repository,"owner_repository");
		Checkers.validateStringNotNull(repository,"repository");
		Checkers.validadeObjectNotNull(updaterClass,"UpdaterClass");
		Checkers.validateStringNotNull(jarUrl, "jarUrl");
		
		if(!jarUrl.contains(".jar")) {
			jarUrl+=".jar";
		}
	     autoUpdater= new AutoUpdater(currentVersion,github_user,github_token,owner_repository,repository,updaterClass,jarUrl);
		
		
		//		System.out.println(jarUrl);
				
			
	}
	
	public boolean constainsUpdate() {
		return autoUpdater.isUpdateAvailable();
	}
	
	public boolean executeUpdate() {
		
		 try {
			    if(!autoUpdater.isUpdateAvailable()) {
			    	System.out.println("Executando a versão mais recente");
			    	return true;
			    }
			    if(!autoUpdater.performDownload()) {
			    	System.out.println("Erro ao tentar efetuar o download da atualização");
			    }
			  
	           // String java = System.getProperty("java.home") + "/bin/java";
	            //String jarPath = new File(updaterClass.getProtectionDomain().getCodeSource().getLocation().toURI()).getCanonicalPath();
			  //  String jarPath = new File(updaterClass.getProtectionDomain().getCodeSource().getLocation().toURI()).getCanonicalPath();

			  
	           // ProcessBuilder builder = new ProcessBuilder(java, "-jar", jarPath);
	            //builder.start();
	            System.exit(0);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            // Lide com exceções ou erros aqui
	        }
		return false;
	}
	
	
}
