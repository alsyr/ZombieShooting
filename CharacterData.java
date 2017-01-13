
public class CharacterData {
	
	private float x;
	private float y;
	private float health;
	private AnimationData curAnimation;
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public void setHealth(float health){
		this.health = health;
	}
	
	public float getHealth(){
		return health;
	}
	
	public void setCurAnim(AnimationData curAnimation){
		this.curAnimation = curAnimation;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public AnimationData getAnim(){
		return curAnimation;
	}
}
