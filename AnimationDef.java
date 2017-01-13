
public class AnimationDef {
	
	private String name;
	private FrameDef[] frames;
	
	public AnimationDef(String name, FrameDef[] frames){
		this.name = name;
		this.frames = frames;
	}
	
	public FrameDef[] getFrameDef(){
		return frames;
	}
	
}