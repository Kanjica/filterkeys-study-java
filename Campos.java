package k;

public class Campos {
    private String autoRepeatDelay;
    private String autoRepeatRate;
    private String bounceTime;
    private String delayBeforeAcceptance;
    private String flags;

    public Campos(){
        this.autoRepeatDelay = "";
        this.autoRepeatRate = "";
        this.bounceTime = "";
        this.delayBeforeAcceptance = "";
        this.flags = "";
    }

	public String getAutoRepeatDelay() {
		return autoRepeatDelay;
	}

	public void setAutoRepeatDelay(String autoRepeatDelay) {
		this.autoRepeatDelay = autoRepeatDelay;
	}

	public String getAutoRepeatRate() {
		return autoRepeatRate;
	}

	public void setAutoRepeatRate(String autoRepeatTime) {
		this.autoRepeatRate = autoRepeatTime;
	}

	public String getBounceTime() {
		return bounceTime;
	}

	public void setBounceTime(String bounceTime) {
		this.bounceTime = bounceTime;
	}

	public String getDelayBeforeAcceptance() {
		return delayBeforeAcceptance;
	}

	public void setDelayBeforeAcceptance(String delayBeforeAcceptance) {
		this.delayBeforeAcceptance = delayBeforeAcceptance;
	}

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}
    
}
