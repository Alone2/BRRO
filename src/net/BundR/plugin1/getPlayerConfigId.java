package net.BundR.plugin1;

public class getPlayerConfigId {
	
	public static String fromName(String Name, plugin1 pl) {
		
		String PlayerId = "BUG";
		for (int i = 0; i < 8; i++) { 
			int e = i + 1; 
			String Config = String.valueOf(pl.getConfig().getString("Player" + String.valueOf(e) + ".name")); 

			if (Config.equals(Name)) {

				PlayerId = String.valueOf(e); 

			} 

		}
		
		return PlayerId;
		
	}
	
	
	public static String fromUUID(String UUID, plugin1 pl) {
		
		String PlayerId = "BUG";
		for (int i = 0; i < 8; i++) { 
			int e = i + 1; 
			String Config = String.valueOf(pl.getConfig().getString("Player" + String.valueOf(e) + ".UUID")); 

			if (Config.equals(UUID)) {

				PlayerId = String.valueOf(e); 

			} 

		}
		
		return PlayerId;
		
	}
	
}
