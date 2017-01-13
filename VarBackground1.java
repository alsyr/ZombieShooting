
public class VarBackground1 {

		// Backgrounds initialization
		public static BackgroundDef background = new BackgroundDef();

		// Size of the sprite.
		private static int[] backSize = new int[2];
		
		// Texture for the sprite.    
		public static int sky;
		public static int cube1;
		public static int cube2;
		public static int cube3;
		public static int cube4;
		public static int cube5;
		public static int tree1;
		public static int tree2;
		public static int tree3;
		public static int tree4;
		public static int cloud1;
		public static int cloud2;
		public static int moon1;
		public static int moon2;
		public static int moon3;
		public static int moon4;
		public static int build1;
		public static int build2;
		public static int build3;
		public static int build4;
		public static int build5;
		public static int build6;
		public static int build7;
		public static int build8;
		public static int build9;
		public static int build10;
		public static int build11;
		public static int build12;
		public static int build13;
		public static int build14;
		public static int build15;
		public static int build16;
		public static int build17;
		public static int build18;
		public static int build19;
		public static int build20;
		public static int build21;
		public static int build22;
		public static int build23;
		public static int build24;
		
		public VarBackground1(){		
			
			// Backgrounds initialization
			background.setWidth(100);
			background.setHeight(15);

			// Load the texture for backgrounds
			sky = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "sky.tga", backSize);
			cube1 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "cube1.tga", backSize);
			cube2 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "cube2.tga", backSize);
			cube3 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "cube3.tga", backSize);
			cube4 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "cube4.tga", backSize);
			cube5 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "cube5.tga", backSize);
			tree1 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "tree1.tga", backSize);
			tree2 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "tree2.tga", backSize);
			tree3 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "tree3.tga", backSize);
			tree4 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "tree4.tga", backSize);
			cloud1 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "cloud1.tga", backSize);
			cloud2 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "cloud2.tga", backSize);
			moon1 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "moon1.tga", backSize);
			moon2 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "moon2.tga", backSize);
			moon3 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "moon3.tga", backSize);
			moon4 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "moon4.tga", backSize);
			build1 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "emptyTile.tga", backSize);
			build2 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "building2.tga", backSize);
			build3 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "building3.tga", backSize);
			build4 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "building4.tga", backSize);
			build5 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "building5.tga", backSize);
			build6 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "building6.tga", backSize);
			build7 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "emptyTile.tga", backSize);
			build8 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "emptyTile.tga", backSize);
			build9 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "emptyTile.tga", backSize);
			build10 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "building10.tga", backSize);
			build11 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "emptyTile.tga", backSize);
			build12 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "emptyTile.tga", backSize);
			build13 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "building13.tga", backSize);
			build14 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "building14.tga", backSize);
			build15 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "building15.tga", backSize);
			build16 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "building16.tga", backSize);
			build17 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "building17.tga", backSize);
			build18 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "building18.tga", backSize);
			build19 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "building19.tga", backSize);
			build20 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "building20.tga", backSize);
			build21 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "building21.tga", backSize);
			build22 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "building22.tga", backSize);
			build23 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "building23.tga", backSize);
			build24 = GeneralMethods.glTexImageTGAFile(JavaFramework.gl, "building24.tga", backSize);
		}
}
