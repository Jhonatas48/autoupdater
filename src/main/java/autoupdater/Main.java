package autoupdater;

import java.io.IOException;

import org.kohsuke.github.GHRelease;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

public class Main {

	public static void main(String[] args) {
	   
		//System.out.println("TESTE");
		try {
			GitHub github = new GitHubBuilder().withOAuthToken("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","jhonatas48").build();
			github.checkApiUrlValidity();
		
			GHRepository repository =  github.getRepository("jhonatas48/bot-java");
			if(repository == null) {
				return;
			}
			
			GHRelease release = repository.getLatestRelease();

			
			System.out.println("TESTE: "+(release == null));
			System.out.println("Finalizou");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
