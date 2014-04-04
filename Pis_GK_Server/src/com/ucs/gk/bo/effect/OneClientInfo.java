package com.ucs.gk.bo.effect;

import com.ucs.gk.bo.client.Config_client;

public class OneClientInfo {
	private Config_client client;
	private Effects effects;
	public Config_client getClient() {
		return client;
	}
	public void setClient(Config_client client) {
		this.client = client;
	}
	public Effects getEffects() {
		return effects;
	}
	public void setEffects(Effects effects) {
		this.effects = effects;
	}
	
}
